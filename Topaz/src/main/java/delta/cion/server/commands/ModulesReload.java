package delta.cion.server.commands;

import delta.cion.api.util.Sender;
import delta.cion.server.plugins.Controller;
import delta.cion.server.plugins.PluginLoader;
import net.minestom.server.command.CommandSender;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.CommandContext;
import net.minestom.server.command.builder.CommandExecutor;
import net.minestom.server.command.builder.arguments.ArgumentType;
import org.jetbrains.annotations.NotNull;

public class ModulesReload extends Command implements CommandExecutor {

	public ModulesReload() {
		super("reload");
		addSyntax(this, ArgumentType.String("module-id"));
	}

	@Override
	public void apply(@NotNull CommandSender sender, @NotNull CommandContext context) {
		String moduleID = context.get("module-id");
		if (moduleID == null || moduleID.isBlank()) {
			Sender.send(sender, "Reloading all modules..");
			Controller.reloadAll();
			return;
		}
		if (!PluginLoader.getPluginIDS().contains(moduleID)) Sender.send(sender, "Unknown plugin");
		if (Controller.reloadPlugin(moduleID)) Sender.send(sender, "Module {} reloaded!", moduleID);
		else Sender.send(sender, "Module {} cant be reloaded!", moduleID);
	}
}
