package com.yaa.rpg.playit.service.impl;

import java.util.List;
import java.util.Scanner;

import com.yaa.rpg.playit.model.User;
import com.yaa.rpg.playit.service.UIservice;
import com.yaa.rpg.playit.theme.ascii.AsciiArt;
import com.yaa.rpg.playit.theme.color.Color;

public class ConsoleBasedUI implements UIservice {

	Scanner scan = new Scanner(System.in);
	StringBuilder strBuilder=new StringBuilder();
	@Override
	public void printStory() {
		strBuilder.setLength(0);
		AsciiArt.drawString("Dark Castle", Color.MAGENTA_BG.format("+"));
		strBuilder.append("\n--------------------------------------------------------------------------------------------------------------------\n")
		.append("You are going to enter a palace.\n" + "There are so many rooms in this palace.\n")
		.append("There is a princess locked up in one of those rooms, surrounded by monsters\n")
		.append("Navigate to princess with N,E,W,S keys  \n").append( "Answer riddles asked by monster to pass each monster")
		.append("\n--------------------------------------------------------------------------------------------------------------------\n") ;
		System.out.println(Color.BLUE.format(strBuilder.toString()));
		System.out.println();

		System.out.flush();
	}

	@Override
	public void printInstructions() {
		strBuilder.setLength(0);
		strBuilder.append("\nWhich direction do you want to move?\n")
		.append("NORTH(N/n) , EAST(E/e),WEST(W/w),SOUTH(S/s) . ").append("Quit/Q/q to exit")
		.append("Type your direction N,E,W,S,Q :");
		System.out.println(Color.YELLOW.format(strBuilder.toString()));
	}

	@Override
	public void displayFinishedGameMessage() {
		System.out.println(Color.GREEN.format("Thanks for playing.Play again to solve harder riddles."));
	}

	@Override
	public void displayUnFinishedGameMessage() {
		System.out.println(Color.GREEN.format("Your game will be saved.You can resume the game by selecting same user"));
	}

	@Override
	public void hitWallMessage() {
		System.out.println(Color.RED.format("Ouch!I hit a wall.Choose Different direction"));
	}

	@Override
	public void displayExploreError() {
		System.err.println("Invalid input.Only N/E/W/S/Q are allowed");
	}

	@Override
	public String readUserInputString() {
		return scan.nextLine();
	}

	@Override
	public int readUserInputInt() {
		int input = scan.nextInt();
		scan.nextLine();//takes care of \n character after number
		return input;
	}

	@Override
	public void destroy() {
		scan.close();
	}
	
	@Override
	public void displayUsers(List<User> users) {
		printHeader();
		int i = 0;
		for (User user : users) {
			System.out.println(Color.MAGENTA_BG.format(++i + ")   " + user.getName() + "\t" + user.getGems() + "\t" + user.getHealth()));
		}
		System.out.println(Color.GREEN_BG.format(++i+ ")   create New user"));
		System.out.println(Color.RED_BG.format(++i+ ")   Delete A user"));
		System.out.println(Color.YELLOW.format("Type your number 1,2,3,..."));
		System.out.flush();
	}

	private void printHeader() {
		System.out.println((Color.MAGENTA.format("Select a user from the following")));
		System.out.println(Color.YELLOW.format("----------------------------------"));
		System.out.println(Color.MAGENTA.format("\tName\tGems\tHealth"));
		System.out.println("----------------------------------");
		System.out.flush();
	}

	@Override
	public void displayInvalidOptionMessage() {
		System.err.println(Color.RED_BG.format("Invalid option.Try again"));
	}

	@Override
	public void confirmUserDeletion() {
		System.out.println(Color.RED_BG.format("To confirm deletion - enter the name of user.To cancel - enter #cancel"));
	}

	@Override
	public void userIntroMessage() {
		System.out.println("Let's get introduced");
		System.out.println(Color.BOLD.format(Color.GREEN.format("What should I call you ,type your NikeName?")));
	}

	@Override
	public void exitMessage() {
		System.out.println(Color.MAGENTA_BG.format("Goodbye!"));
	}

	@Override
	public void displayWinMessage() {
		AsciiArt.drawString("PRINCESS", "*");
		System.out.println(Color.MAGENTA_BG.format("****************************"));
		System.out.println(Color.GREEN_BG.format("Princess is in this room.You won!!"));
		System.out.println(Color.MAGENTA_BG.format("****************************"));
	}

	@Override
	public void displayAnswerPrompt() {
		System.out.println(Color.MAGENTA_BG.format("Enter #help for available commands."));
		System.out.println(Color.GREEN_BG.format("1) Answert Question."));
		System.out.println(Color.BLUE_BG.format("2) Pay gems by typing '#pay' " ));
		System.out.println(Color.RED_BG.format("3) Fight the monster type '#giveup' "));
		System.out.println(Color.YELLOW.format("Type your Answer/Command ,#pay,#giveup or #help:"));
		System.out.flush();
	}

	@Override
	public void displayMonster() {
		AsciiArt.drawString("MONSTER", Color.RED_BG.format("X"));
		System.out.println(Color.YELLOW_BG.format("There is monster in this room.Answer the following riddle to impress it"));
	}

	@Override
	public void displayLooseMessage() {
		System.out.println(Color.RED_BG.format("You died.You lost the game"));
	}

	@Override
	public void displayNoGemsMessage() {
		System.out.println(Color.YELLOW_BG.format("You have no Gems.You can fight by typing #giveup if you cannot answer"));
	}

	@Override
	public void displayCorrectAnswerMessage() {
		System.out.println(Color.GREEN_BG.format("Correct Answer! Monster gave you one gem"));
	}

	@Override
	public void displayInCorrectAnswerMessage() {
		System.out.println(Color.RED_BG.format("Wrong Answer! Try again"));
	}
	@Override
	public void printHelp() {
		System.out.println(Color.YELLOW_BG.format("You have to answer monster riddle.\n" + "If you answer correctly you will get gems.\n"
				+ "It will give you hints if you pay gems by typing '#pay' \n" + "You can type '#giveup' to fight the monster\n"));
	}
}
