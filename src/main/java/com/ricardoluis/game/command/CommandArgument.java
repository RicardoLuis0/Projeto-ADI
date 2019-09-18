package com.ricardoluis.game.command;

public class CommandArgument {

	private String name;

	private String value;
	
	@Override
	public String toString() {
		return name+"="+value;
	}

	public CommandArgument(){
		
	}

	public CommandArgument(String name, String value) {
		this();
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
