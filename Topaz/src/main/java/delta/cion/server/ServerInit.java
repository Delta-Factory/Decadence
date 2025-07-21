package delta.cion.server;

import delta.cion.server.utils.ConnectionChecker;
import delta.cion.server.utils.ConsoleReader;
import net.minestom.server.MinecraftServer;

import java.net.SocketAddress;

public class ServerInit {

	private final MinecraftServer SERVER = MinecraftServer.init();

	private final SocketAddress SERVER_ADDRESS;

	public ServerInit(SocketAddress serverAddress) {
		MinecraftServer.setBrandName("DECADENCE");
		ConnectionChecker.initProtocolChecker();
		this.SERVER_ADDRESS = serverAddress;
	}

	public void start() {
		SERVER.start(SERVER_ADDRESS);
		ConsoleReader.consoleReader().start();
	}
}
