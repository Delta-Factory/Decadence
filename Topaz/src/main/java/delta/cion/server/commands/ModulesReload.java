package delta.cion.server.commands;

import delta.cion.server.plugins.Controller;
import net.minestom.server.command.CommandSender;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.CommandContext;
import net.minestom.server.command.builder.CommandExecutor;
import net.minestom.server.command.builder.arguments.ArgumentType;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModulesReload extends Command implements CommandExecutor {

	private static final Logger LOGGER = LoggerFactory.getLogger("RELOAD_SERVER");

	public ModulesReload() {
		super("reload");
		addSyntax(this, ArgumentType.String("module-id"));
	}

	@Override
	public void apply(@NotNull CommandSender sender, @NotNull CommandContext context) {
		String moduleID = context.get("module-id");
		if (moduleID == null || moduleID.isBlank()) {
			Controller.reloadAll();
			return;
		}
		if (Controller.reloadPlugin(moduleID)) LOGGER.info("Module {} reloaded!", moduleID);
		else LOGGER.info("Module {} cant be reloaded!", moduleID);
	}
}
