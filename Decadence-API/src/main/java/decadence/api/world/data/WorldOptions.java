package decadence.api.world.data;

public interface WorldOptions {
	boolean gamemodeSwitcher();
	boolean entity();
	// Only for players
	boolean canMove();
	boolean placeBlocks();
	boolean brakeBlocks();
}
