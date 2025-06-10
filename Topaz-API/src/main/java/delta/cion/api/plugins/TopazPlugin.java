package delta.cion.api.plugins;

import delta.cion.api.commands.CommandNode;
import delta.cion.api.files.configurations.FileConfiguration;
import net.minestom.server.event.Event;
import net.minestom.server.event.EventNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.*;

public class TopazPlugin {

	private String pluginID;
	private String pluginName;
	private EventNode<Event> eventNode;
	private File pluginDir;
	private File configFile;
	private FileConfiguration config;
	private CommandNode commandNode;
	private Logger pluginLogger;

	private void init(String pluginID, String pluginName) {
		this.pluginID = pluginID;
		this.pluginName = pluginName;
		this.eventNode = EventNode.all(pluginID);
		this.pluginDir = new File("plugins", pluginID);
		this.configFile = new File(pluginDir, "config.yml");
		this.pluginLogger = LoggerFactory.getLogger(pluginName);
		preEnable();
		onEnable();
	}

	public final void disable() {
		preDisable();
		onDisable();
	}

	public String getPluginID() {
		return this.pluginID;
	}

	public String getPluginName() {
		return this.pluginName;
	}

	public EventNode<Event> getEventNode() {
		return this.eventNode;
	}

	public File getPluginDir() {
		return this.pluginDir;
	}

	public File getConfigFile() {
		return this.configFile;
	}

	public FileConfiguration getConfig() {
		if (config != null) return config;
		try (InputStream is = new FileInputStream(configFile)) {
			config = new FileConfiguration(new Yaml().load(is));
			return config;
		} catch (IOException e) {
			getLogger().error(e.toString());
			return null;
		}
	}

	public void reloadConfig() {
		try (InputStream is = new FileInputStream(configFile)) {
			config = new FileConfiguration(new Yaml().load(is));
		} catch (IOException e) {
			getLogger().error(e.toString());
		}
	}

	public void saveDefaultConfig() {
		try {
			if (!configFile.exists() || configFile.isDirectory()) {
				if (configFile.createNewFile()) getLogger().info("Base config file created!");
				else getLogger().info("Cant create base config file!");
			}
		} catch (IOException e) {
			getLogger().error(e.toString());
		}
	}

	public CommandNode getCommandNode() {
		return this.commandNode;
	}

	public Logger getLogger() {
		return this.pluginLogger;
	}

	// Basic
	public void onEnable() {}
	public void onDisable() {}
	// Other
	public void preEnable() {}
	public void preDisable() {}
}
