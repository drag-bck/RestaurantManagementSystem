package com.amit.kfc.controller;

import com.amit.kfc.model.SaleType;
import com.amit.kfc.model.Seller;
import com.amit.kfc.utils.ModelHelper;

import java.util.ArrayList;

public class SellerController {
	
	public ArrayList<Seller> getSellers() {
		return Models.getInstance().getSellers();
	}
	
	public void addSeller(String saleType, String name, float rate) throws Exception {
		if (name == null)
			throw new Exception("Seller Name cannot be Empty");
		
		name = name.trim();
		
		if (name.isEmpty())
			throw new Exception("Seller Name cannot be Empty");
		if (rate < 0 || rate > 100)
			throw new Exception("Invalid Rate");
		
		SaleType sellerSaleType = SaleType.parse(saleType);
		int id = getSellers().isEmpty() ? 1 : getSellers().get(getSellers().size() - 1).getSellerId() + 1;
		
		Seller seller = new Seller();
		seller.setSellerId(id);
		seller.setName(name);
		seller.setSaleType(sellerSaleType);
		seller.setRate(rate);
		
		String query = seller.getWriteQuery();
		if (!ModelHelper.executeQuery(query, Database.getConnection()))
			throw new Exception("Error Adding Seller");
		
		getSellers().add(seller);
	}
}
