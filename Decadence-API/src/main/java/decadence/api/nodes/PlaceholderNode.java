package decadence.api.nodes;

import decadence.api.placeholders.Placeholder;

import java.util.ArrayList;

public final class PlaceholderNode extends NodeABS<Placeholder> {

	private final ArrayList<Placeholder> PLACEHOLDERS;

	public PlaceholderNode(String nodeID) {
		super(nodeID);
		this.PLACEHOLDERS = new ArrayList<>();
	}

	@Override
	public void registerNodeObjects() {

	}

	@Override
	public void unregisterNodeObjects() {

	}
}
