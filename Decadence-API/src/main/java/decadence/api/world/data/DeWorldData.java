package decadence.api.world.data;

public interface DeWorldData {
	// World format
	// U cant load world without required format
	int MAGIC_NUMBER(); // 0x43747279 - Ctry

	// Data versions
	// World (Generator)
	// Entity (Data components and other for mobs)
	// Blocks (Like entity)
	int WORLD_VERSION();
	int ENTITY_VERSION();
	int BLOCK_VERSION();

	// If disabled - U can only load mobs, blocks and world (Maybe it need for lobby (IDK))
	boolean saveWorld();
	boolean saveEntity();
	// Positions
	boolean savePlayersPosition();
	// Inventories
	boolean savePlayersInventories();
	// If disabled - Saving only blocks with position (No inventories or special components)
	boolean saveBlocks();
}
