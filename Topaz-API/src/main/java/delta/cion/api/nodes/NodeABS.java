package delta.cion.api.nodes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

abstract class NodeABS<T> {

    private final ArrayList<T> OBJECTS;
    private final Map<String, ArrayList<T>> NODES;

    private final String NODE_ID;

    private final Logger LOGGER = LoggerFactory.getLogger("NODE_HANDLER");

    public NodeABS(String nodeID) {
        NODE_ID = nodeID;
        OBJECTS = new ArrayList<>();
        NODES = new HashMap<>();
    }

	public final NodeABS<T> addToNode(T object) {
		if (!OBJECTS.contains(object)) OBJECTS.add(object);
		if (NODES.containsKey(NODE_ID)) NODES.replace(NODE_ID, OBJECTS);
		return this;
	}

	public final NodeABS<T> addToNode(T... object) {
		for (T o : object) addToNode(o);
		return this;
	}

	public final NodeABS<T> removeFromNode(T object) {
		OBJECTS.remove(object);
		if (NODES.containsKey(NODE_ID)) NODES.replace(NODE_ID, OBJECTS);
		return this;
	}

	public final NodeABS<T> removeFromNode(T... object) {
		for (T o : object) removeFromNode(o);
		return this;
	}

	public final NodeABS<T> removeFromNode(T object, boolean autoUpdate) {
		removeFromNode(object);
		if (autoUpdate) updateNode();
		return this;
	}

	public final NodeABS<T> addToNode(T object, boolean autoUpdate) {
        addToNode(object);
        if (autoUpdate) updateNode();
		return this;
    }

	public ArrayList<T> getObjects() {
		return this.OBJECTS;
	}

	public abstract void registerNodeObjects();

	public abstract void unregisterNodeObjects();

	public final void registerNode() {
		this.registerNodeObjects();
		if (!NODES.containsKey(NODE_ID)) NODES.put(NODE_ID, OBJECTS);
		else LOGGER.warn("Node {} already registered!", NODE_ID);
	}

	public final void updateNode() {
		this.unregisterNodeObjects();
		this.registerNodeObjects();
		if (NODES.containsKey(NODE_ID)) NODES.replace(NODE_ID, OBJECTS);
		else NODES.put(NODE_ID, OBJECTS);
	}

}
