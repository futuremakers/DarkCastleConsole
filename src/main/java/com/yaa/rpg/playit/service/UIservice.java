package com.yaa.rpg.playit.service;

import java.util.List;

import com.yaa.rpg.playit.model.User;

public interface UIservice {
	void printStory(String game);
	void printInstructions();
	void displayFinishedGameMessage();
	void displayUnFinishedGameMessage();
	void hitWallMessage();
	void displayExploreError();
	String readUserInputString();
	int readUserInputInt();
	void destroy();
	void displayUsers(List<User> users);
	void displayInvalidOptionMessage();
	void confirmUserDeletion();
	void userIntroMessage();
	void exitMessage();
	void displayWinMessage();
	void displayAnswerPrompt();
	void displayMonster();
	void displayLooseMessage();
	void displayNoGemsMessage();
	void displayCorrectAnswerMessage();
	void displayInCorrectAnswerMessage();
	void printHelp();
	void displayGameCharacter();
}
