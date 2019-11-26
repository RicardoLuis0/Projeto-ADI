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
	
	public abstract boolean commandMove(int x,int y);
	public abstract String commandScan(int x,int y);
	public abstract ArrayList<ArrayList<WorldTile>> commandLook(int range);
	public abstract String commandStatus();
	public abstract void commandSpawn();
	public abstract void onDamage(int dmg);

	protected PlayerStateToken createToken() {
		return new PlayerStateToken();
	}

}
