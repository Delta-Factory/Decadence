package delta.cion.api.placeholders;

public final class PlaceholderHandler {

	public String replace(String string, Placeholder placeholder) {
		return string.replaceAll(placeholder.getPLACEHOLDER(), placeholder.getREPLACE());
	}

}
