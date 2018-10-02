package com.amit.kfc.controller;

import com.amit.kfc.model.Item;
import com.amit.kfc.utils.ModelHelper;

import java.sql.PreparedStatement;
import java.util.ArrayList;

public class ItemController {
	public ArrayList<Item> getItems() {
		return Models.getInstance().getItems();
	}
	
	public void addItem(int category, String name, float cost) throws Exception {
		if (name == null)
			throw new Exception("ComboItem Name cannot be Empty");
		name = name.trim();
		if (name.isEmpty())
			throw new Exception("ComboItem Name cannot be Empty!");
		
		for (Item item : getItems()) {
			if (item.getName().equals(name)) {
				throw new Exception("ComboItem already exists!");
			}
		}
		
		if (cost <= 0)
			throw new Exception("Invalid Price");
		
		
		int id = getItems().isEmpty() ? 1 : getItems().get(getItems().size() - 1).getItemId();
		
		Item item = new Item();
		item.setItemId(id);
		item.setName(name);
		item.setCatId(category);
		item.setCost(cost);
		
		PreparedStatement query = item.getWriteQuery(Database.getConnection());
		if (!ModelHelper.executeQuery(query))
			throw new Exception("ComboItem creation Failed!");
		
		getItems().add(item);
	}
	
	public void deleteItem(int itemId) throws Exception {
		Item item = new Item();
		item.setItemId(itemId);
		if (!ModelHelper.executeQuery(item.getDeleteStatement(Database.getConnection()))) {
			throw new Exception("Item Deletion Failed");
		}
	}
	
	public void updateItem(int itemId, float cost) throws Exception{
		Item item = new Item();
		item.setItemId(itemId);
		item.setCost(cost);
		if (!ModelHelper.executeQuery(item.getUpdateQuery(Database.getConnection()))) {
			throw new Exception("Item Updating Failed");
		}
	}
}
