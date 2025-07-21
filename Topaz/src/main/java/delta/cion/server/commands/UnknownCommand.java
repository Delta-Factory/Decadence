package delta.cion.server.commands;

import delta.cion.api.util.Sender;
import net.minestom.server.command.CommandSender;
import net.minestom.server.utils.callback.CommandCallback;
import org.jetbrains.annotations.NotNull;

public class UnknownCommand implements CommandCallback {

	@Override
	public void apply(@NotNull CommandSender sender, @NotNull String s) {
		Sender.send(sender, "Unknown command: /"+s);
	}
}
