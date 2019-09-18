package com.ricardoluis.game.world.tiles;

public class StoneTile extends WorldTile {

	@Override
	public boolean isObstacle() {
		return false;
	}

	@Override
	public String getType() {
		return "Stone";
	}

}
