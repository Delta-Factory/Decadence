package delta.cion.server.commands;

import delta.cion.api.util.Sender;
import delta.cion.server.plugins.PluginLoader;
import net.minestom.server.command.CommandSender;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.CommandContext;
import net.minestom.server.command.builder.CommandExecutor;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class PluginsList extends Command implements CommandExecutor {

	public PluginsList() {
		super("plugins");
		addSyntax(this);
	}

	@Override
	public void apply(@NotNull CommandSender sender, @NotNull CommandContext context) {
		ArrayList<String> plugins = PluginLoader.getPluginIDS();
		ArrayList<String> pluginNames = new ArrayList<>();
		for (String s : plugins)
			pluginNames.add(PluginLoader.getPluginMap().get(s).name());

		String message = "Topaz plugins:\n"+String.join(", ", pluginNames);
		Sender.send(sender, message);
	}
}
