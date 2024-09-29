package com.exchange.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.exchange.demo.constants.Constants;
import com.exchange.demo.model.BillRequest;
import com.exchange.demo.model.PayableAmount;

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
		DiscountCalculator discountCalculator = new DiscountCalculator();

		double totalBillAmount = billRequest.getItems().stream().mapToDouble(item -> item.getPrice()).sum();
		double discount = discountCalculator.getApplicableDiscount(billRequest);
		double totalPayable = (totalBillAmount - discount) * exchangeRate;
		
		System.out.println("~~ Total Bill Amount   ::: " + totalBillAmount);
		System.out.println("~~ Discount            ::: " + discount);
		System.out.println("~~ After Discount      ::: " + totalPayable);

		PayableAmount payableAmount = new PayableAmount();
		payableAmount.setPaymentAmount(totalPayable);

		return payableAmount;
	}

}
