package delta.cion.api.permissions;

import org.jetbrains.annotations.NotNull;

public class Permission {

	/**A-Za-z0-9._**/
	private final String PERMISSION;
	private boolean PLAYER_ONLY;



	public Permission(String permission) {
		this.PERMISSION = permission;
		this.PLAYER_ONLY = false;
	}

	public Permission setPlayerOnly() {
		this.PLAYER_ONLY = true;
		return this;
	}

	@NotNull
	public String getPermissionName() {
		return this.PERMISSION;
	}

	public boolean consoleCanUse() {
		return this.PLAYER_ONLY;
	}
}
