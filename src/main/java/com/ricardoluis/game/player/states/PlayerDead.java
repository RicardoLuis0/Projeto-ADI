package com.ricardoluis.game.player.states;

import java.util.ArrayList;

import com.ricardoluis.game.player.Player;
import com.ricardoluis.game.player.exceptions.PlayerCommandException;
import com.ricardoluis.game.world.WorldLocation;

public class PlayerDead extends PlayerState {
	
	public PlayerDead(Player parent) {
		super(parent);
	}

	@Override
	public boolean commandMove(int x,int y) {
		throw new PlayerCommandException("Player is not Alive");
	}

	@Override
	public ArrayList<ArrayList<WorldLocation>> commandLook(int range) {
		throw new PlayerCommandException("Player is not Alive");
	}

	@Override
	public String commandStatus() {
		return "{\"state\":\"Dead\"}";
	}

	@Override
	public void commandSpawn() {
		parent.setState(new PlayerAlive(parent), createToken());
	}

	@Override
	public String commandScan(int x, int y) {
		throw new PlayerCommandException("Player is not Alive");
	}

	@Override
	public void onDamage(int dmg) {
		//by default do nothing
	}
}
