package decadence.api.permissions;

import delta.cion.api.nodes.PermissionNode;
import net.minestom.server.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PermissionManager {

	record PlayerPermissions(Player player, List<Permission> permissions) {}

	private final Map<Player, List<Permission>> playerPermissions = new HashMap<>();
	private final List<PermissionNode> permissionNodes = new ArrayList<>();

	public PermissionManager() {

	}



	public static boolean hasPermission(Player player, Permission permission) {
		return false;
	}

	public static void addPermission(Player player, Permission permission) {

	}

	public static void removePermission(Player player, Permission permission) {

	}

}
