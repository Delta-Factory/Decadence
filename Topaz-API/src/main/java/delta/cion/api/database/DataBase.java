package delta.cion.api.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DataBase implements AutoCloseable {

	private final Logger LOGGER = LoggerFactory.getLogger("DATABASE");

	private enum TYPE {FILE, SERVER};

	private final TYPE CONNECTION_TYPE;

	private final String URL;
	private final String USER;
	private final String PASSWORD;

	private Connection connection;

	public DataBase(String path) {
		this.CONNECTION_TYPE = TYPE.FILE;

		if (new File(path).mkdirs())
			LOGGER.info("Dirs for database created! [{}]", path);

		this.USER = null;
		this.PASSWORD = null;
		this.URL = "jdbc:sqlite:" + path;
	}

	public DataBase(String url, String user, String password) {
		this.CONNECTION_TYPE = TYPE.SERVER;

		this.URL = url;
		this.USER = user;
		this.PASSWORD = password;
	}

	private void connect() {
		try {
			if (CONNECTION_TYPE == TYPE.FILE) this.connection = DriverManager.getConnection(this.URL);
			else this.connection = DriverManager.getConnection(this.URL, this.USER, this.PASSWORD);
		} catch (SQLException e) { LOGGER.error("Cannot connect to DataBase:", e); }
	}

	@Deprecated
	public Connection getConnection() {
		LOGGER.warn("You try to get a connection but its unsafe! Please use a standard database utils!");
		if (this.connection != null) return this.connection;
		LOGGER.error("Cannot connect to DataBase!");
		return null;
	}

	@Deprecated
	public boolean execute(String command) throws SQLException {
		LOGGER.warn("THIS IS UNSAFE METHOD! #DataBase.execute()");
		return connection.createStatement().execute(command);
	}

	public boolean createField() {}

	public boolean deleteField() {}

	public boolean execToField(String field, String... objects) {}

	public boolean removeFromField() {}

	public boolean createTable() {}

	public boolean deleteTable() {}


	@Override
	public void close() {
		if (this.connection != null) try {
			this.connection.close();
		} catch (SQLException e) {
			LOGGER.error("Cannot close a connection!",e);
		}
	}

}
