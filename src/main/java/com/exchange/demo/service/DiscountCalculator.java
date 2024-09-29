package com.exchange.demo.service;

import java.util.List;

import com.exchange.demo.constants.Constants;
import com.exchange.demo.model.BillRequest;
import com.exchange.demo.model.Item;

//This class provide the methods to calculate the discount based on specified rules.
public class DiscountCalculator {

	public double getApplicableDiscount(BillRequest billRequest) {

		double noGroceryTotal     = 0.0;
		double percentageDiscount = 0.0;

		int customerSince  = billRequest.getCustomerTenure();
		String userType    = billRequest.getUserType();
		double totalAmount = billRequest.getItems().stream().mapToDouble(Item::getPrice).sum();

		// Calculate fixed discount for every $100 on the bill
		double fixedDiscount = Math.floor(totalAmount / 100) * 5;

		// To obtain the total for non-grocery items, as discounts only apply to them
		// according to the specified rules.
		for (Item item : billRequest.getItems()) {
			if (!Constants.GROCERRIES.equalsIgnoreCase(item.getCategory())) {
				noGroceryTotal += item.getPrice();
			}
		}

		if (hasNonGroceries(billRequest.getItems())) {
			percentageDiscount = getPercentageDiscount(userType, customerSince);
			noGroceryTotal = noGroceryTotal * percentageDiscount;
		}

		double discount = noGroceryTotal + fixedDiscount;

		return discount;
	}

	// Static method to check if any item in the list has the category "groceries"
	public static boolean hasNonGroceries(List<Item> items) {
		if (items == null || items.isEmpty()) {
			return false;
		}
		return items.stream().anyMatch(item -> !Constants.GROCERRIES.equalsIgnoreCase(item.getCategory()));
	}

	private double getPercentageDiscount(String userType, int customerSince) {
		double percentageDiscount = 0;

		if (Constants.USER_TYPE_EMPLOYEE.equals(userType)) {
			percentageDiscount = 0.30; // 30% discount for employees
		} else if ("affiliate".equals(userType)) {
			percentageDiscount = 0.10; // 10% discount for affiliates
		} else if (customerSince > 2) {
			percentageDiscount = 0.05; // 5% discount for regular customers with tenure > 2 years
		}
		return percentageDiscount;
	}
}
