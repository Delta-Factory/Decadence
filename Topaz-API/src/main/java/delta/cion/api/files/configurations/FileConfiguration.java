package delta.cion.api.files.configurations;

import java.util.Map;

public class FileConfiguration extends BaseConfiguration {

	protected Map<String, Object> values;

	public FileConfiguration(Map<String, Object> values) {
		this.values = values;
	}

	public static FileConfiguration load(Map<String, Object> values) {
		return new FileConfiguration(values);
	}

	@Override
	public Object get(String path) {
		return values.get(path);
	}

	@Override
	public Object get(String path, Object def) {
		return (get(path) == null) ? def : get(path);
	}

	@Override
	public String getString(String path) {
		return (String) get(path);
	}

	@Override
	public String getString(String path, String def) {
		return (String) get(path, def);
	}

	@Override
	public String[] getStringList(String path) {
		return (String[]) get(path);
	}

	@Override
	public String[] getStringList(String path, String[] def) {
		return (String[]) get(path, def);
	}

	@Override
	public int getInt(String path) {
		return (int) get(path);
	}

	@Override
	public int getInt(String path, int def) {
		return (int) get(path, def);
	}

	@Override
	public boolean getBoolean(String path) {
		return (boolean) get(path);
	}

	@Override
	public boolean getBoolean(String path, boolean def) {
		return (boolean) get(path, def);
	}

	@Override
	public boolean itIs(String path, Class<?> objectClass) {
		Object obj = get(path);
		return objectClass.isInstance(obj);
	}
}
