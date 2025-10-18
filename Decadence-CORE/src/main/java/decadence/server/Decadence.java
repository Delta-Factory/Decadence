package decadence.server;

import decadence.api.files.utils.FileSaver;
import decadence.api.nodes.CommandNode;
import decadence.server.commands.ModulesReload;
import decadence.server.commands.PluginsList;
import decadence.server.commands.ServerStop;
import decadence.server.commands.UnknownCommand;
import decadence.server.plugins.PluginLoader;
import decadence.server.utils.LoggerCreator;
import net.minestom.server.MinecraftServer;
import net.minestom.server.command.CommandManager;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.*;

public class Decadence {

	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger("SERVER_INIT");

	private static Properties SERVER_PROPERTIES;

	// Maybe it a bad solution but I use it!
	private static final Set<String> CONFIGURATIONS = new HashSet<>(List.of(
		"server.properties",
		"decadence.conf.yml"
	));

	public static void main(String[] args) {
		// Save configurations
		for (String s : CONFIGURATIONS)
			FileSaver.saveFromResources(s);

		init();
	}

	private static void regCommands() {
		CommandNode commandNode = new CommandNode("topaz_server");
		commandNode.addToNode(new ServerStop(), new PluginsList(), new ModulesReload());
		commandNode.registerNode();
		commandNode.registerNodeObjects();
	}

	private static void init() {
		SERVER_PROPERTIES = FileSaver.loadProperties("server.properties");
		ServerInit server = new ServerInit(getAddress());
		LoggerCreator.setDebugLogger();
		server.start();

		CommandManager manager = MinecraftServer.getCommandManager();
		manager.setUnknownCommandCallback(new UnknownCommand());
		regCommands();

		PluginLoader.init(true);
	}

	public static Properties getServerProperties() {
		return SERVER_PROPERTIES;
	}

	private static SocketAddress getAddress() {
		String server_ip = SERVER_PROPERTIES.getProperty("server-ip", "0.0.0.0");
		String server_port_raw = SERVER_PROPERTIES.getProperty("server-port", "25565");

		int server_port = Integer.parseInt(server_port_raw);
		return new InetSocketAddress(server_ip, server_port);
	}

}
