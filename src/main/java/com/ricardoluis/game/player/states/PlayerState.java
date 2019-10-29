package com.ricardoluis.game.player.states;

import java.util.ArrayList;

import com.ricardoluis.game.player.Player;
import com.ricardoluis.game.world.tiles.WorldTile;

public abstract class PlayerState {
	protected final Player parent;
	public PlayerState(Player parent){
		this.parent=parent;
	}
	public class PlayerStateToken{
		private PlayerStateToken() {
			
		}
	}
	
	public abstract boolean move(int direction);
	public abstract ArrayList<ArrayList<WorldTile>> look(int range);
	public abstract String getStatus();

	protected PlayerStateToken createToken() {
		return new PlayerStateToken();
	}

}
