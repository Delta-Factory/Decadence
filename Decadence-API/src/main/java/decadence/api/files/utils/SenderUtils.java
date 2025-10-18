package decadence.api.files.utils;

import decadence.api.files.configurations.FileConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class SenderUtils {

	private final Logger LOGGER = LoggerFactory.getLogger("SENDER-UTILS");

	private FileConfiguration messageList;

	public SenderUtils(File yamlConfig) {
		try {
			InputStream is = new FileInputStream(yamlConfig);
			this.messageList = new FileConfiguration(new Yaml().load(is));
		} catch (FileNotFoundException e) {
			LOGGER.error(e.toString());
		}
	}

	public final String getFromKey(String key) {
		return messageList.getString(key);
	}
}
