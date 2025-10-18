package decadence.api.files.utils;

import decadence.api.plugin.Plugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Properties;

public class FileSaver {
	private static final Logger LOGGER = LoggerFactory.getLogger("FILE_SAVER");

	public static void saveFromResources(String resourcePath) {
		saveFromResources(resourcePath, new File(resourcePath), false);
	}

	public static void saveFromResources(String resourcePath, Plugin plugin) {
		saveFromResources(resourcePath, resourcePath, false, plugin);
	}

	public static void saveFromResources(String resourcePath, String outputPath, Plugin plugin) {
		saveFromResources(resourcePath, outputPath, false, plugin);
	}

	public static void saveFromResources(String resourcePath, File outputFile, Plugin plugin) {
		saveFromResources(resourcePath, outputFile.getAbsolutePath(), false, plugin);
	}

	public static void saveFromResources(String resourcePath, boolean replace, Plugin plugin) {
		saveFromResources(resourcePath, resourcePath, replace, plugin);
	}

	public static void saveFromResources(String resourcePath, String outputPath, boolean replace, Plugin plugin) {
		saveFromResources(resourcePath, new File(outputPath), replace, plugin);
	}

	private static InputStream getResourceAsStream(String resourcePath) {
		for (ClassLoader loader : getClassloaders()) {
			InputStream input = loader.getResourceAsStream(resourcePath);
			if (input != null) LOGGER.info("Found file {}... Saving", resourcePath);
			if (input != null) return input;
		}
		LOGGER.warn("File {} not found", resourcePath);
		return null;
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

	public static void saveFromResources(String resourcePath, File outputPath, boolean replace, Plugin plugin) {
		if (!replace && outputPath.exists()) return;
		URL resourceUrl = plugin.getClass().getClassLoader().getResource(resourcePath);
		if (resourceUrl == null) LOGGER.warn("File {} not found", resourcePath);
		if (resourceUrl == null) return;
		try (InputStream is = resourceUrl.openStream()) {
			Files.copy(is, outputPath.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			LOGGER.error("Cannot save {} in {}!", resourcePath, outputPath, e);
		}
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

	private static ClassLoader[] getClassloaders() {
		return new ClassLoader[]{
			FileSaver.class.getClassLoader(),
			Thread.currentThread().getContextClassLoader(),
			ClassLoader.getSystemClassLoader()
		};
	}
}
