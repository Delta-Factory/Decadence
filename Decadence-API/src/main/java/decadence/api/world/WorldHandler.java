package decadence.api.world;

import net.minestom.server.instance.InstanceContainer;

import java.util.HashMap;
import java.util.Map;

public class WorldHandler {

	private static final Map<String, InstanceContainer> WORLDS = new HashMap<>();

	public WorldHandler() {}

	public void registerNewWorld(String worldName, InstanceContainer container) {
		if (WORLDS.containsKey(worldName)) WORLDS.put(worldName, container);
	}

	public InstanceContainer getWorld(String worldName) {
		return WORLDS.get(worldName);
	}

	public void deleteWorld(String worldName) {
		WORLDS.remove(worldName);
	}

}
