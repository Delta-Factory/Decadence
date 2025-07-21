package delta.cion.api.nodes;

import net.minestom.server.MinecraftServer;
import net.minestom.server.command.CommandManager;
import net.minestom.server.command.builder.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public final class CommandNode extends NodeABS<Command> {

	private final ArrayList<Command> COMMANDS;

	private final Logger LOGGER = LoggerFactory.getLogger("COMMAND_NODE");

	private static final CommandManager COMMAND_MANAGER = MinecraftServer.getCommandManager();

	public CommandNode(String moduleID) {
		super(moduleID);
		this.COMMANDS = new ArrayList<>();
	}

	@Override
	public void registerNodeObjects() {
		for (Command command : this.getObjects()) {
			if (COMMANDS.contains(command)) {
				LOGGER.error("Command {} already registered!", command.getName());
				return;
			}
			COMMAND_MANAGER.register(command);
			COMMANDS.add(command);
		}
	}

	@Override
	public void unregisterNodeObjects() {
		for (Command command : this.getObjects()) {
			if (!COMMANDS.contains(command)) {
				LOGGER.error("Command {} not exists!", command.getName());
				return;
			}
			COMMAND_MANAGER.unregister(command);
			COMMANDS.remove(command);
		}
	}
}
