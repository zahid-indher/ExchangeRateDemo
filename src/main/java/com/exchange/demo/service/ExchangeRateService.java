package com.exchange.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.exchange.demo.constants.Constants;
import com.exchange.demo.model.BillRequest;
import com.exchange.demo.model.Item;
import com.exchange.demo.model.PayableAmount;

import java.util.List;
import java.util.Map;

//Exchange Rate Class which will fetch exchnage rates and calcuated payable amount and application discunt as per requirments.
@Service
public class ExchangeRateService {

	private final RestTemplate restExecutor = new RestTemplate();

	@SuppressWarnings("unchecked")
	public double fetchExchangeRate(String baseCurrency, String targetCurrency, String apiKey) {
		String url = Constants.API_URL + baseCurrency + "?apikey=" + apiKey;

		System.out.println("Third Paryt URL ::: " + url);
		Map<String, Object> response = restExecutor.getForObject(url, Map.class);
		Map<String, Double> rates = (Map<String, Double>) response.get("rates");
		return rates.get(targetCurrency);
	}

	public PayableAmount calculatePayableAmount(BillRequest billRequest, double exchangeRate) {

		double totalBillAmount = billRequest.getItems().stream().mapToDouble(item -> item.getPrice()).sum();
		double discount = getApplicableDiscount(billRequest);
		double totalPayable = (totalBillAmount - discount) * exchangeRate;

		System.out.println("~~ Total Bill Amount   ::: " + totalBillAmount);
		System.out.println("~~ Discount            ::: " + discount);
		System.out.println("~~ After Discount      ::: " + totalPayable);

		PayableAmount payableAmount = new PayableAmount();
		payableAmount.setPaymentAmount(totalPayable);

		return payableAmount;
	}

	public double getApplicableDiscount(BillRequest billRequest) {

		double noGroceryTotal = 0.0;
		double percentageDiscount = 0.0;

		int customerSince = billRequest.getCustomerTenure();
		String userType = billRequest.getUserType();
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
