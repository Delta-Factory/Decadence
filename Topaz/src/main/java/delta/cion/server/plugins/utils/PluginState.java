package delta.cion.server.plugins.utils;

import org.jetbrains.annotations.NotNull;

public enum PluginState {

	// Dont print
	LOADED(""),
	// Dont print
	DISABLED(""),
	// Show
	LOAD_ERROR("Plugin load error. See console or logs for more information"),
	INVALID_DATA("Plugin data incorrect. See console or logs for more information");

	private final String message;

	PluginState(@NotNull String message) {
		this.message = message;
	}

	@NotNull
	public String getMessage() {
		return message;
	}
}
