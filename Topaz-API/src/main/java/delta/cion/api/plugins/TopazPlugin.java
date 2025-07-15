package delta.cion.api.plugins;

import delta.cion.api.files.utils.FileSaver;
import delta.cion.api.nodes.CommandNode;
import delta.cion.api.files.configurations.FileConfiguration;
import delta.cion.api.files.utils.SenderUtils;
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
	private SenderUtils senderUtils;

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

		this.pluginID = id;
		this.pluginName = name;
		this.apiEnabled = apiStatus;

		this.pluginDir = new File("plugins", pluginID);
		if (!pluginDir.exists()) pluginDir.mkdirs();

		if (enableMsgList) enableMessageList();
		this.configFile = new File(pluginDir, "config.yml");
		this.pluginLogger = LoggerFactory.getLogger(pluginName);

		this.eventNode = EventNode.all(pluginID);
		this.commandNode = new CommandNode(pluginID);

		preEnable();
		onEnable();
	}

	private void enableMessageList() throws FileNotFoundException {
		File messagesFile = new File(pluginDir, "messages.yml");
		FileSaver.saveFromResources("messages.yml", messagesFile);
		this.senderUtils = new SenderUtils(messagesFile);
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
		if (!configFile.exists()) saveDefaultConfig();

		try (InputStream is = new FileInputStream(configFile)) {
			config = new FileConfiguration(new Yaml().load(is));
			return config;
		} catch (IOException e) {
			getLogger().error("Cannot load FileConfiguration it TopazPlugin#getConfig()", e);
			throw new RuntimeException("Cannot load FileConfiguration it TopazPlugin#getConfig()", e);
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
		if (!configFile.exists() || configFile.isDirectory()) {
			FileSaver.saveFromResources("config.yml", configFile);
		}
	}

	public final CommandNode getCommandNode() {
		return this.commandNode;
	}

	public final Logger getLogger() {
		return this.pluginLogger;
	}

	public final SenderUtils getSenderUtils() {
		return this.senderUtils;
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
