package com.yaa.rpg.playit.core;

import java.util.Random;

import com.yaa.rpg.playit.model.User;
import com.yaa.rpg.playit.service.UIservice;
import com.yaa.rpg.playit.theme.ascii.AsciiArt;
import com.yaa.rpg.playit.theme.color.Color;
import com.yaa.rpg.playit.utils.Constants;

public class HarryPotterRules implements GameRules {

	UIservice ui;
	//Rooms
    String [] location = {"The First floor girl's lavatory//Moaning Myrtle's Bathroom", "the Main Castle", "Madam Pomfrey's Hospital Ward", "the Collapsed Tunnel", "The Chamber"};
    //Enemies
    String[] enemies = { "Professor Gilderoy Lockhart", "Tom Riddle", "Tom Riddle's Diary", "Basilisk" };
    //Weapons
    String [] weaponAttack = { "your Wand", "the Sword of Gryffindor", "some Rocks", "a Basilisk tooth"};

	int maxEnemyHealth = 100;
	int enemyAttackDamage = 25;

	public HarryPotterRules(UIservice ui) {
		this.ui = ui;
		ui.printStory(Constants.HARRY_POTTER_GAME);
	}

	@Override
	public boolean apply(User harry) {
        // System objects
        Random rand = new Random();
        boolean running = true;
        // initiate game
        GAME:
        while (running) {

            String building = location[rand.nextInt(location.length)];
            System.out.println(Color.MAGENTA.format("\t# " + "You are in: " + building + " #\n"));

            String harryWeapon = weaponAttack[rand.nextInt(weaponAttack.length)];
            System.out.println(Color.MAGENTA.format("\t# " + "You are equipped with: " + harryWeapon + " #\n"));

            int enemyHealth = rand.nextInt(maxEnemyHealth);
            String enemy = enemies[rand.nextInt(enemies.length)];
            AsciiArt.drawString(enemy, Color.RED_BG.format("X"),10);
            System.out.println(Color.RED.format("\t# " + enemy + " has appeared! #\n"));

            while (enemyHealth > 0 && harry.getHealth() <= 100) {
                System.out.println(Color.YELLOW.format("\tYour HP: " + harry.getHealth()));
                System.out.println(Color.YELLOW.format("\t" + enemy + "'s HP: " + enemyHealth));
                System.out.println(Color.YELLOW.format("\t" + "You are in: " + building));
                System.out.println(Color.YELLOW.format("\n\tWhat would you like to do?"));
                System.out.println(Color.RED_BG.format("\t1. Attack: Sectumsempra"));
                System.out.println(Color.MAGENTA_BG.format("\t2. Drink some pumpkin juice"));
                System.out.println(Color.GREEN_BG.format("\t3. Run!"));
                if (harry.getHealth()> 5) {
                    harry.setHealth(harry.getHealth()- 5);
                }
                if (harry.getHealth()< 1) {
                    System.out.println(Color.RED.format("\tYou limp out of " + building + " beaten by " + enemy));
                    System.out.println(Color.YELLOW.format("\tYou have been defeated. Better luck next time!"));
                    System.out.println("############################");
                    System.out.println(Color.GREEN_BG.format("Thank you for playing! "));
                    System.out.println("############################");
                    //System.exit(0);
                    return true;
                    //break;
                } else if (harry.getHealth() > 100) {
                    harry.setHealth(100) ;
                    System.out.println(Color.YELLOW.format("Maximum HP is " + harry.getHealth()));
                    System.out.println(Color.YELLOW.format("You are at full health"));
                }

                String input = ui.readUserInputString();
                if (input.equals("1")) {
                    int damageDealt = rand.nextInt(harry.getAttackDmg());
                    int damageTaken = rand.nextInt(enemyAttackDamage);

                    enemyHealth -= damageDealt;
                    harry.setHealth(harry.getHealth() - damageTaken);

                    System.out.println(Color.YELLOW.format("\t> You attack the " + enemy + " with " + harryWeapon +" for " + damageDealt + " damage"));
                    System.out.println(Color.YELLOW.format("\t> You have received " + damageTaken + " in retaliation"));

                    if (harry.getHealth() < 1) {
                        System.out.println(Color.RED.format("\t You have taken too much damage, you are too weak to go on"));
                        System.out.println(Color.RED.format("\t You have been defeated. Better luck next time!"));
                        System.out.println("############################");
                        System.out.println(Color.GREEN.format("  Thank you for playing! "));
                        System.out.println(Color.GREEN.format("  You're a wizard, Harry! "));
                        System.out.println("############################");
                        return true;
                    }
                } else if (input.equals("2")) {
                    if (harry.getNumHealthPots() > 0 && harry.getHealth() > 0) {
                        harry.setHealth(harry.getHealth()+harry.getHealthPotionHealAmount()) ;
                        harry.setNumHealthPots(harry.getNumHealthPots()-1);
                        System.out.println(Color.MAGENTA_BG.format("\t> You drank a health potion, healed for: " + harry.getHealthPotionHealAmount() + " HP."
                                + "\n\t> You now have " + harry.getNumHealthPots() + " health potions left.\n"));
                    }
                } else if (input.equals("3")) {
                    System.out.println(Color.BLUE.format("\t> You ran away from " + enemy));
                    System.out.println(Color.BLUE.format("\t> You exit the " + building));
                    continue GAME;
                } else if (input.equals("quit")){
                System.out.println(Color.MAGENTA_BG.format("\t> You run away from the " + enemy));
                System.out.println("############################");
                System.out.println(Color.GREEN.format("  Thank you for playing! "));
                System.out.println(Color.GREEN.format("  You're a wizard, Harry! "));
                System.out.println("############################");
               return true;
            }   else {
                    System.out.println(Color.RED.format("\tInvalid command"));
                }
            }


            System.out.println("--------------------------------------");
            System.out.println(" # " + enemy + " was defeated! # ");
            System.out.println("10 points to Gryffindor, bravo!");
            if (harry.getHealth() > 0) {
                System.out.println(Color.MAGENTA_BG.format(" # You have " + harry.getHealth() + "HP left # "));
            }else{
                System.out.println(Color.RED_BG.format("You have been defeated. Better luck next time!"));
            }
            // If the random number is less than 10 it drops
            if (rand.nextInt(100) < harry.getHealthPotionDropChance()) {
                harry.setNumHealthPots(harry.getNumHealthPots()+1);
                System.out.println(Color.BLUE_BG.format(" # The " + enemy + " dropped a pumpkin juice potion. # "));
                System.out.println(Color.BLUE_BG.format(" # You now have " + harry.getNumHealthPots() + " health potion(s). # "));
            }
            System.out.println("--------------------------------------");
            System.out.println(Color.YELLOW.format("What would you like to do now?"));
            System.out.println(Color.BLUE_BG.format("1. Continue battling to defeat the horcrux"));
            System.out.println(Color.MAGENTA_BG.format("2. Exit the Chamber of Secrets"));
            String input = ui.readUserInputString();

            while (!input.equals("1") && !input.equals("2")) {
                System.out.println(Color.RED.format("\tInvalid command"));
                input = ui.readUserInputString();

            }
            if (input.equals("1") && harry.getHealth() > 0) {
                System.out.println(Color.BLUE_BG.format("You continue your adventure."));
            } else if (input.equals("2")) {
                System.out.println(Color.RED_BG.format("You exited the Chamber of Secrets and swiftly found yourself in Madam Pomfrey's hopsital ward."));
                return true;
            }
        }
        System.out.println("############################");
        System.out.println(Color.GREEN_BG.format("  Thank you for playing! "));
        System.out.println(Color.GREEN_BG.format("  You're a wizard, Harry! "));
        System.out.println("############################");
    
		
		return false;
	}

	
}
