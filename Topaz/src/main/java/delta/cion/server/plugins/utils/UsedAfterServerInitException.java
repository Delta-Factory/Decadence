package delta.cion.server.plugins.utils;

public class UsedAfterServerInitException extends Exception {

	public UsedAfterServerInitException() {
		super("Class or method cant be used after server initialization!");
	}

}
