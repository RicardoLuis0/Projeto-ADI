package com.ricardoluis.game.player;

import java.util.ArrayList;
import java.util.function.Consumer;

public class PlayerManager {
	static private PlayerManager manager=new PlayerManager();

	private ArrayList<Player> players;


	private PlayerManager() {
		players= new ArrayList<>();
	}

	public static PlayerManager getInstance() {
		return manager;
	}

	public void forEach(Consumer<? super Player> action) {
		players.forEach(action);
	}

	public boolean playerExists(String name) {
		for(Player p:players) {
			if(p.getName().equals(name))return true;
		}
		return false;
	}

	public Player findPlayer(String name) {
		for(Player p:players) {
			if(p.getName().equals(name))return p;
		}
		return null;
	}

	public boolean registerPlayer(String name) {
		if(!playerExists(name)) {
			players.add(new Player(name));
			return true;
		}else {
			return false;
		}
	}

}
