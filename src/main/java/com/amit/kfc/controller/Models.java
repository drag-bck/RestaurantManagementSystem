package com.amit.kfc.controller;

import com.amit.kfc.model.*;
import com.amit.kfc.utils.ModelHelper;

import java.sql.ResultSet;
import java.util.*;

@SuppressWarnings("unchecked")
public class Models {
	private static Models models = null;
	
	private List<Class<? extends BaseModel>> modelList;
	
	private Map<String, ArrayList<? extends BaseModel>> modelMap;
	
	public ArrayList<Category> getCategories() {
		return (ArrayList<Category>) modelMap.get(Category.class.getName());
	}
	
	public ArrayList<Item> getItems() {
		return (ArrayList<Item>) modelMap.get(Item.class.getName());
	}
	
	public ArrayList<Order> getOrders() {
		return (ArrayList<Order>) modelMap.get(Order.class.getName());
	}
	
	public ArrayList<Seller> getSellers() {
		return (ArrayList<Seller>) modelMap.get(Seller.class.getName());
	}
	
	public ArrayList<User> getUsers() {
		return (ArrayList<User>) modelMap.get(User.class.getName());
	}
	
	private Models() {
		modelList = Arrays.asList(Category.class, Item.class, Order.class, Seller.class, User.class);
		modelMap = new HashMap<>();
	}
	
	public static synchronized Models getInstance() {
		if (models == null) {
			models = new Models();
		}
		return models;
	}
	
	public void init() {
		
		try {
			modelMap = new HashMap<>();
			for (Class<? extends BaseModel> cls : modelList) {
				BaseModel model = cls.newInstance();
				
				ModelHelper.executeQuery(model.getInitQuery(), Database.getConnection());
				ResultSet resultSet = ModelHelper.executeQueryForResult(model.getFetchQuery(), Database.getConnection());
				
				ArrayList<? extends BaseModel> itemList = ModelHelper.resultToModelList(resultSet, cls.getName());
				modelMap.put(cls.getName(), itemList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
