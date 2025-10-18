package decadence.api.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.serializer.ComponentSerializer;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import net.minestom.server.command.CommandSender;
import net.minestom.server.entity.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Formatter;

public class Sender {

	private static final ComponentSerializer<Component, TextComponent, String> SERIALIZER = PlainTextComponentSerializer.plainText();

	private static final Logger LOGGER = LoggerFactory.getLogger("SENDER");

	public static void send(Player player, String message) {
		send((CommandSender) player, message);
	}

	public static void send(Player player, String message, Object... objects) {
		message = new Formatter().format(message, objects).toString();
		send((CommandSender) player, message);
	}

	public static void send(Player player, Component message) {
		send((CommandSender) player, message);
	}

	public static void send(CommandSender player, String message, Object... objects) {
		message = new Formatter().format(message, objects).toString();
		send((CommandSender) player, message);
	}

	public static void send(CommandSender sender, String message) {
		if (sender instanceof Player) sender.sendMessage(message);
		else LOGGER.info(message);
	}

	public static void send(CommandSender sender, Component message) {
		if (sender instanceof Player) sender.sendMessage(message);
		else LOGGER.info(SERIALIZER.serialize(message));
	}
}
