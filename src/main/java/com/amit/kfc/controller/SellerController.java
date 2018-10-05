package com.amit.kfc.controller;

import com.amit.kfc.model.Seller;
import com.amit.kfc.utils.ModelHelper;

import java.sql.PreparedStatement;
import java.util.ArrayList;

public class SellerController {
	
	public ArrayList<Seller> getSellers() {
		return Models.getInstance().getSellers();
	}
	
	public void addSeller(String name, float rate) throws Exception {
		if (name == null)
			throw new Exception("Seller Name cannot be Empty");
		
		name = name.trim();
		
		if (name.isEmpty())
			throw new Exception("Seller Name cannot be Empty");
		if (rate < 0 || rate > 100)
			throw new Exception("Invalid Rate");
		
		int id = getSellers().isEmpty() ? 1 : getSellers().get(getSellers().size() - 1).getSellerId() + 1;
		
		Seller seller = new Seller();
		seller.setSellerId(id);
		seller.setName(name);
		seller.setRate(rate);
		
		PreparedStatement query = seller.getWriteQuery(Database.getConnection());
		if (!ModelHelper.executeQuery(query))
			throw new Exception("Error Adding Seller");
		
		getSellers().add(seller);
	}
}
