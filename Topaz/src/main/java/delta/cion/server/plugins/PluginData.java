package delta.cion.server.plugins;

import delta.cion.server.plugins.utils.Plugin;
import delta.cion.server.plugins.utils.PluginState;
import delta.cion.server.plugins.utils.PluginsData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PluginData extends PluginsData {

	private static ArrayList<Plugin> PLUGINS = new ArrayList<>();
	private static Map<Plugin, PluginState> PLUGINS_STATUS = new HashMap<>();

	public static ArrayList<Plugin> getPLUGINS() {
		return PLUGINS;
	}

	public static void setPLUGINS(ArrayList<Plugin> plugins) {
		PLUGINS = plugins;
	}

	public static void cleanPLUGINS() {
		PLUGINS.clear();
	}

	public static PluginState getPluginStatus(Plugin plugin) {
		return PLUGINS_STATUS.get(plugin);
	}

	public static void setPluginsStatus(Plugin plugin, PluginState state) {
		PLUGINS_STATUS.put(plugin, state);
	}

}
