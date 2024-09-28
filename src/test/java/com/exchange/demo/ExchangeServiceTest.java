package com.exchange.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import org.mockito.InjectMocks;
import com.exchange.demo.model.Item;
import com.exchange.demo.model.PayableAmount;
import com.exchange.demo.model.BillRequest;
import com.exchange.demo.service.ExchangeRateService;

import java.util.Arrays;

@SpringBootTest
public class ExchangeServiceTest {

	@InjectMocks
	private ExchangeRateService exchangeService;

	@Test
	public void testCalculateTotalAmountForNonEmployeeWithGroceries() {
		BillRequest bill = new BillRequest();
		bill.setUserType("non-employee");
		bill.setCustomerTenure(1);

		Item item1 = new Item();
		item1.setCategory("groceries");
		item1.setPrice(200);

		Item item2 = new Item();
		item2.setCategory("other");
		item2.setPrice(0);

		bill.setItems(Arrays.asList(item1, item2));

		bill.setOriginalCurrency("USD");
		bill.setTargetCurrency("PKR");

		PayableAmount payableAmount = exchangeService.calculatePayableAmount(bill, 278.0861);

		assertEquals(52836.359, payableAmount.getPaymentAmount());

	}

	@Test
	public void testCalculateTotalAmountForEmployeeWithGroceries() {
		BillRequest bill = new BillRequest();
		bill.setUserType("employee");
		bill.setCustomerTenure(1);

		Item item1 = new Item();
		item1.setCategory("groceries");
		item1.setPrice(200);

		Item item2 = new Item();
		item2.setCategory("other");
		item2.setPrice(0);

		bill.setItems(Arrays.asList(item1, item2));

		bill.setOriginalCurrency("USD");
		bill.setTargetCurrency("PKR");

		PayableAmount payableAmount = exchangeService.calculatePayableAmount(bill, 278.0861);

		assertEquals(52836.359, payableAmount);

	}

	@Test
	public void testCalculateTotalAmountForEmployeeWithNonGroceries() {
		BillRequest bill = new BillRequest();
		bill.setUserType("employee");
		bill.setCustomerTenure(1);

		Item item1 = new Item();
		item1.setCategory("groceries");
		item1.setPrice(0);

		Item item2 = new Item();
		item2.setCategory("other");
		item2.setPrice(200);

		bill.setItems(Arrays.asList(item1, item2));

		bill.setOriginalCurrency("USD");
		bill.setTargetCurrency("PKR");

		PayableAmount payableAmount = exchangeService.calculatePayableAmount(bill, 278.0861);

		assertEquals(52836.359, payableAmount);

	}

}