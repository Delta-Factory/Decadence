package decadence.api.placeholders;

public abstract class Placeholder {

	private final String PLACEHOLDER;
	private final String REPLACE;

	/**
	 * It`s placeholder object...
	 * @param name it`s %name% (can contains only [A-Za-z0-9_])
	 */
	public Placeholder(String name, String replace) {
		this.PLACEHOLDER = String.format("%%%s%%", name);
		this.REPLACE = replace;
	}

	public final String getPLACEHOLDER() {
		return this.PLACEHOLDER;
	}

	public final String getREPLACE() {
		return this.REPLACE;
	}
}
