package decadence.api.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.regex.Pattern;

public final class DataBase implements AutoCloseable {

	private final Logger LOGGER = LoggerFactory.getLogger("DATABASE");

	private enum ConnectionType {FILE, SERVER};

	private static final Pattern SQL_INJECTION_PATTERN = Pattern.compile(
		"(?i)\\b(INSERT|SELECT|DELETE|UPDATE|DROP|ALTER|EXEC|UNION)\\b|[';`]|--"
	);

	private final ConnectionType CONNECTION_TYPE;

	private final String URL;
	private final String USER;
	private final String PASSWORD;

	private Connection connection;

	public DataBase(String path) {
		this.CONNECTION_TYPE = ConnectionType.FILE;

		if (new File(path).mkdirs())
			LOGGER.info("Dirs for database created! [{}]", path);

		this.USER = null;
		this.PASSWORD = null;
		this.URL = "jdbc:sqlite:" + path;
		this.connect();
	}

	public DataBase(String url, String user, String password) {
		this.CONNECTION_TYPE = ConnectionType.SERVER;

		this.URL = url;
		this.USER = user;
		this.PASSWORD = password;
		this.connect();
	}

	private void connect() {
		try {
			if (CONNECTION_TYPE == ConnectionType.FILE) this.connection = DriverManager.getConnection(this.URL);
			else this.connection = DriverManager.getConnection(this.URL, this.USER, this.PASSWORD);
		} catch (SQLException e) { LOGGER.error("Cannot connect to DataBase:", e); }
	}


	/**
	 * PLEASE DON`T USE THIS METHOD!
	 * THIS IS UNSAFE
	 * @return DataBase Connection
	 */
	@Deprecated
	public Connection getConnection() {
		LOGGER.warn("You try to get a connection but its unsafe! Please use a standard database utils!");
		if (this.connection != null) return this.connection;
		LOGGER.error("Cannot connect to DataBase!");
		return null;
	}

	private boolean execute(String command) {
		LOGGER.debug(command);
		try { return connection.prepareStatement(command).execute(); }
		catch (SQLException e) {
			LOGGER.error("Cannot execute request!", e);
			return false;
		}
	}

	private boolean execute(String command, String... args) {
		if (command.contains("%s") && args.length < 1) {
			LOGGER.error("You use {} but args is null!", command);
			return false;
		}
		return execute(String.format(command, (Object) args));
	}

	// Start of requests

	public boolean createTable() { return false; }
	public boolean renameTable() { return false; }
	public boolean deleteTable() { return false; }

	public boolean createColumn() { return false; }
	public boolean renameColumn() { return false; }
	public boolean deleteColumn() { return false; }

	public boolean createString() { return false; }
	public boolean renameString() { return false; }
	public boolean deleteString() { return false; }

	public Object[] getString() { return null; }
	public Object[] getFromString() { return null; }

	// End of requests

	private boolean validateData(String... params) {
		if (params == null || params.length < 1) return false;
		return Arrays.stream(params).allMatch(this::validateData);
	}

	private boolean validateData(String param) {
		if (SQL_INJECTION_PATTERN.matcher(param).find()) return true;
		LOGGER.error("Detected invalid param: [{}]", param);
		return false;
	}

	@Override
	public void close() {
		if (this.connection != null) try {
			this.connection.close();
		} catch (SQLException e) {
			LOGGER.error("Cannot close a connection!",e);
		}
	}

}
