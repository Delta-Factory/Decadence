package delta.cion.server.plugins;

import delta.cion.server.plugins.utils.Plugin;
import delta.cion.server.plugins.utils.UsedAfterServerInitException;
import net.minestom.server.MinecraftServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Controller {

	private static final Logger LOGGER = LoggerFactory.getLogger("PLUGINS_CONTROLLER");


	private static boolean isInit = false;

	public static void onServerInit() {
		initChecker();
		PluginLoader loader = new PluginLoader();
		loader.loadPlugins();
		for (Plugin plugin : PluginData.getPLUGINS()) {
			enablePlugin(plugin);
		}
	}

	public static void disablePlugin(Plugin plugin) {

	}

	public static void enablePlugin(Plugin plugin) {

	}

	private static void initChecker() {
		try {
			if (ic()) LOGGER.info("Enable plugins");
		} catch (UsedAfterServerInitException e) {
			LOGGER.error(e.toString());
			MinecraftServer.stopCleanly();
			System.exit(1);
		}
	}

	private static boolean ic() throws UsedAfterServerInitException {
		if (isInit) {
			LOGGER.warn("Attempted launch plugins via Controller#onServerInit() after server init!");
			throw new UsedAfterServerInitException();
		}
		isInit = true;
		return true;
	}

}
