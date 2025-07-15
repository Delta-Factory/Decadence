package delta.cion.api.files.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;

public class FileSaver {

	private static final Logger LOGGER = LoggerFactory.getLogger("FILE_SAVER");

	//laps1
	public static void saveFromResources(String pathToFile) {
		LOGGER.info("Saving {} with #laps1", pathToFile);
		save(pathToFile, pathToFile, false);
	}

	//laps2
	public static void saveFromResources(String pathToFile, String pathToSave) {
		LOGGER.info("Saving {} with #laps2", pathToFile);
		save(pathToFile, pathToSave, false);
	}

	//laps2.5
	public static void saveFromResources(String pathToFile, File pathToSave) {
		LOGGER.info("Saving {} with #laps2.5", pathToFile);
		String pathToSave1 = pathToSave.getAbsolutePath();
		save(pathToFile, pathToSave1, false);
	}

	//laps3
	public static void saveFromResources(String pathToFile, boolean replaceFile) {
		LOGGER.info("Saving {} with #laps3", pathToFile);
		save(pathToFile, pathToFile, replaceFile);
	}

	//laps4
	public static void saveFromResources(String pathToFile, String pathToSave, boolean replaceFile) {
		LOGGER.info("Saving {} with #laps4", pathToFile);
		save(pathToFile, pathToSave, replaceFile);
	}

	//laps5
	private static void save(String pathToFile, String pathToSave, boolean replaceFile) {
		LOGGER.info("Saving {} with #laps5", pathToFile);
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

	//laps6
	public static Properties loadProperties(String path) {
		try (InputStream is = new FileInputStream(path)) {
			Properties properties = new Properties();
			properties.load(is);
			return properties;
		} catch (Exception e) {
			return null;
		}
	}

	//laps7
	private static boolean fileExists(File file) {
		if (!file.exists()) return false;
		LOGGER.info("File {} is exists!", file.getName());
		return true;
	}

}
