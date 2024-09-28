package com.exchange.demo.service;

import com.exchange.demo.model.BillRequest;
import com.exchange.demo.model.Item;

//This class provide the methods to calculate the discount based on specified rules.
public class DiscountCalculator {

	public double calculateTotalPayable(BillRequest billRequest) {
		double totalBill = 0;
		double groceryTotal = 0;

		// Calculate total bill and grocery total
		for (Item item : billRequest.getItems()) {
			totalBill += item.getPrice();
			if ("groceries".equalsIgnoreCase(item.getCategory())) {
				groceryTotal += item.getPrice();
			}
		}

		// Calculate applicable percentage discount
		double percentageDiscount = getPercentageDiscount(billRequest);

		// Calculate percentage discount amount
		double percentageDiscountAmount = (percentageDiscount > 0) ? (totalBill - groceryTotal) * percentageDiscount
				: 0;

		// Calculate additional discount ($5 for every $100 on the bill)
		double additionalDiscount = Math.floor(totalBill / 100) * 5;

		// Calculate total discount
		double totalDiscount = percentageDiscountAmount + additionalDiscount;

		// Calculate total payable amount
		double totalPayable = totalBill - totalDiscount;

		return totalPayable;
	}

	private double getPercentageDiscount(BillRequest billRequest) {
		double discount = 0;

		if ("employee".equals(billRequest.getUserType())) {
			discount = 0.30; // 30% discount for employees
		} else if ("affiliate".equals(billRequest.getUserType())) {
			discount = 0.10; // 10% discount for affiliates
		} else if (billRequest.getCustomerTenure() > 2) {
			discount = 0.05; // 5% discount for regular customers with tenure > 2 years
		}

		return discount;
	}
}
