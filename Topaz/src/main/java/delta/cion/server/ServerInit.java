package delta.cion.server;

import net.minestom.server.MinecraftServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.SocketAddress;
import java.util.Scanner;

public class ServerInit {

	private final Logger LOGGER = LoggerFactory.getLogger("SERVER");

	private final MinecraftServer SERVER = MinecraftServer.init();
	private final Scanner CONSOLE = new Scanner(System.in);

	private final SocketAddress SERVER_ADDRESS;

	public ServerInit(SocketAddress serverAddress) {
		MinecraftServer.setBrandName("DECADENCE");
		this.SERVER_ADDRESS = serverAddress;
	}

	public void start() {
		SERVER.start(SERVER_ADDRESS);
        consoleReader();
	}

	private void consoleReader() {
		Thread console = new Thread(this::run, "CONSOLE_READER");
		console.start();
	}

	private void run() {
		while (true) {
			String input = CONSOLE.nextLine();
			LOGGER.info("Console issued server command: /{}", input);
			MinecraftServer.getCommandManager().executeServerCommand(input);
		}
	}
}
