package delta.cion.server.plugins;

import delta.cion.server.plugins.utils.Plugin;
import delta.cion.server.plugins.utils.PluginState;
import delta.cion.server.plugins.utils.PluginsData;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PluginData extends PluginsData implements AutoCloseable {

	private static ArrayList<Plugin> PLUGINS = new ArrayList<>();
	private static final Map<Plugin, PluginState> PLUGINS_STATUS = new HashMap<>();
	private static final Map<Plugin, File> PLUGINS_FILES = new HashMap<>();

	public PluginData() {

	}

	@Override
	public ArrayList<Plugin> getPLUGINS() {
		return PLUGINS;
	}

	@Override
	public void setPLUGINS(ArrayList<Plugin> plugins) {
		PLUGINS = plugins;
	}

	@Override
	public void cleanPLUGINS() {
		PLUGINS.clear();
	}

	@Override
	public PluginState getPluginStatus(Plugin plugin) {
		return PLUGINS_STATUS.get(plugin);
	}

	@Override
	public void setPluginsStatus(Plugin plugin, PluginState state) {
		PLUGINS_STATUS.put(plugin, state);
	}

	@Override
	public File getPluginFile(Plugin plugin) {
		return PLUGINS_FILES.get(plugin);
	}

	@Override
	public void setPluginFile(Plugin plugin, File file) {
		PLUGINS_FILES.put(plugin, file);
	}
	
	@Override
	public void close() {
		PLUGINS.clear();
		PLUGINS_FILES.clear();
		PLUGINS_STATUS.clear();
	}
}
