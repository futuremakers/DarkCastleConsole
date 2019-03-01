package com.yaa.rpg.playit.service;

import com.yaa.rpg.playit.model.User;

public interface UserManagement {

	public User selectUser();
	public User addUser();
	public void saveUser(User player);
}
