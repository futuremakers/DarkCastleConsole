package com.yaa.rpg.playit.core;

import com.yaa.rpg.playit.Game;
import com.yaa.rpg.playit.service.UIservice;
import com.yaa.rpg.playit.theme.color.Color;
import com.yaa.rpg.playit.utils.Constants;

public class GameRuleFactory {

	public static GameRules getGameRule(UIservice ui) {
		ui.displayGameCharacter();
		String	selected=ui.readUserInputString();
			if(Constants.DARK_CASTLE_GAME.equals(selected)){
				Game.isHarryPotter=false;				
				return new DarkCastleGameRules(ui);
			}else if(Constants.HARRY_POTTER_GAME.equals(selected)){
				Game.isHarryPotter=true;				
				return new HarryPotterRules(ui);
			}else {
				System.out.println(Color.RED.format("Invalid command ,please select 1 or 2"));
				getGameRule(ui);
			}
		return null;
	}
	
	
}
