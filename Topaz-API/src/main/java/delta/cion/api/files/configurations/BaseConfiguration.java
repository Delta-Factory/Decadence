package delta.cion.api.files.configurations;

public abstract class BaseConfiguration {

	public abstract Object get(String path);
	public abstract Object get(String path, Object def);

	public abstract String getString(String path);
	public abstract String getString(String path, String def);

	public abstract String[] getStringList(String path);
	public abstract String[] getStringList(String path, String[] def);

	public abstract int getInt(String path);
	public abstract int getInt(String path, int def);

	public abstract boolean getBoolean(String path);
	public abstract boolean getBoolean(String path, boolean def);

	public abstract boolean itIs(String path, Class<?> object);

}
