package com.yaa.rpg.playit.service.impl;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.yaa.rpg.playit.model.User;
import com.yaa.rpg.playit.persistence.UserDao;
import com.yaa.rpg.playit.persistence.impl.UserDaoSerialization;
import com.yaa.rpg.playit.service.UIservice;

@RunWith(MockitoJUnitRunner.class)
public class UserManageCLITest {
	@Mock
	private static UIservice ui;
	@InjectMocks
	private static UserManageCLI userManageCLI;
	private static UserDao userdao;
    private static User user1;
    private static User user2;
    private static int deleteIndex=4;

    
    @BeforeClass
    public static void init() {
    	userdao = mock(UserDaoSerialization.class);
        
    	user1=new User("yasser1");
    	user2=new User("yasser2");
        when(userdao.getUsers()).thenReturn(Arrays.asList(user1, user2));
        when(userdao.persistUser(user1)).thenReturn(user1);
        when(userdao.updateUser(user1)).thenReturn(user1);
        when(userdao.deleteUser(user1.getName())).thenReturn(user1.getName());
    }
    
	public void testSelectUser_firstUser() throws Exception {
		when(ui.readUserInputInt()).thenReturn(1);
		User user=userManageCLI.selectUser();
		assertTrue(user.getName().equals("yasser1"));
	}
	
	@Test
	public void testSelectUser_secondUser() throws Exception {
		when(ui.readUserInputInt()).thenReturn(2);
		User user=userManageCLI.selectUser();
		assertTrue(user.getName().equals("yasser2"));
	}
	
	@Test
	public void testSelectUser_notExist() throws Exception {
		when(ui.readUserInputInt()).thenReturn(3);
		when(ui.readUserInputString()).thenReturn("yasser4") ;
		User user=userManageCLI.selectUser();
		assertTrue(user.getName().equals("yasser4"));
	}

	@Test
	public void testSelectUser_addUser() throws Exception {
		when(ui.readUserInputInt()).thenReturn(3);
		when(ui.readUserInputString()).thenReturn("yasser3") ;
		User user=userManageCLI.selectUser();
		assertTrue(user.getName().equals("yasser3"));
	}
	
}
