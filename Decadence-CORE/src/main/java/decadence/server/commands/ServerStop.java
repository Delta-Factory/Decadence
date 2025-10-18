package decadence.server.commands;

import decadence.server.plugins.Controller;
import net.minestom.server.MinecraftServer;
import net.minestom.server.command.CommandSender;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.CommandContext;
import net.minestom.server.command.builder.CommandExecutor;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerStop extends Command implements CommandExecutor {

	private final Logger LOGGER = LoggerFactory.getLogger("SERVER_STOPPER");

	public ServerStop() {
		super("stop");
		setDefaultExecutor(this);
	}

	@Override
	public void apply(@NotNull CommandSender sender, @NotNull CommandContext context) {
		LOGGER.info("Stopping plugins");
		Controller.disableAll();
		MinecraftServer.stopCleanly();
		LOGGER.info("Exiting from Topaz!");
		System.exit(0);
	}

}
