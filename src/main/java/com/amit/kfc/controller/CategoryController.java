package com.amit.kfc.controller;

import com.amit.kfc.model.Category;
import com.amit.kfc.utils.ModelHelper;

import java.sql.PreparedStatement;
import java.util.ArrayList;

public class CategoryController {
	public ArrayList<Category> getCategories() {
		return Models.getInstance().getCategories();
	}
	
	public void addCategory(String name) throws Exception {
		if (name == null)
			throw new Exception("Category Name cannot be Empty");
		name = name.trim();
		if (name.isEmpty())
			throw new Exception("Category Name cannot be Empty!");
		
		for (Category category : getCategories()) {
			if (category.getName().equals(name)) {
				throw new Exception("Category already exists!");
			}
		}
		
		int id = getCategories().isEmpty() ? 1 : getCategories().get(getCategories().size() - 1).getCatId();
		
		Category category = new Category();
		category.setCatId(id);
		category.setName(name);
		
		PreparedStatement query = category.getWriteQuery(Database.getConnection());
		if(!ModelHelper.executeQuery(query))
			throw new Exception("Category creation Failed!");
		
		getCategories().add(category);
	}
}
