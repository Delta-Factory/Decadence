package delta.cion.api.commands;

import net.minestom.server.MinecraftServer;
import net.minestom.server.command.CommandManager;
import net.minestom.server.command.builder.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class CommandNode {

	private final Logger LOGGER = LoggerFactory.getLogger("COMMAND_NODE");

	private final String nodeName;
	private final CommandManager COMMAND_MANAGER;

	private final ArrayList<Command> COMMANDS;


	public CommandNode(String name) {
		COMMAND_MANAGER = MinecraftServer.getCommandManager();
		COMMANDS = new ArrayList<>();
		this.nodeName = name;
	}

	public String getNodeName() {
		return this.nodeName;
	}

	public void registerCommand(Command command) {
		if (COMMANDS.contains(command)) {
			LOGGER.error("Command {} already registered!", command.getName());
			return;
		}
		COMMAND_MANAGER.register(command);
		COMMANDS.add(command);
	}

	public void unregisterCommand(Command command) {
		if (!COMMANDS.contains(command)) {
			LOGGER.error("Command {} not exists!", command.getName());
			return;
		}
		COMMAND_MANAGER.unregister(command);
		COMMANDS.remove(command);
	}

	public void unregisterAll() {
		for (Command command : COMMANDS) {
			unregisterCommand(command);
		}
	}
}
