package com.ricardoluis.game.player.exceptions;

public class PlayerCommandException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PlayerCommandException(String message) {
		super("{\"error\":\""+message+"\"}");
	}
	
}
