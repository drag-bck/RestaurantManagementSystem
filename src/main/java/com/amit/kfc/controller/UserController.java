package com.amit.kfc.controller;

import com.amit.kfc.model.User;
import com.amit.kfc.utils.Encryptor;
import com.amit.kfc.utils.ModelHelper;

import java.sql.PreparedStatement;
import java.util.ArrayList;

public class UserController {
	private static final String key = "12qw34ty67uj,.:'";
	private static final int USER_NAME_MIN = 4;
	private static final int USER_NAME_MAX = 8;
	private static final int PASSWORD_MIN = 8;
	private static final int PASSWORD_MAX = 16;
	
	public ArrayList<User> getUsers() {
		return Models.getInstance().getUsers();
	}
	
	public void addUser(String userName, String password) throws Exception {
		if (userName == null)
			throw new Exception("Username Required!");
		userName = userName.trim();
		if (userName.length() < USER_NAME_MIN)
			throw new Exception("Username too short!");
		if (userName.length() > USER_NAME_MAX)
			throw new Exception("Username too long!");
		if (password == null || password.length() < PASSWORD_MIN)
			throw new Exception("Password too short!");
		if (password.length() > PASSWORD_MAX)
			throw new Exception("Password too long!");
		
		for (User user : getUsers()) {
			if (user.getUserName().equals(userName)) {
				throw new Exception("User already Exists!");
			}
		}
		
		String encPassword = Encryptor.encrypt(key, password);
		int id = getUsers().isEmpty() ? 1 : (getUsers().get(getUsers().size() - 1).getUserId() + 1);
		
		User user = new User();
		user.setUserName(userName);
		user.setPassword(encPassword);
		user.setUserId(id);
		
		PreparedStatement query = user.getWriteQuery(Database.getConnection());
		if (!ModelHelper.executeQuery(query))
			throw new Exception("User Creation Failed");
		
		getUsers().add(user);
	}
	
	public void login(String userName, String password) throws Exception {
		if (getUsers().isEmpty()) {
			addUser(userName, password);
			return;
		}
		
		if (userName == null)
			throw new Exception("Username Required!");
		userName = userName.trim();
		if (userName.length() < USER_NAME_MIN)
			throw new Exception("Username too short!");
		if (userName.length() > USER_NAME_MAX)
			throw new Exception("Username too long!");
		if (password == null || password.length() < PASSWORD_MIN)
			throw new Exception("Password too short!");
		if (password.length() > PASSWORD_MAX)
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
