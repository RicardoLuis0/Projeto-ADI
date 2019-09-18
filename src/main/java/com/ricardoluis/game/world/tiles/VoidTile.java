package com.ricardoluis.game.world.tiles;

public class VoidTile extends WorldTile {

	@Override
	public boolean isObstacle() {
		return true;
	}

	@Override
	public String getType() {
		return "Void";
	}

}
