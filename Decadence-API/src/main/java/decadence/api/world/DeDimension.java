package decadence.api.world;

import net.minestom.server.instance.IChunkLoader;

public interface DeDimension {
	String dimensionName();
	IChunkLoader chunkLoader();
}
