package decadence.server.plugins;

import decadence.server.plugins.utils.Plugin;
import decadence.server.plugins.utils.ValidatePlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PluginLoader {

	private static final Logger LOGGER = LoggerFactory.getLogger("MODULE_LOADER");

	private static final File PLUGIN_DIR = new File("plugins");

	private static final Map<String, Plugin> PLUGIN_MAP = new HashMap<>();
	private static final Map<String, File> PLUGIN_FILE_MAP = new HashMap<>();
	private static final ArrayList<String> PLUGIN_IDS = new ArrayList<>();

	private static FileFilter isJar() {
		return pathname -> pathname.getName().endsWith(".jar");
	}

	public static void init() {
		if (!checkDir()) return;

		File[] jars = PLUGIN_DIR.listFiles(isJar());
		if (jars == null) return;

		for (File jarFile : jars) {
			ValidatePlugin checker = new ValidatePlugin(jarFile);
			LOGGER.info("Try to load {}", jarFile.getName());
			Plugin plugin = checker.getPlugin();
			if (plugin == null) continue;
			if (PLUGIN_MAP.containsKey(plugin.id())) {
				LOGGER.info("Unable to load two modules with one id!");
				continue;
			}

			addToPluginIDS(plugin.id());
			addToPluginMap(plugin.id(), plugin);
			addToPluginFileMap(plugin.id(), jarFile);
		}
	}

	public static void init(boolean isStart) {
		init();
		if (!isStart) return;
		for (String id : PLUGIN_IDS) {
			Controller.enablePlugin(id);
		}
	}

	// IDs map
	public static ArrayList<String> getPluginIDS() {
		return PLUGIN_IDS;
	}

	public static void addToPluginIDS(String pluginID) {
		PLUGIN_IDS.add(pluginID);
	}

	public static void removeFromPluginIDS(String pluginID) {
		PLUGIN_IDS.remove(pluginID);
	}

	// Module map
	public static Map<String, Plugin> getPluginMap() {
		return PLUGIN_MAP;
	}

	public static void addToPluginMap(String pluginID, Plugin plugin) {
		PLUGIN_MAP.put(pluginID, plugin);
	}

	public static void removeFromPluginMap(String pluginID) {
		PLUGIN_MAP.remove(pluginID);
	}

	// Module file map
	public static Map<String, File> getPluginFileMap() {
		return PLUGIN_FILE_MAP;
	}

	public static void addToPluginFileMap(String pluginID, File pluginFile) {
		PLUGIN_FILE_MAP.put(pluginID, pluginFile);
	}

	public static void removeFromPluginFileMao(String pluginID) {
		PLUGIN_FILE_MAP.remove(pluginID);
	}

	private static boolean checkDir() {
		if (!PLUGIN_DIR.exists() || PLUGIN_DIR.isFile()) {
			if (PLUGIN_DIR.mkdir()) LOGGER.info("Dir for plugins created!");
			else LOGGER.info("Cant create dir for plugins");
			return false;
		} else return true;
	}
}
