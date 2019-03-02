package com.yaa.rpg.playit.core;

import com.yaa.rpg.playit.model.GameCharacter;
import com.yaa.rpg.playit.model.User;
import com.yaa.rpg.playit.persistence.RiddleDao;
import com.yaa.rpg.playit.persistence.impl.RiddleDaoImpl;
import com.yaa.rpg.playit.service.UIservice;
import com.yaa.rpg.playit.utils.Commands;
import com.yaa.rpg.playit.utils.Constants;

public class DarkCastleGameRules implements GameRules {

	RiddleDao riddlesDao;
	UIservice ui;

	public DarkCastleGameRules(UIservice ui) {
		riddlesDao = new RiddleDaoImpl();
		this.ui = ui;
		ui.printStory(Constants.DARK_CASTLE_GAME);
	}

	@Override
	public boolean apply(User player) {
		if (player.getMap().getCurrentRoom().getOccupiedBy() == GameCharacter.PRINCESS) {
			ui.displayWinMessage();
			return true;
		} else if (player.getMap().getCurrentRoom().getOccupiedBy() == GameCharacter.MONSTER) {
			ui.displayMonster();

			System.out.println();
			System.out.println(riddlesDao.getRiddle(player.getLevel()));
			String userAnswer = "";
			do {
				ui.displayAnswerPrompt();
				userAnswer = ui.readUserInputString();
				if (userAnswer.equalsIgnoreCase(Commands.GIVEUP)) {
					double healthLost = fightMonster();
					player.modifyHealth((int) healthLost);
					player.IncLevel();
					if (player.getHealth() == 0) {
						ui.displayLooseMessage();
						return true;
					}
					break;
				} else if (userAnswer.equalsIgnoreCase(Commands.HELP)) {
					ui.printHelp();
				} else if (userAnswer.equalsIgnoreCase(Commands.PAY)) {
					if (player.getGems() == 0) {
						ui.displayNoGemsMessage();
					} else {
						player.modifyGems(-1);
						System.out.println(riddlesDao.getRiddleHint(player.getLevel()));
					}
				} else if (riddlesDao.checkRiddleAnswer(userAnswer, player.getLevel())) {
					player.IncLevel();
					player.modifyGems(1);
					ui.displayCorrectAnswerMessage();
					break;
				} else {
					ui.displayInCorrectAnswerMessage();
				}
			} while (true);
			System.out.println(player);
		}
		return false;
	}

	private double fightMonster() {
		System.out.println("Fighting Monster...");
		return Math.random() * 25 - 40;
	}
	
}
