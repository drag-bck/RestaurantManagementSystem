package com.amit.kfc.controller;

import com.amit.kfc.model.User;
import com.amit.kfc.utils.Encryptor;
import com.amit.kfc.utils.ModelHelper;

import java.util.ArrayList;

public class UserController {
	private static final String key = "12qw34ty67uj,.:'";
	
	public ArrayList<User> getUsers() {
		return Models.getInstance().getUsers();
	}
	
	void addUser(String userName, String password) throws Exception {
		if (userName == null)
			throw new Exception("Username Required!");
		userName = userName.trim();
		if (userName.length() < 8)
			throw new Exception("Username too short!");
		if (userName.length() > 16)
			throw new Exception("Username too long!");
		if (password == null || password.length() < 8)
			throw new Exception("Password too short!");
		if (password.length() > 16)
			throw new Exception("Password too long!");
		
		for (User user : getUsers()) {
			if (user.getUserName().equals(userName)) {
				throw new Exception("User already Exists!");
			}
		}
		
		String encPassword = Encryptor.encrypt(key, password);
		int id = getUsers().isEmpty() ? 1 : getUsers().get(getUsers().size() - 1).getUserId() + 1;
		
		User user = new User();
		user.setUserName(userName);
		user.setPassword(encPassword);
		user.setUserId(id);
		
		String query = user.getWriteQuery();
		if (!ModelHelper.executeQuery(query, Database.getConnection()))
			throw new Exception("User Creation Failed");
		
		getUsers().add(user);
	}
	
	void login(String userName, String password) throws Exception {
		if (userName == null)
			throw new Exception("Username Required!");
		userName = userName.trim();
		if (userName.length() < 8)
			throw new Exception("Username too short!");
		if (userName.length() > 16)
			throw new Exception("Username too long!");
		if (password == null || password.length() < 8)
			throw new Exception("Password too short!");
		if (password.length() > 16)
			throw new Exception("Password too long!");
		
		String encPassword = Encryptor.encrypt(key, password);
		ArrayList<User> users = Models.getInstance().getUsers();
		
		for (User user : users) {
			if (user.getUserName().equals(userName)) {
				if (user.getPassword().equals(encPassword))
					return;
				break;
			}
		}
		
		throw new Exception("Invalid Username/Password");
	}
}
