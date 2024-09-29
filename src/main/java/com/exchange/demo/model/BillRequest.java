package com.exchange.demo.model;

import java.util.List;
import java.util.stream.Collectors;

//BillRequest is class which maps json values to java fields
public class BillRequest {
	private List<Item> items;
	private String originalCurrency;
	private String targetCurrency;
	private String userType; // employee, affiliate, regular
	private int customerTenure; // in years

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public String getOriginalCurrency() {
		return originalCurrency;
	}

	public void setOriginalCurrency(String originalCurrency) {
		this.originalCurrency = originalCurrency;
	}

	public String getTargetCurrency() {
		return targetCurrency;
	}

	public void setTargetCurrency(String targetCurrency) {
		this.targetCurrency = targetCurrency;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public int getCustomerTenure() {
		return customerTenure;
	}

	public void setCustomerTenure(int customerTenure) {
		this.customerTenure = customerTenure;
	}
	
//	public void removeGroceryItems() {
//		if (this.items != null) {
//			this.items = this.items.stream()
//				.filter(item -> !"groceries".equalsIgnoreCase(item.getCategory()))
//				.collect(Collectors.toList());
//		}
//	}

}
