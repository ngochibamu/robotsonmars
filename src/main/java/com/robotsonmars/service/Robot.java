package com.robotsonmars.service;

import java.util.Arrays;

public class Robot {
	
	private int xpos;
	private int ypos;
	private String cardinal;
	private char[] sequenceActions;
	public Robot(int xpos, int ypos, String cardinal, char[] sequenceActions) {
		super();
		this.xpos = xpos;
		this.ypos = ypos;
		this.cardinal = cardinal;
		this.sequenceActions = sequenceActions;
	}
	public int getXpos() {
		return xpos;
	}
	public void setXpos(int xpos) {
		this.xpos = xpos;
	}
	public int getYpos() {
		return ypos;
	}
	public void setYpos(int ypos) {
		this.ypos = ypos;
	}
	public String getCardinal() {
		return cardinal;
	}
	public void setCardinal(String cardinal) {
		this.cardinal = cardinal;
	}
	public char[] getSequenceActions() {
		return sequenceActions;
	}
	public void setSequenceActions(char[] sequenceActions) {
		this.sequenceActions = sequenceActions;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cardinal == null) ? 0 : cardinal.hashCode());
		result = prime * result + Arrays.hashCode(sequenceActions);
		result = prime * result + xpos;
		result = prime * result + ypos;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Robot other = (Robot) obj;
		if (cardinal == null) {
			if (other.cardinal != null)
				return false;
		} else if (!cardinal.equals(other.cardinal))
			return false;
		if (!Arrays.equals(sequenceActions, other.sequenceActions))
			return false;
		if (xpos != other.xpos)
			return false;
		if (ypos != other.ypos)
			return false;
		return true;
	}
	
	public String toString(){
		return "[x="+xpos+", ypos="+ypos+", cardinal="+cardinal+", sequence="+String.valueOf(sequenceActions)+"]";
		
	}
}
