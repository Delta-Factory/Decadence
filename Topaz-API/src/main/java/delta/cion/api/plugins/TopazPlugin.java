package delta.cion.api.plugins;

import delta.cion.api.files.utils.FileSaver;
import delta.cion.api.nodes.CommandNode;
import delta.cion.api.files.configurations.FileConfiguration;
import delta.cion.api.files.utils.SenderUtils;
import net.minestom.server.MinecraftServer;
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
	public final void init(String id, String name, String command, boolean enableMsgList, boolean apiStatus) {
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
		MinecraftServer.getGlobalEventHandler().addChild(this.eventNode);

		finalChecks();
		preEnable();
		onEnable();
	}

	private void enableMessageList() {
		this.messageList = new File(pluginDir, "messages.yml");
		saveFromResources("messages.yml", this.messageList.getAbsolutePath());
		this.senderUtils = new SenderUtils(this.messageList);
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
		if (this.config != null) return config;
		if (!configFile.exists()) saveDefaultConfig();

		try (InputStream is = new FileInputStream(configFile)) {
			this.config = new FileConfiguration(new Yaml().load(is));
			return this.config;
		} catch (IOException e) {
			getLogger().error("Cannot load FileConfiguration it TopazPlugin#getConfig()", e);
			throw new RuntimeException("Cannot load FileConfiguration it TopazPlugin#getConfig()", e);
		}
	}

	public final void reloadConfig() {
		try (InputStream is = new FileInputStream(configFile)) {
			this.config = new FileConfiguration(new Yaml().load(is));
		} catch (IOException e) {
			getLogger().error(e.toString());
		}
	}

	public final void saveDefaultConfig() {
		if (!configFile.exists() || configFile.isDirectory()) {
			saveFromResources("config.yml", configFile.getAbsolutePath());
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

	// File savers
	public void saveFromResources(String pathToFile) {
		FileSaver.saveFromResources(pathToFile, this.getPluginDir().getAbsolutePath(), false, this);
	}

	public void saveFromResources(String pathToFile, String pathToSave) {
		FileSaver.saveFromResources(pathToFile, pathToSave, false, this);
	}

	public void saveFromResources(String pathToFile, String pathToSave, boolean replace) {
		FileSaver.saveFromResources(pathToFile, pathToSave, replace, this);
	}

	// Finally checks
	private void finalChecks() {
		checker(apiEnabled,		"apiEnabled");
		checker(pluginID, 		"pluginID");
		checker(pluginName, 	"pluginName");
		checker(eventNode, 		"eventNode");
		checker(pluginDir, 		"pluginDir");
		checker(configFile, 	"configFile");
		checker(commandNode,	"commandNode");
		checker(pluginLogger,	"pluginLogger");
		checker(senderUtils,	"senderUtils");
		checker(commandPrefix, 	"commandPrefix");
		checker(messageList, 	"messageList");
	}

	private void checker(Object object, String whatIs) {
		if (object == null) getLogger().debug("{} not found in ({}) TopazPlugin#finalChecker", whatIs, pluginName);
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
