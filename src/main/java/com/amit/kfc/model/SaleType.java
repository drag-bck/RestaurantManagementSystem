package com.amit.kfc.model;

public enum SaleType {
	Prepaid,
	PostPaid;
	
	static SaleType getSaleType(int value) {
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
}