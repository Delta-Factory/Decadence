package decadence.server.utils;

import net.minestom.server.MinecraftServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class ConsoleReader {


	private static final Logger LOGGER = LoggerFactory.getLogger("SERVER");
	private static final Scanner CONSOLE = new Scanner(System.in);

	public static Thread consoleReader() {
		return new Thread(ConsoleReader::run, "CONSOLE_READER");
	}

	private static void run() {
		while (true) {
			String input = CONSOLE.nextLine();
			if (MinecraftServer.getCommandManager().getCommand(input) != null)
				LOGGER.info("Console issued server command: /{}", input);
			MinecraftServer.getCommandManager().executeServerCommand(input);
		}
	}

}
