package delta.cion.server.plugins;

import delta.cion.server.plugins.utils.Plugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

public class Controller {

	private static final Logger LOGGER = LoggerFactory.getLogger("PLUGINS_CONTROLLER");

	private static ArrayList<Plugin> PLUGINS = new ArrayList<>();
	private static

	public static void loadAll() {
		PluginLoader pluginLoader = new PluginLoader();
		pluginLoader.loadPlugins();
		PLUGINS = pluginLoader.getPluginsList();
	}

}
