package delta.cion.api.files.utils;

import delta.cion.api.files.configurations.FileConfiguration;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class Sender {

	private static FileConfiguration messageList;

	public final String getFromKey(String key) {
		return messageList.getString(key);
	}

	public static void setMessageList(File file) throws FileNotFoundException {
		InputStream is = new FileInputStream(file);
		messageList = new FileConfiguration(new Yaml().load(is));
	}
}
