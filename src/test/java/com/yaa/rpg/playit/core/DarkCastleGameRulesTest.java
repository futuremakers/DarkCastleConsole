package com.yaa.rpg.playit.core;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.yaa.rpg.playit.model.User;
import com.yaa.rpg.playit.persistence.RiddleDao;
import com.yaa.rpg.playit.service.UIservice;

public class DarkCastleGameRulesTest {
	@InjectMocks
	public DarkCastleGameRules darkCastle;
	@Mock
	RiddleDao riddlesDao;
	@Mock
	UIservice ui;
	
	static User user;
	@BeforeClass
	public static void init(){
		user =new User("newUser");
		

	}
	
	@Test
	public void testApply() throws Exception {}

	@Test
	public void testFightMonster() throws Exception {
		throw new RuntimeException("not yet implemented");
	}
	
	
	
	
}
