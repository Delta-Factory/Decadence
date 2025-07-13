package delta.cion.api.nodes;

import delta.cion.api.placeholders.Placeholder;
import net.minestom.server.command.builder.Command;

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
