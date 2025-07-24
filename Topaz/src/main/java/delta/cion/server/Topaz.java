package delta.cion.server;

import delta.cion.api.files.utils.FileSaver;
import delta.cion.api.nodes.CommandNode;
import delta.cion.server.commands.ModulesReload;
import delta.cion.server.commands.PluginsList;
import delta.cion.server.commands.ServerStop;
import delta.cion.server.commands.UnknownCommand;
import delta.cion.server.plugins.PluginLoader;
import delta.cion.server.utils.LoggerCreator;
import net.minestom.server.MinecraftServer;
import net.minestom.server.command.CommandManager;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

public class Topaz {

	private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger("SERVER_INIT");

	private static Properties SERVER_PROPERTIES;

	// Use that later.
	// LOL
	private static final ArrayList<String> CONFIGURATIONS = new ArrayList<>(Arrays.asList(
		"server.properties"
	));

	public static void main(String[] args) {
		saveConfigs();
		init();
	}

	private static void regCommands() {
		CommandNode commandNode = new CommandNode("topaz_server");
		commandNode.addToNode(new ServerStop(), new PluginsList(), new ModulesReload());
		commandNode.registerNode();
		commandNode.registerNodeObjects();
	}

	private static void saveConfigs() {
		for (String s : CONFIGURATIONS)
			FileSaver.saveFromResources(s);
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
