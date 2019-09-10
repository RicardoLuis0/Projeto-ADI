package com.ricardoluis.game.command;

import java.util.ArrayList;

public class Command {
	private String command;
	private ArrayList<CommandArgument> args;
	
	public Command() {
		
	}
	
	public Command(String command, ArrayList<CommandArgument> args) {
		this();
		this.command = command;
		this.args = args;
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
}
