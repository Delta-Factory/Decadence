package delta.cion.server;

import delta.cion.api.files.utils.FileSaver;
import delta.cion.api.nodes.CommandNode;
import delta.cion.server.commands.ModulesReload;
import delta.cion.server.commands.ServerStop;
import delta.cion.server.plugins.PluginLoader;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

public class Topaz {

	private static Properties SERVER_PROPERTIES;

	private static final ArrayList<String> CONFIGURATIONS = new ArrayList<>(Arrays.asList(
		"server.properties",
		"decadence.config.yml"
	));

	public static void main(String[] args) {
		saveConfigs();
		regCommands();
		init();
	}

	private static void regCommands() {
		CommandNode commandNode = new CommandNode("topaz_server");

		commandNode.addToNode(new ServerStop());
		commandNode.addToNode(new ModulesReload());
		commandNode.registerNode();
	}

	private static void saveConfigs() {
		for (String s : CONFIGURATIONS) {
			FileSaver.saveFromResources(s);
		}
	}

	private static void init() {
		SERVER_PROPERTIES = FileSaver.loadProperties("server.properties");
		ServerInit server = new ServerInit(getAddress());
		server.start();
		PluginLoader.init(true);
	}

	private static SocketAddress getAddress() {
		String server_ip = SERVER_PROPERTIES.getProperty("server-ip", "0.0.0.0");
		String server_port_raw = SERVER_PROPERTIES.getProperty("server-port", "25565");

		int server_port = Integer.parseInt(server_port_raw);
		return new InetSocketAddress(server_ip, server_port);
	}

}
