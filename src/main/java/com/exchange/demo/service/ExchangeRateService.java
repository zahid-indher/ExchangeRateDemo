package com.exchange.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.exchange.demo.constants.Constants;
import com.exchange.demo.model.BillRequest;
import com.exchange.demo.model.Item;
import java.util.Map;

//Exchange Rate Class which will fetch exchnage rates and calcuated payable amount and application discunt as per requirments.
@Service
public class ExchangeRateService {

	private final RestTemplate restExecutor = new RestTemplate();

	@SuppressWarnings("unchecked")
	public double fetchExchangeRate(String baseCurrency, String targetCurrency, String apiKey) {
		String url = Constants.API_URL + baseCurrency + "?apikey=" + apiKey;

		System.out.println("URL ::: " + url);
		Map<String, Object> response = restExecutor.getForObject(url, Map.class);
		Map<String, Double> rates = (Map<String, Double>) response.get("rates");
		return rates.get(targetCurrency);
	}

//    public double getTotalAmount(BillRequest bill, double exchangeRate) 
//    {
//        double totalAmount = bill.getTotalAmount();
//        double discount = getDiscountAmount(bill);
//        return (totalAmount - discount) * exchangeRate;
//    }

	public double calculatePayableAmount(BillRequest billRequest, double exchangeRate) {
		double totalAmount      = billRequest.getItems().stream().mapToDouble(item -> item.getPrice()).sum();
		double discount         = getDiscountAmount(billRequest);
		double discountedAmount = (totalAmount - discount) * exchangeRate;

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("~~ Total Amount   ::: " + totalAmount);
		System.out.println("~~ Discount       ::: " + discount);
		System.out.println("~~ After Discount ::: " + discountedAmount);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		return discountedAmount;
	}

/*	private double getDiscountAmount(BillRequest billRequest) {
		double discount = 0;
		double percentageDiscount = 0;

//        if (user.getType().toLowerCase().equals("employee")) 
//        {
//            percentageDiscount = 0.30;
//        } else if (user.getType().toLowerCase().equals("affiliate")) 
//        {
//            percentageDiscount = 0.10;
//        } else if (user.getTenure() > 2) 
//        {
//            percentageDiscount = 0.05;
//        }
		// below logic will check for applicable discounts based on userType value
		String userType = billRequest.getUserType();
		switch (userType) {
		case Constants.USER_TYPE_EMPLOYEE:
			percentageDiscount = 0.30;
			break;
		case Constants.USER_TYPE_AFFILIATE:
			percentageDiscount = 0.10;
			break;
		default:
			if (billRequest.getCustomerTenure() > Constants.CUSTOMER_TENURE) {
				percentageDiscount = 0.05;
			} else {
				percentageDiscount = 0.0;
			}
			break;
		}

		double nonGroceryTotal = billRequest.getItems().stream()
				.filter(item -> !item.getCategory().equals(Constants.GROCERRIES)).mapToDouble(Item::getPrice).sum();

		discount += nonGroceryTotal * percentageDiscount;
		discount += (int) (billRequest.getItems().stream().mapToDouble(item -> item.getPrice()).sum() / 100) * 5; // $5
																													// for
																													// every
																													// $100

		return discount;
	}*/
	
	// Calculate discount amount based on the rules
    private double getDiscountAmount(BillRequest billRequest) {
        double totalBill = 0;
        double groceryTotal = 0;
        double nonGroceryTotal = 0;

        for (Item item : billRequest.getItems()) {
            totalBill += item.getPrice();
            if ("groceries".equalsIgnoreCase(item.getCategory())) {
                groceryTotal += item.getPrice();
            } else {
                nonGroceryTotal += item.getPrice();
            }
        }

        // Determine percentage discount
        double percentageDiscount = 0;
        switch (billRequest.getUserType()) {
            case "employee":
                percentageDiscount = 0.30;
                break;
            case "affiliate":
                percentageDiscount = 0.10;
                break;
            case "regular":
                if (billRequest.getCustomerTenure() > 2) {
                    percentageDiscount = 0.05;
                }
                break;
        }

        // Apply percentage discount only to non-grocery items
        double percentageDiscountAmount = nonGroceryTotal * percentageDiscount;

        // Calculate $5 discount for every $100 on the total bill
        double additionalDiscount = Math.floor(totalBill / 100) * 5;

        // Calculate total discount amount
        double totalDiscount = percentageDiscountAmount + additionalDiscount;

        return totalDiscount;
    }
}
