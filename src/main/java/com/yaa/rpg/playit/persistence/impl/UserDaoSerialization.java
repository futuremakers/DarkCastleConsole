package com.yaa.rpg.playit.persistence.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.yaa.rpg.playit.model.User;
import com.yaa.rpg.playit.persistence.UserDao;
import com.yaa.rpg.playit.utils.FileUtil;

public class UserDaoSerialization implements UserDao {

	public List<User> getUsers() {
			List<User> users = new ArrayList<>();
			FileUtil.listFiles().stream().forEach(fileName -> {
				try {
					users.add((User)FileUtil.readFile(fileName));
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
			});
			return users;
			
	}

	@Override
	public void persistUser(User toSave) {
		try {
			FileUtil.writeFile(toSave, toSave.getName());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateUser(User player) {
		persistUser(player);
	}

	@Override
	public void deleteUser(String username) {
		try {
			FileUtil.deleteFile(username);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
