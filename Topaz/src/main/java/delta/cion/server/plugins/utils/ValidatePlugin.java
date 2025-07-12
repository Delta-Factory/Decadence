package delta.cion.server.plugins.utils;

import delta.cion.api.files.configurations.FileConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ValidatePlugin {

	private final Logger LOGGER = LoggerFactory.getLogger("MODULE_CHECKS");

	private final File pluginJar;
	private final FileConfiguration DATA;

	private String id;
	private String name;
	private String mainClass;
	private String[] depends;
	private String[] softDepends;
	private String commandPrefix;
	private boolean enableMsgList;
	private boolean enableApi;

	public ValidatePlugin(File pluginJar) {
		this.pluginJar = pluginJar;
		this.DATA = loadYaml();
	}

	public Plugin getPlugin() {
		if (DATA == null) return isFalse("plugin.yml not found");
		this.id = DATA.getString("plugin-id");
		this.name = DATA.getString("plugin-name");
		this.depends = DATA.getStringList("depends");
		this.mainClass = DATA.getString("main-class");
		this.softDepends = DATA.getStringList("soft-depends");
		this.commandPrefix = DATA.getString("command-prefix");
		this.enableMsgList = DATA.getBoolean("message-list");
		this.enableApi = DATA.getBoolean("contains-api");

		if (check(id)) return isFalse("Plugin id is null!");
		if (check(name)) return isFalse("Plugin name is null!");
		if (check(mainClass)) return isFalse("Plugin main-class is null!");

		return buildPlugin();
	}

	private FileConfiguration loadYaml() {
		try (JarFile jar = new JarFile(pluginJar)) {
			JarEntry entry = new JarEntry(jar.getEntry("plugin.yml"));
			try (InputStream stream = jar.getInputStream(entry)) {
				return FileConfiguration.load(new Yaml().load(stream));}
		} catch (IOException e) {
			LOGGER.error(e.toString());
			return null;
		}
	}

	private Plugin buildPlugin() {
		return new PluginRecord(id, name, mainClass, depends, softDepends, commandPrefix, enableMsgList, enableApi);
	}

	private boolean check(String data) {
		return data == null || data.isBlank();
	}

	private Plugin isFalse(String message) {
		LOGGER.error(message);
		return null;
	}

}
