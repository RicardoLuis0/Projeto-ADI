package com.ricardoluis.game.command;

public class CommandArgument {
	private String argName;
	private Object argValue;
	
	public CommandArgument(){
		
	}
	
	public CommandArgument(String argName, Object argValue) {
		this();
		this.argName = argName;
		this.argValue = argValue;
	}

	public String getArgName() {
		return argName;
	}
	public void setArgName(String argName) {
		this.argName = argName;
	}
	public Object getArgValue() {
		return argValue;
	}
	public void setArgValue(Object argValue) {
		this.argValue = argValue;
	}
}
