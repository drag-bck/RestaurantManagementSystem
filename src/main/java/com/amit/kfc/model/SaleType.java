package com.amit.kfc.model;

public enum SaleType {
	Prepaid,
	PostPaid;
	
	public static SaleType parse(int value) {
		if (value == 0) {
			return SaleType.Prepaid;
		}
		return SaleType.PostPaid;
	}
	
	public int getValue() {
		if (this == Prepaid) {
			return 0;
		}
		return 1;
	}
	
	public static SaleType parse(String saleType) throws Exception {
		if (saleType.toLowerCase().equals("prepaid"))
			return Prepaid;
		if (saleType.toLowerCase().equals("postpaid"))
			return PostPaid;
		throw new Exception("Invalid Sale Type");
	}
}