package com.yaa.rpg.playit.core;

import com.yaa.rpg.playit.model.User;

public interface GameRules {

	boolean apply(User player);
	
}
