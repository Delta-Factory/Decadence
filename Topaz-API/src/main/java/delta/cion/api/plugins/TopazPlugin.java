package delta.cion.api.plugins;

import delta.cion.api.commands.CommandNode;
import delta.cion.api.files.configurations.FileConfiguration;
import delta.cion.api.files.utils.Sender;
import net.minestom.server.event.Event;
import net.minestom.server.event.EventNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.*;

public class TopazPlugin {

	private boolean apiEnabled;
	private String pluginID;
	private String pluginName;
	private EventNode<Event> eventNode;
	private File pluginDir;
	private File configFile;
	private FileConfiguration config;
	private CommandNode commandNode;
	private Logger pluginLogger;

	private String commandPrefix = null;
	private File messageList = null;

	/**
	 * Main plugin class
	 * Init main params and start onEnable() method
	 * @param id is plugin utility name (used in commands and logic)
	 * @param name is plugin human-readable name
	 */
	public final void init(String id, String name, String command, boolean enableMsgList, boolean apiStatus) throws FileNotFoundException {
		if (command != null && !command.isBlank()) commandPrefix = command;
		else commandPrefix = id;
		if (enableMsgList) enableMessageList();
		this.pluginID = id;
		this.pluginName = name;
		this.eventNode = EventNode.all(pluginID);
		this.commandNode = new CommandNode(pluginID);
		this.pluginDir = new File("plugins", pluginID);
		this.configFile = new File(pluginDir, "config.yml");
		this.pluginLogger = LoggerFactory.getLogger(pluginName);
		this.apiEnabled = apiStatus;

		preEnable();
		onEnable();
	}

	private final void enableMessageList() throws FileNotFoundException {
		this.messageList = new File(pluginDir, "messages.yml");
		Sender.setMessageList(this.messageList);
	}

	public final void disable() {
		preDisable();
		onDisable();
	}

	public final String getPluginID() {
		return this.pluginID;
	}

	public final String getPluginName() {
		return this.pluginName;
	}

	public final EventNode<Event> getEventNode() {
		return this.eventNode;
	}

	public final File getPluginDir() {
		return this.pluginDir;
	}

	public final File getConfigFile() {
		return this.configFile;
	}

	public final FileConfiguration getConfig() {
		if (config != null) return config;
		try (InputStream is = new FileInputStream(configFile)) {
			config = new FileConfiguration(new Yaml().load(is));
			return config;
		} catch (IOException e) {
			getLogger().error(e.toString());
			return null;
		}
	}

	public final void reloadConfig() {
		try (InputStream is = new FileInputStream(configFile)) {
			config = new FileConfiguration(new Yaml().load(is));
		} catch (IOException e) {
			getLogger().error(e.toString());
		}
	}

	public final void saveDefaultConfig() {
		try {
			if (!configFile.exists() || configFile.isDirectory()) {
				if (configFile.createNewFile()) getLogger().info("Base config file created!");
				else getLogger().info("Cant create base config file!");
			}
		} catch (IOException e) {
			getLogger().error(e.toString());
		}
	}

	public final CommandNode getCommandNode() {
		return this.commandNode;
	}

	public final Logger getLogger() {
		return this.pluginLogger;
	}

	// Basic
	public void apiInit() {} // Used only if "contains-api" is true (in plugin.yml)
	public void apiDisable() {} // Used only if "contains-api" is true (in plugin.yml)
	public void onEnable() {}
	public void onDisable() {}
	// Other
	public final void preEnable() {
		if (apiEnabled) apiInit();
	}
	public final void preDisable() {
		if (apiEnabled) apiDisable();
	}
}
