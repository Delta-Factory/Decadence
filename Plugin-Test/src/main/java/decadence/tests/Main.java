package decadence.tests;

import decadence.api.plugin.Plugin;

public class Main extends Plugin {

	@Override
	public void onEnable() {
		getLogger().info("Plugin started!");
	}

	@Override
	public void onDisable() {
		getLogger().info("Disabling plugin!");
	}
}
