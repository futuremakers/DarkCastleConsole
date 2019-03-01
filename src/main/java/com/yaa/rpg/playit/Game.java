package com.yaa.rpg.playit;

import com.yaa.rpg.playit.core.DarkCastleGameRules;
import com.yaa.rpg.playit.core.GameRules;
import com.yaa.rpg.playit.model.Palace;
import com.yaa.rpg.playit.model.User;
import com.yaa.rpg.playit.service.UIservice;
import com.yaa.rpg.playit.service.UserManagement;
import com.yaa.rpg.playit.service.impl.ConsoleBasedUI;
import com.yaa.rpg.playit.service.impl.UserManageCLI;

public class Game {

	public static void main(String[] args) {
		
			UIservice ui = new ConsoleBasedUI();
			ui.printStory();
			UserManagement usermanager = new UserManageCLI(ui);
			GameRules gameRules = new DarkCastleGameRules(ui);

			User player = usermanager.selectUser();
			player.setUI(ui);
			boolean gotResult = false;
			System.out.println("Let's go!!");
			while (!gotResult) {
				if (player.explore()) { // returns true if user wants to quit
					break;
				}
				gotResult = gameRules.apply(player);
			}
			if (gotResult) {
				player.setMap(new Palace());
				player.modifyHealth(100-player.getHealth());
				ui.displayFinishedGameMessage();
			} else {
				ui.displayUnFinishedGameMessage();
			}
			usermanager.saveUser(player);
	}
}
