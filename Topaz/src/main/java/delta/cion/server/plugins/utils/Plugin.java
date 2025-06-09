package delta.cion.server.plugins.utils;

public interface Plugin {
	String id();
	String name();
	String mainClass();
	String[] depends();
	String[] softDepends();
}
