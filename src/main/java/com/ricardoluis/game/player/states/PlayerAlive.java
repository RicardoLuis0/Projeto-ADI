package com.ricardoluis.game.player.states;

import com.ricardoluis.game.player.Player;
import com.ricardoluis.game.player.exceptions.PlayerCommandException;
import com.ricardoluis.game.world.WorldManager;

public class PlayerAlive extends PlayerSpectating {

	int health;

	public PlayerAlive(Player parent) {
		super(parent);
		health=100;
		var rand=new java.util.Random();
		while(true) {
			int rx=rand.nextInt(WorldManager.sizex);
			int ry=rand.nextInt(WorldManager.sizey);
			if(parent.moveXY(rx, ry, true, createToken()))break;//spawn in random location
		}
	}

	@Override
	public boolean commandMove(int x,int y) {
		return parent.moveXY(x, y, true, createToken());
	}

	@Override
	public String commandStatus() {
		return "{\"state\":\"Alive\",\"health\":"+health+"}";
	}

	@Override
	public void onDamage(int dmg) {
		health-=dmg;
		if(health<1) {
			parent.setState(new PlayerDead(parent),createToken());
		}
	}

	@Override
	public void commandSpawn() {
		throw new PlayerCommandException("Player has Already Spawned");
	}

}
