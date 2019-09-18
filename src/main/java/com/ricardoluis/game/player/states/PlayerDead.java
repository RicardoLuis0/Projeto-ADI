package com.ricardoluis.game.player.states;

import java.util.ArrayList;

import com.ricardoluis.game.player.Player;
import com.ricardoluis.game.world.tiles.WorldTile;

public class PlayerDead extends PlayerState {

	public PlayerDead(Player parent) {
		super(parent);
	}

	@Override
	public boolean move(int direction) {
		return false;//cannot move while dead
	}

	@Override
	public ArrayList<ArrayList<WorldTile>> look(int range) {
		return null;
	}

}
