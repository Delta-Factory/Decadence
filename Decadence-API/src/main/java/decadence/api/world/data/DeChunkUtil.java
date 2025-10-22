package decadence.api.world.data;

import net.minestom.server.instance.Chunk;
import net.minestom.server.instance.IChunkLoader;
import net.minestom.server.instance.Instance;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

public class DeChunkUtil implements IChunkLoader {

	@Override
	public void loadInstance(@NotNull Instance instance) {
		IChunkLoader.super.loadInstance(instance);
	}

	@Override
	public @Nullable Chunk loadChunk(@NotNull Instance instance, int i, int i1) {
		return null;
	}

	@Override
	public void saveInstance(@NotNull Instance instance) {
		IChunkLoader.super.saveInstance(instance);
	}

	@Override
	public void saveChunk(@NotNull Chunk chunk) {

	}

	@Override
	public void saveChunks(@NotNull Collection<Chunk> chunks) {
		IChunkLoader.super.saveChunks(chunks);
	}

	@Override
	public boolean supportsParallelSaving() {
		return IChunkLoader.super.supportsParallelSaving();
	}

	@Override
	public boolean supportsParallelLoading() {
		return IChunkLoader.super.supportsParallelLoading();
	}

	@Override
	public void unloadChunk(Chunk chunk) {
		IChunkLoader.super.unloadChunk(chunk);
	}
}
