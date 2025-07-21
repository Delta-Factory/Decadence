package delta.cion.server.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.serializer.ComponentSerializer;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Player;
import net.minestom.server.network.PlayerProvider;

public class ConnectionChecker {

	private static final ComponentSerializer<Component, TextComponent, String> SERIALIZER = PlainTextComponentSerializer.plainText();
	private static final Component message = SERIALIZER.deserialize("Your version dont support by DECADANCE!");

	private static final int PROTOCOL_VERSION = MinecraftServer.PROTOCOL_VERSION;

	public static void initProtocolChecker() {
		MinecraftServer.getConnectionManager().setPlayerProvider(getPlayerProvider());
	}

	private static PlayerProvider getPlayerProvider() {
		return (connection, profile) -> {
			if (connection.getProtocolVersion() != PROTOCOL_VERSION)
				connection.kick(message);
			return new Player(connection, profile);
		};
	}
}
