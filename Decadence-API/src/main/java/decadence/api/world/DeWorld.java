package decadence.api.world;

import net.minestom.server.instance.IChunkLoader;

public interface DeWorld {
	String worldName();
	IChunkLoader chunkLoader();
}
