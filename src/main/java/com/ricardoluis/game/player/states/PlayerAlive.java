package com.ricardoluis.game.player.states;

import java.util.ArrayList;

import com.ricardoluis.game.player.Player;
import com.ricardoluis.game.world.WorldManager;
import com.ricardoluis.game.world.tiles.WorldTile;

public class PlayerAlive extends PlayerState {

	public PlayerAlive(Player parent) {
		super(parent);
		var rand=new java.util.Random();
		while(true) {
			int rx=rand.nextInt(WorldManager.sizex);
			int ry=rand.nextInt(WorldManager.sizey);
			if(parent.moveXY(rx, ry, createToken()))break;//spawn in random location
		}
	}
	
	private boolean tryMove(int x,int y) {
		if(!WorldManager.getInstance().getXY(parent.getX()+x, parent.getY()+y).isObstacle()) {
			return parent.moveXY(x, y, createToken());
		}
		return false;
	}

	@Override
	public boolean move(int direction) {
		switch(direction) {
		case 0://up
			return tryMove(0, -1);
		case 1://down
			return tryMove(0, 1);
		case 2://left
			return tryMove(-1, 0);
		case 3://right
			return tryMove(1, 0);
		default:
			throw new IllegalArgumentException("Direction must be between 0-3");
		}
	}

	@Override
	public String getStatus() {
		return "Alive";
	}

	@Override
	public ArrayList<ArrayList<WorldTile>> look(int range) {
		return WorldManager.getInstance().look(parent.getX(), parent.getY(), range);
	}

}
