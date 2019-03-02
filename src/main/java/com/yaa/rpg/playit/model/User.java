package com.yaa.rpg.playit.model;

import java.io.Serializable;
import com.yaa.rpg.playit.service.UIservice;
import com.yaa.rpg.playit.utils.Constants;

public class User  implements Serializable{
	private static final long serialVersionUID = -7247309926635523852L;

	private String name;
	private Palace map;
	private Integer level;
	private Integer gems;
	private Integer health;
	transient UIservice ui;
	
    private int attackDmg = 25;
	private int numHealthPots = 3;
	private int healthPotionHealAmount = 20;
	private int healthPotionDropChance = 10;

	@Override
	public String toString() {
		return this.getName() + "'s Health : " + this.getHealth() + " Gems: " + this.getGems();
	}
	
	public User(String name, int level,int gems, int health, Palace palace) {
		this.name = name;
		this.level=level;
		this.gems = gems;
		this.health = health;
		this.map = palace;
	}



	public User(String name) {
		this.name = name;
		this.level = 1;
		this.gems = 0;
		this.health=100;
		this.map = new Palace();
	}

	public boolean explore() {
		map.print();
		ui.printInstructions();
		String cmd = ui.readUserInputString();
		if (cmd.equalsIgnoreCase("QUIT") || cmd.equalsIgnoreCase("Q")) {
			return true;
		} else {
			switch (cmd.toUpperCase()) {
				case "NORTH":
				case "N":
					moveNorth();
					break;
				case "SOUTH":
				case "S":
					moveSouth();
					break;
				case "WEST":
				case "W":
					moveWest();
					break;
				case "EAST":
				case "E":
					moveEast();
					break;
				default:
					ui.displayExploreError();
			}
		}

		System.out.flush();
		return false;
	}

	private void moveNorth() {
		if (map.getCurrentRoomX() == 0) {
			ui.hitWallMessage();
		} else {
			map.IncOrDecCurrentRoomX(-1);
		}

	}

	private void moveSouth() {
		if (map.getCurrentRoomX() == Constants.rows - 1) {
			ui.hitWallMessage();
		} else {
			map.IncOrDecCurrentRoomX(1);
		}

	}

	private void moveWest() {
		if (map.getCurrentRoomY() == 0) {
			ui.hitWallMessage();
		} else {
			map.IncOrDecCurrentRoomY(-1);
		}

	}

	private void moveEast() {
		if (map.getCurrentRoomY() == Constants.columns - 1) {
			ui.hitWallMessage();
		} else {
			map.IncOrDecCurrentRoomY(1);
		}
	}

	
	
	public void modifyHealth(int delta) {
		health += delta;
		if(health<0)
			health=0;
	}

	public void modifyGems(int number) {
		gems += number;
	}

	public String getName() {
		return name;
	}

	public Palace getMap() {
		return map;
	}

	public void setMap(Palace map) {
		this.map = map;
	}

	public Integer getLevel() {
		return level;
	}

	public void IncLevel() {
		this.level++;
	}

	public Integer getGems() {
		return gems;
	}

	public Integer getHealth() {
		return health;
	}

	public void setUI(UIservice ui) {
		this.ui=ui;
	}

	public int getAttackDmg() {
		return attackDmg;
	}

	public void setAttackDmg(int attackDmg) {
		this.attackDmg = attackDmg;
	}

	public int getNumHealthPots() {
		return numHealthPots;
	}

	public void setNumHealthPots(int numHealthPots) {
		this.numHealthPots = numHealthPots;
	}

	public int getHealthPotionHealAmount() {
		return healthPotionHealAmount;
	}

	public void setHealthPotionHealAmount(int healthPotionHealAmount) {
		this.healthPotionHealAmount = healthPotionHealAmount;
	}

	public int getHealthPotionDropChance() {
		return healthPotionDropChance;
	}

	public void setHealthPotionDropChance(int healthPotionDropChance) {
		this.healthPotionDropChance = healthPotionDropChance;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public void setGems(Integer gems) {
		this.gems = gems;
	}

	public void setHealth(Integer health) {
		this.health = health;
	}

}
