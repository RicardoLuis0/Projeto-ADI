package com.ricardoluis.game.player.states;

import java.util.ArrayList;

import com.ricardoluis.game.player.Player;
import com.ricardoluis.game.world.WorldManager;
import com.ricardoluis.game.world.tiles.WorldTile;

public class PlayerSpectating extends PlayerState {

	public PlayerSpectating(Player parent) {
		super(parent);
	}

	@Override
	public boolean move(int direction) {
		//spectators can noclip, always move
		switch(direction) {
		case 0://right
			return parent.moveXY(1, 0, createToken());
		case 1://down
			return parent.moveXY(0, 1, createToken());
		case 2://left
			return parent.moveXY(-1, 0, createToken());
		case 3://up
			return parent.moveXY(0, -1, createToken());
		default:
			throw new IllegalArgumentException("Direction must be between 0-3");
		}
	}

	@Override
	public ArrayList<ArrayList<WorldTile>> look(int range) {
		return WorldManager.getInstance().look(parent.getX(), parent.getY(), range);
	}

}
