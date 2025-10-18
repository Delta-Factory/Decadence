package decadence.server.commands;

import decadence.api.util.Sender;
import decadence.server.plugins.Controller;
import decadence.server.plugins.PluginLoader;
import net.minestom.server.command.CommandSender;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.CommandContext;
import net.minestom.server.command.builder.CommandExecutor;
import net.minestom.server.command.builder.arguments.ArgumentType;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModulesReload extends Command implements CommandExecutor {

	private final Logger LOGGER = LoggerFactory.getLogger("RELOAD_COMMAND");

	public ModulesReload() {
		super("reload");
		addSyntax(this, ArgumentType.String("module-id"));
	}

	@Override
	public void apply(@NotNull CommandSender sender, @NotNull CommandContext context) {
		String moduleID = context.get("module-id");
		LOGGER.debug("ModuleID: {}", moduleID);
		if (moduleID == null || moduleID.isBlank()) {
			Sender.send(sender, "Reloading all modules..");
			Controller.reloadAll();
		}
		else if (!PluginLoader.getPluginIDS().contains(moduleID)) Sender.send(sender, "Unknown plugin");
		else if (Controller.reloadPlugin(moduleID)) Sender.send(sender, "Module {} reloaded!", moduleID);
		else Sender.send(sender, "Module {} cant be reloaded!", moduleID);
	}
}
