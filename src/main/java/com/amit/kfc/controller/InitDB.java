package com.amit.kfc.controller;

import com.amit.kfc.model.Category;
import com.amit.kfc.model.Item;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class InitDB {
	private static InitDB instance = null;
	
	private CategoryController categoryController;
	private ItemController itemController;
	
	private InitDB() {
		categoryController = new CategoryController();
		itemController = new ItemController();
	}
	
	public static InitDB getInstance() {
		if (instance == null) {
			instance = new InitDB();
		}
		return instance;
	}
	
	public void initialise() {
		if (Models.getInstance().getCategories().isEmpty()) {
			readCategories();
			readItems();
		}
	}
	
	private void readCategories() {
		String csvFile = InitDB.class.getResource("/dataset/ItemCat.csv").getPath();
		BufferedReader br = null;
		String line;
		String cvsSplitBy = "\\|";
		
		try {
			
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				String[] tokens = line.split(cvsSplitBy);
				categoryController.addCategory(new Category(tokens[0], tokens[1]));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private void readItems() {
		String csvFile = InitDB.class.getResource("/dataset/Items.csv").getPath();
		BufferedReader br = null;
		String line;
		String cvsSplitBy = "\\|";
		
		try {
			
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				String[] tokens = line.split(cvsSplitBy);
				itemController.addItem(new Item(tokens[0], tokens[1], tokens[2]));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
