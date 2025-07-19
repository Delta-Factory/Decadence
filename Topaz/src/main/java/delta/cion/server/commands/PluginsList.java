package delta.cion.server.commands;

import delta.cion.server.plugins.PluginLoader;
import net.minestom.server.command.CommandSender;
import net.minestom.server.command.builder.Command;
import net.minestom.server.command.builder.CommandContext;
import net.minestom.server.command.builder.CommandExecutor;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class PluginsList extends Command implements CommandExecutor {

	private static final Logger LOGGER = LoggerFactory.getLogger("PLUGINS_LIST");

	public PluginsList() {
		super("plugins");
	}

	@Override
	public void apply(@NotNull CommandSender sender, @NotNull CommandContext context) {
		ArrayList<String> plugins = PluginLoader.getPluginIDS();
		sender.sendMessage(String.join(",", plugins));
	}
}
