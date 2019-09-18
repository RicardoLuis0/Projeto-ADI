package com.ricardoluis.game.world.tiles;
public class WallTile extends WorldTile {

	@Override
	public boolean isObstacle() {
		return true;
	}

	@Override
	public String getType() {
		return "Wall";
	}

}
