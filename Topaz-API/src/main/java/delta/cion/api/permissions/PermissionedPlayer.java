package delta.cion.api.permissions;

import net.minestom.server.entity.Player;
import net.minestom.server.network.player.GameProfile;
import net.minestom.server.network.player.PlayerConnection;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PermissionedPlayer extends Player {

	private final List<String> playerPermissions;

	public PermissionedPlayer(@NotNull PlayerConnection playerConnection, @NotNull GameProfile gameProfile) {
		super(playerConnection, gameProfile);
		this.playerPermissions = new ArrayList<>();
	}

	public final boolean hasPermission(String permission) {
		return playerPermissions.contains(permission);
	}

	public final void addPermission(String permission) {
		playerPermissions.add(permission);
	}

	public final void removePermission(String permission) {
		playerPermissions.remove(permission);
	}

}
