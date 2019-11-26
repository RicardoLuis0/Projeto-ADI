package com.ricardoluis.game.world;

import com.ricardoluis.game.player.Player;
import com.ricardoluis.game.world.tiles.WorldTile;

public class WorldLocation {
	public WorldLocation(WorldTile tile) {
		this.tile=tile;
	}
	public WorldTile tile;
	public Player player;
}
