package com.ricardoluis.game.player.states;

import java.util.ArrayList;

import com.ricardoluis.game.player.Player;
import com.ricardoluis.game.player.exceptions.PlayerCommandException;
import com.ricardoluis.game.world.WorldManager;
import com.ricardoluis.game.world.tiles.WorldTile;

public class PlayerSpectating extends PlayerDead {

	public PlayerSpectating(Player parent) {
		super(parent);
	}

	@Override
	public boolean commandMove(int x,int y) {
		//spectators can noclip, don't check collision
		return parent.moveXY(x, y, false, createToken());
	}

	@Override
	public ArrayList<ArrayList<WorldTile>> commandLook(int range) {
		return WorldManager.getInstance().look(parent.getX(), parent.getY(), range);
	}

	@Override
	public String commandStatus() {
		return "{\"state\":\"Spectating\"}";
	}

	@Override
	public String commandScan(int x, int y) {
		throw new PlayerCommandException("Unimplemented");
	}

}
