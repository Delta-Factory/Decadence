package decadence.api.world.data;

public class DeWorldCitory implements DeWorldData {
	@Override
	public int MAGIC_NUMBER() {
		return 0x43747279;	}

	@Override
	public int WORLD_VERSION() {
		return 1;	}

	@Override
	public int ENTITY_VERSION() {
		return 1;	}

	@Override
	public int BLOCK_VERSION() {
		return 1;	}

	@Override
	public boolean saveWorld() {
		return true;	}

	@Override
	public boolean saveEntity() {
		return false;	}

	@Override
	public boolean savePlayersPosition() {
		return false;
	}

	@Override
	public boolean savePlayersInventories() {
		return false;
	}

	@Override
	public boolean saveBlocks() {
		return true;	}
}
