package delta.cion.api.files.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;

public class FileSaver {
	private static final Logger LOGGER = LoggerFactory.getLogger("FILE_SAVER");

	public static void saveFromResources(String resourcePath) {
		saveFromResources(resourcePath, resourcePath, false);
	}

	public static void saveFromResources(String resourcePath, String outputPath) {
		saveFromResources(resourcePath, outputPath, false);
	}

	public static void saveFromResources(String resourcePath, File outputFile) {
		saveFromResources(resourcePath, outputFile.getAbsolutePath(), false);
	}

	public static void saveFromResources(String resourcePath, boolean replace) {
		saveFromResources(resourcePath, resourcePath, replace);
	}

	public static void saveFromResources(String resourcePath, String outputPath, boolean replace) {
		saveFromResources(resourcePath, new File(outputPath), replace);
	}

	public static void saveFromResources(String resourcePath, File outputFile, boolean replace) {
		if (!replace && outputFile.exists()) return;

		File parentDir = outputFile.getParentFile();
		if (parentDir != null && !parentDir.exists()) {
			if (!parentDir.mkdirs()) return;
		}

		try (InputStream inputStream = getResourceAsStream(resourcePath)) {
			if (inputStream == null) return;

			try (OutputStream outputStream = new FileOutputStream(outputFile)) {
				byte[] buffer = new byte[4096];
				int bytesRead;
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, bytesRead);
				}
			}
		} catch (IOException e) {
			LOGGER.error("Saving error for {} in {}!", resourcePath, outputFile, e);
		}
	}

	private static InputStream getResourceAsStream(String resourcePath) {
		InputStream inputStream = FileSaver.class.getClassLoader().getResourceAsStream(resourcePath);
		if (inputStream == null) return Thread.currentThread().getContextClassLoader().getResourceAsStream(resourcePath);
		return inputStream;
	}

	public static Properties loadProperties(String path) {
		File file = new File(path);
		if (!file.exists()) return null;

		try (InputStream is = new FileInputStream(file)) {
			Properties properties = new Properties();
			properties.load(is);
			return properties;
		} catch (Exception e) {
			LOGGER.error("Cannot load property {}!", path, e);
			return null;
		}
	}
}
