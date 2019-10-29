package com.ricardoluis.game.player;

import java.util.ArrayList;

import com.ricardoluis.game.player.states.PlayerSpectating;
import com.ricardoluis.game.player.states.PlayerState;
import com.ricardoluis.game.player.states.PlayerState.PlayerStateToken;
import com.ricardoluis.game.world.WorldManager;
import com.ricardoluis.game.world.tiles.WorldTile;

public class Player {
	private String name;
	private PlayerState state;
	private int x;
	private int y;

	@FunctionalInterface
	private interface MovePredicate {
		boolean canMove();
	}

	public Player(String name) {
		this.name = name;
		this.state= new PlayerSpectating(this);
		this.x=0;
		this.y=0;
	}

	public String getName() {
		return name;
	}
	
	public void setState(PlayerState state) {
		this.state = state;
	}

	public boolean move(int direction) {
		return state.move(direction);
	}
	
	public String getStatus() {
		return state.getStatus();
	}
	
	public ArrayList<ArrayList<WorldTile>> look(int range){
		return state.look(range);
	}

	public boolean moveXY(int x,int y, PlayerStateToken token) {
		if(WorldManager.getInstance().validXY(this.x+x,this.y+y)) {
			this.x=this.x+x;
			this.y=this.y+y;
			return true;
		}
		return false;
	}

	public int getX() {
		return x;
	}

	public void setX(int x, PlayerStateToken token) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y, PlayerStateToken token) {
		this.y = y;
	}
}
