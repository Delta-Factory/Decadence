package delta.cion.server.plugins;

import delta.cion.api.plugins.TopazPlugin;
import delta.cion.server.plugins.utils.Plugin;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.HashMap;
import java.util.Map;

public class Controller {

	private static final Logger LOGGER = LoggerFactory.getLogger("PLUGINS_CONTROLLER");

	private static final Map<String, URLClassLoader> LOADER_MAP = new HashMap<>();
	private static final Map<String, TopazPlugin> PLUGIN_MAP = new HashMap<>();

	public static boolean enablePlugin(String moduleID) {
		File moduleFile = PluginLoader.getPluginFileMap().get(moduleID);
		URI pathToModule = moduleFile.toURI();

		try {
			URLClassLoader classLoader = new URLClassLoader(
				new URL[]{moduleFile.toURI().toURL()},
				ClassLoader.getSystemClassLoader()
			) {
				@Override
				public URL getResource(String name) {
					URL url = findResource(name);
					if (url == null) url = ClassLoader.getSystemClassLoader().getResource(name);
					return url;
				}

				@Override
				public InputStream getResourceAsStream(String name) {
					URL url = getResource(name);
					try {
						return url != null ? url.openStream() : null;
					} catch (IOException e) {
						return null;
					}
				}
			};

			LOGGER.info("Try to init module {}", moduleFile.getName());

			Plugin plugin = PluginLoader.getPluginMap().get(moduleID);

			String moduleName = plugin.name();
			String mainClass = plugin.mainClass();
			boolean apiStatus = plugin.apiStatus();
			Class<?> clazz = Class.forName(mainClass, true, classLoader);
			if (!TopazPlugin.class.isAssignableFrom(clazz)) {
				LOGGER.error("Cant load {}. It module main class dont extend #TopazPlugin()", moduleName);
				return false;
			}
			Constructor<?> moduleConstructor = clazz.getDeclaredConstructor();
			TopazPlugin topazPlugin = (TopazPlugin) moduleConstructor.newInstance();
			topazPlugin.init(moduleID,
				moduleName,
				plugin.commandPrefix(),
				plugin.enableMsgList(),
				plugin.apiStatus());

			LOADER_MAP.put(moduleID, classLoader);
			PLUGIN_MAP.put(moduleID, topazPlugin);
			return true;
		} catch (Exception e) {
			LOGGER.error("Cant initialize {}!", moduleID);
			LOGGER.error(e.toString());
			return false;
		}

	}

	public static boolean reloadPlugin(String moduleID) {
		if (!disablePlugin(moduleID, false)) isFalse("Cant reload plugin {}", moduleID);
		if (!enablePlugin(moduleID)) isFalse("Cant reload plugin {}", moduleID);
		LOGGER.info("Plugin {} success reloaded!", moduleID);
		return true;
	}

	public static void reloadAll() {
		for (String id : PluginLoader.getPluginIDS()) {
			if (disablePlugin(id, false)) {
				enablePlugin(id);
			} else LOGGER.error("Cant reload {}", id);
		}
	}

	public static boolean disablePlugin(String moduleID) {
		Plugin module = PluginLoader.getPluginMap().get(moduleID);
		if (module == null) return isFalse("Plugin {} not found!", moduleID);

		try {
			TopazPlugin plugin = PLUGIN_MAP.get(moduleID);
			plugin.disable();
			LOADER_MAP.get(moduleID).close();
			return true;
		} catch (IOException e) {
			LOGGER.error("Cant disable {}", moduleID);
			LOGGER.error(e.toString());
			return false;
		}
	}

	public static boolean disableAll() {
		boolean result = true;
		for (String id : PluginLoader.getPluginIDS()) {
			if (!disablePlugin(id, true)) result = false;
		}
		return result;
	}

	private static boolean disablePlugin(String moduleID, boolean delFromServer) {
		boolean returned = disablePlugin(moduleID);
		if (!delFromServer) return returned;
		LOADER_MAP.remove(moduleID);
		PLUGIN_MAP.remove(moduleID);
		PluginLoader.removeFromPluginMap(moduleID);
		PluginLoader.removeFromPluginFileMao(moduleID);
		PluginLoader.removeFromPluginIDS(moduleID);
		return returned;
	}

	private static boolean isFalse(String message, @Nullable String id) {
		LOGGER.error(message, id);
		return false;
	}

}
