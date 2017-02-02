package com.robotsonmars.util;

public enum CardinalPoint {
	NORTH("N"), SOUTH("S"), EAST("E"), WEST("W");

	private String cardinal;

	private CardinalPoint(String direction){
		this.cardinal = direction;
	}

	public String value(){
		return this.cardinal;
	}

	public void setCardinal(String direction){
		this.cardinal = direction;
	}
}
