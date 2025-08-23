package delta.cion.api.permissions;

import javax.naming.InvalidNameException;
import java.util.regex.Pattern;

public class PermissionValidator {

	private static final Pattern PATTERN = Pattern.compile("^[A-Za-z0-9._]+$");

	public static boolean validatePermission(String permission) throws InvalidNameException {
		if (permission == null || permission.isBlank()) throw new InvalidNameException("Permission name cannot be null!");
		if (PATTERN.matcher(permission).matches()) return true;
		throw new InvalidNameException("Permission contains invalid characters!");
	}

}
