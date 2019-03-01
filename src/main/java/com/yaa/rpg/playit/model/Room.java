package com.yaa.rpg.playit.model;

import java.io.Serializable;

public class Room implements Serializable{
	private static final long serialVersionUID = -8213889141699262822L;

	public GameCharacter getOccupiedBy() {
		return occupiedBy;
	}

	public void setOccupiedBy(GameCharacter you) {
		this.occupiedBy = you;
	}

	GameCharacter occupiedBy;
}
