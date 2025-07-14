package delta.cion.api.files.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;

public class FileSaver {

	private static final Logger LOGGER = LoggerFactory.getLogger("FILE_SAVER");

	public static void saveFromResources(String pathToFile) {
		save(pathToFile, "./", false);
	}

	public static void saveFromResources(String pathToFile, String pathToSave) {
		save(pathToFile, pathToSave, false);
	}

	public static void saveFromResources(String pathToFile, boolean replaceFile) {
		save(pathToFile, pathToFile, replaceFile);
	}

	public static void saveFromResources(String pathToFile, String pathToSave, boolean replaceFile) {
		save(pathToFile, pathToSave, replaceFile);
	}

	private static void save(String pathToFile, String pathToSave, boolean replaceFile) {
		if (!replaceFile && fileExists(new File(pathToFile))) return;

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		try (InputStream inputStream = classLoader.getResourceAsStream(pathToFile)) {
			if (inputStream == null) LOGGER.error("File {} not found!", pathToFile);
			if (inputStream == null) return;
			try (OutputStream outputStream = new FileOutputStream(pathToSave)) {
				byte[] buffer = new byte[4096];
				int n;
				while ((n = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, n);
				}
			}
		} catch (IOException e) {
			LOGGER.error(e.toString());
		}

	}

	public static Properties loadProperties(String path) {
		try (InputStream is = new FileInputStream(path)) {
			Properties properties = new Properties();
			properties.load(is);
			return properties;
		} catch (Exception e) {
			return null;
		}
	}

	private static boolean fileExists(File file) {
		if (!file.exists()) return false;
		LOGGER.info("File {} is exists!", file.getName());
		return true;
	}

}
