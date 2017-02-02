package com.robotsonmars.util;

public enum Command {
	L("L"), R("R"), M("M");

	private final String action;

	private Command(final String a){
		action = a;
	}

	public String toString(){
		return action;
	}
}