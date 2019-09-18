package com.ricardoluis.game.world.tiles;

public class GrassTile extends WorldTile {

	@Override
	public boolean isObstacle() {
		return false;
	}

	@Override
	public String getType() {
		return "Grass";
	}

}
