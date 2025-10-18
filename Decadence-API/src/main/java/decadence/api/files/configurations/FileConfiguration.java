<<<<<<<< HEAD:Decadence-API/src/main/java/decadence/api/files/yaml/FileConfiguration.java
package delta.cion.api.files.yaml;

import delta.cion.api.files.BaseConfiguration;
========
package decadence.api.files.configurations;
>>>>>>>> origin/dev-main:Decadence-API/src/main/java/decadence/api/files/configurations/FileConfiguration.java

import java.util.List;
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
		Object value = get(path);
		return convertToStringArray(value);
	}

	@Override
	public String[] getStringList(String path, String[] def) {
		Object value = get(path, def);
		if (value == null) return def;
		return convertToStringArray(value);
	}

	private String[] convertToStringArray(Object value) {
		if (value instanceof String) return new String[]{(String) value};
		if (value instanceof String[]) return (String[]) value;
		if (!(value instanceof List)) return new String[0];
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) value;
		return list.toArray(new String[0]);
	}

	@Override
	public Integer[] getIntList(String path) {
		Object value = get(path);
		return convertToIntegerArray(value);
	}

	@Override
	public Integer[] getIntList(String path, Integer[] def) {
		Object value = get(path, def);
		if (value == null) return def;
		return convertToIntegerArray(value);
	}

	private Integer[] convertToIntegerArray(Object value) {
		if (value instanceof Integer) return new Integer[]{(Integer) value};
		if (value instanceof Integer[]) return (Integer[]) value;
		if (!(value instanceof List)) return new Integer[0];
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) value;
		return list.toArray(new Integer[0]);
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
	public boolean is(String path, Class<?> objectClass) {
		Object obj = get(path);
		return objectClass.isInstance(obj);
	}
}
