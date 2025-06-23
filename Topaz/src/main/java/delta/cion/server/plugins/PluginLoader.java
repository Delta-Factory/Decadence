package delta.cion.server.plugins;

import delta.cion.server.Topaz;
import delta.cion.server.plugins.utils.Plugin;
import delta.cion.server.plugins.utils.PluginsData;
import delta.cion.server.plugins.utils.ValidatePlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;

public class PluginLoader {

	private final Logger LOGGER = LoggerFactory.getLogger("PLUGIN_LOADER");

	private final File PLUGINS_DIR = new File("plugins");
	private final File[] JARS;
	private final ArrayList<Plugin> PLUGINS;
	private final PluginData DATA;

	public PluginLoader() {
		JARS = PLUGINS_DIR.listFiles(isJar());
		DATA = Topaz.getPluginData();
		PLUGINS = new ArrayList<>();
		if (!checkDir()) return;
		if (JARS == null) return;
	}

	public void loadPlugins() {
		assert JARS != null;
		for (File f : JARS) {
			ValidatePlugin validator = new ValidatePlugin(f);
			Plugin plugin = validator.getPlugin();
			if (plugin == null) return;
			PLUGINS.add(plugin);
		}
		DATA.setPLUGINS(PLUGINS);
	}

	private boolean checkDir() {
		if (!PLUGINS_DIR.exists() || PLUGINS_DIR.isFile()) {
			if (PLUGINS_DIR.mkdir()) return isFalse("Directory for plugins creates successful");
			return isFalse("Cant create a plugins directory! Try to check permissions and restart this server!");
		} else return true;
	}

	private FileFilter isJar() {
		return pathname -> pathname.getName().endsWith(".jar");
	}

	private boolean isFalse(String message) {
		LOGGER.error(message);
		return false;
	}

}
