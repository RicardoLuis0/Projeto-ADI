package com.ricardoluis.game.command;

import java.util.ArrayList;

public class Command {
	private String command;
	private ArrayList<CommandArgument> args;
	private String playerName;
	
	@Override
	public String toString() {
		return "("+playerName+") "+"command: "+command+"\nargs: "+args;
	}
	
	public Command() {
		
	}

	public Command(String command, ArrayList<CommandArgument> args, String playerName) {
		this.command = command;
		this.args = args;
		this.playerName = playerName;
	}

	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public ArrayList<CommandArgument> getArgs() {
		return args;
	}
	public void setArgs(ArrayList<CommandArgument> args) {
		this.args = args;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
}
