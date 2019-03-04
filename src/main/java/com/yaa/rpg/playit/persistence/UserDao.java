package com.yaa.rpg.playit.persistence;

import java.util.List;

import com.yaa.rpg.playit.model.User;

public interface UserDao {
	public List<User> getUsers() ;
	public User persistUser(User toSave);
	public User updateUser(User player);
	public String deleteUser(String name);
}
