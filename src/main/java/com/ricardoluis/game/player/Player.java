package com.ricardoluis.game.player;

import java.util.ArrayList;

import com.ricardoluis.eventhandling.Event;
import com.ricardoluis.eventhandling.EventHandler;
import com.ricardoluis.game.player.events.DamageEvent;
import com.ricardoluis.game.player.exceptions.PlayerCommandException;
import com.ricardoluis.game.player.states.PlayerSpectating;
import com.ricardoluis.game.player.states.PlayerState;
import com.ricardoluis.game.player.states.PlayerState.PlayerStateToken;
import com.ricardoluis.game.world.WorldManager;
import com.ricardoluis.game.world.tiles.WorldTile;

public class Player implements EventHandler {
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
	
	public void setState(PlayerState state, PlayerStateToken token) {//state can only be changed by a state
		this.state = state;
	}

	/**
	 * Relatively move player, method can only be called by a state
	 * @param x relative x-position
	 * @param y relative y-position
	 * @param collide whether to collide
	 * @param token control token
	 * @return
	 */
	public boolean moveXY(int x,int y,boolean collide, PlayerStateToken token) {
		if(WorldManager.getInstance().validXY(this.x+x,this.y+y,collide)) {
			this.x=this.x+x;
			this.y=this.y+y;
			return true;
		}
		return false;
	}

	/**
	 * Set Player Position, method can only be called by a state
	 * @param x x-position
	 * @param y y-position
	 * @param token control token
	 * @return
	 */
	public void setXY(int x,int y, PlayerStateToken token) {//player can only be moved by a state
		this.x = x;
		this.y = y;
	}

	/**
	 * Set Player X Position, method can only be called by a state
	 * @param x x-position
	 * @param token control token
	 * @return
	 */
	public void setX(int x, PlayerStateToken token) {//player can only be moved by a state
		this.x = x;
	}

	/**
	 * Set Player Y Position, method can only be called by a state
	 * @param y y-position
	 * @param token control token
	 * @return
	 */
	public void setY(int y, PlayerStateToken token) {//player can only be moved by a state
		this.y = y;
	}

	public boolean move(String direction) {
		switch(direction) {
		case "up":
			return state.commandMove(-1,0);
		case "down":
			return state.commandMove(1,0);
		case "left":
			return state.commandMove(0,-1);
		case "right":
			return state.commandMove(0,1);
		default:
			throw new PlayerCommandException("Invalid Direction");
		}
	}
	
	public String getStatus() {
		return state.commandStatus();
	}
	
	public ArrayList<ArrayList<WorldTile>> look(int range){
		return state.commandLook(range);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public void handleEvent(Event e) {
		if(e instanceof DamageEvent) {
			state.onDamage(((DamageEvent)e).damage);
		}
	}

	public void spawn() {
		state.commandSpawn();
	}

	public String scan(int x, int y) {
		// TODO Auto-generated method stub
		return state.commandScan(x, y);
	}
}
