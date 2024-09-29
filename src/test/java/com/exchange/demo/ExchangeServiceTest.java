package com.exchange.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import org.mockito.InjectMocks;
import com.exchange.demo.model.Item;
import com.exchange.demo.model.PayableAmount;
import com.exchange.demo.constants.Constants;
import com.exchange.demo.model.BillRequest;
import com.exchange.demo.service.ExchangeRateService;

import java.util.Arrays;

@SpringBootTest
public class ExchangeServiceTest {

	@InjectMocks
	private ExchangeRateService exchangeService;

	double exchangeRate = Math.round(278.0861); // Retrieve the latest USD to PKR exchange rate from a third-party service, and update the variable for precise calculations.
 
	
	@Test
	public void testEmployeeWithNoGroceries() {
		BillRequest bill = new BillRequest();
		bill.setUserType(Constants.USER_TYPE_EMPLOYEE);
		bill.setCustomerTenure(1);

		Item item1 = new Item();
		item1.setCategory(Constants.GROCERRIES);
		item1.setPrice(0);

		Item item2 = new Item();
		item2.setCategory("other");
		item2.setPrice(200);

		bill.setItems(Arrays.asList(item1, item2));

		bill.setOriginalCurrency(Constants.ORIGINAL_CURRENCY);
		bill.setTargetCurrency(Constants.TARGET_CURRENCY);

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("~~ Calling        ::: testEmployeeWithNoGroceries");
		PayableAmount payableAmount = exchangeService.calculatePayableAmount(bill, exchangeRate);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		assertEquals(Math.round(36140.0), Math.round(payableAmount.getPaymentAmount()));

	}
	
	
	@Test
	public void testEmployeeWithGroceries() {
		BillRequest bill = new BillRequest();
		bill.setUserType(Constants.USER_TYPE_EMPLOYEE);
		bill.setCustomerTenure(1);

		Item item1 = new Item();
		item1.setCategory(Constants.GROCERRIES);
		item1.setPrice(200);

		Item item2 = new Item();
		item2.setCategory("other");
		item2.setPrice(0);

		bill.setItems(Arrays.asList(item1, item2));

		bill.setOriginalCurrency(Constants.ORIGINAL_CURRENCY);
		bill.setTargetCurrency(Constants.TARGET_CURRENCY);

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("~~ Calling        ::: testEmployeeWithGroceries");
		PayableAmount payableAmount = exchangeService.calculatePayableAmount(bill, exchangeRate);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		assertEquals(Math.round(52820.0), Math.round(payableAmount.getPaymentAmount()));

	}

	
	
	@Test
	public void testEmployeeWithGroceriesAndNoGroceries() {
		BillRequest bill = new BillRequest();
		bill.setUserType(Constants.USER_TYPE_EMPLOYEE);
		bill.setCustomerTenure(1);

		Item item1 = new Item();
		item1.setCategory(Constants.GROCERRIES);
		item1.setPrice(100);

		Item item2 = new Item();
		item2.setCategory("other");
		item2.setPrice(100);

		bill.setItems(Arrays.asList(item1, item2));

		bill.setOriginalCurrency(Constants.ORIGINAL_CURRENCY);
		bill.setTargetCurrency(Constants.TARGET_CURRENCY);

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("~~ Calling        ::: testEmployeeWithGroceriesAndNoGroceries");
		PayableAmount payableAmount = exchangeService.calculatePayableAmount(bill, exchangeRate);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		assertEquals(Math.round(44480), Math.round(payableAmount.getPaymentAmount()));

	}
	
	
	
	@Test
	public void testAffiliateWithNoGroceries() {
		BillRequest bill = new BillRequest();
		bill.setUserType(Constants.USER_TYPE_AFFILIATE);
		bill.setCustomerTenure(1);

		Item item1 = new Item();
		item1.setCategory(Constants.GROCERRIES);
		item1.setPrice(0);

		Item item2 = new Item();
		item2.setCategory("other");
		item2.setPrice(200);

		bill.setItems(Arrays.asList(item1, item2));

		bill.setOriginalCurrency("USD");
		bill.setTargetCurrency("PKR");

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("~~ Calling        ::: testAffiliateWithNoGroceries");
		PayableAmount payableAmount = exchangeService.calculatePayableAmount(bill, exchangeRate);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		
		assertEquals(Math.round(47260.0), Math.round(payableAmount.getPaymentAmount()));

	}
	
	@Test
	public void testAffiliateWithGroceries() {
		BillRequest bill = new BillRequest();
		bill.setUserType(Constants.USER_TYPE_AFFILIATE);
		bill.setCustomerTenure(1);

		Item item1 = new Item();
		item1.setCategory(Constants.GROCERRIES);
		item1.setPrice(200);

		Item item2 = new Item();
		item2.setCategory("other");
		item2.setPrice(0);

		bill.setItems(Arrays.asList(item1, item2));

		bill.setOriginalCurrency(Constants.ORIGINAL_CURRENCY);
		bill.setTargetCurrency(Constants.TARGET_CURRENCY);

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("~~ Calling        ::: testAffiliateWithGroceries");
		PayableAmount payableAmount = exchangeService.calculatePayableAmount(bill, exchangeRate);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		assertEquals(Math.round(52820.0), Math.round(payableAmount.getPaymentAmount()));

	}
	
	
	@Test
	public void testAffiliateWithGroceriesAndNoGroceries() {
		BillRequest bill = new BillRequest();
		bill.setUserType(Constants.USER_TYPE_AFFILIATE);
		bill.setCustomerTenure(1);

		Item item1 = new Item();
		item1.setCategory(Constants.GROCERRIES);
		item1.setPrice(100);

		Item item2 = new Item();
		item2.setCategory("other");
		item2.setPrice(100);

		bill.setItems(Arrays.asList(item1, item2));

		bill.setOriginalCurrency(Constants.ORIGINAL_CURRENCY);
		bill.setTargetCurrency(Constants.TARGET_CURRENCY);

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("~~ Calling        ::: testAffiliateWithGroceriesAndNoGroceries");
		PayableAmount payableAmount = exchangeService.calculatePayableAmount(bill, exchangeRate);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		assertEquals(Math.round(50040.0), Math.round(payableAmount.getPaymentAmount()));

	}
	
	@Test
	public void testEmployeeeWithLongerTenureAndGroceries() {
		BillRequest bill = new BillRequest();
		bill.setUserType(Constants.USER_TYPE_EMPLOYEE);
		bill.setCustomerTenure(3);

		Item item1 = new Item();
		item1.setCategory(Constants.GROCERRIES);
		item1.setPrice(200);

		Item item2 = new Item();
		item2.setCategory("other");
		item2.setPrice(0);

		bill.setItems(Arrays.asList(item1, item2));

		bill.setOriginalCurrency(Constants.ORIGINAL_CURRENCY);
		bill.setTargetCurrency(Constants.TARGET_CURRENCY);

		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("~~ Calling        ::: testAffiliateWithGroceriesAndNoGroceries");
		PayableAmount payableAmount = exchangeService.calculatePayableAmount(bill, exchangeRate);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

		assertEquals(Math.round(52820.0), Math.round(payableAmount.getPaymentAmount()));

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Test
	public void testNonEmployeeWithGroceries() {
		BillRequest bill = new BillRequest();
		bill.setUserType("non-employee");
		bill.setCustomerTenure(1);

		Item item1 = new Item();
		item1.setCategory(Constants.GROCERRIES);
		item1.setPrice(200);		  // passing 200 groceries price

		Item item2 = new Item();
		item2.setCategory("other");
		item2.setPrice(0);			  // passing 0  other price

		bill.setItems(Arrays.asList(item1, item2));

		bill.setOriginalCurrency(Constants.ORIGINAL_CURRENCY);
		bill.setTargetCurrency(Constants.TARGET_CURRENCY);

		System.out.println("~~~ Calling ::: testNonEmployeeWithGroceries");
		PayableAmount payableAmount = exchangeService.calculatePayableAmount(bill, exchangeRate);

		assertEquals(Math.round(52820.0), Math.round(payableAmount.getPaymentAmount()));

	}

	
	
	

	@Test
	public void testEmployeeWithNonGroceriesWithLongerTenure() {
		BillRequest bill = new BillRequest();
		bill.setUserType(Constants.USER_TYPE_EMPLOYEE);
		bill.setCustomerTenure(4);

		Item item1 = new Item();
		item1.setCategory(Constants.GROCERRIES);
		item1.setPrice(0);

		Item item2 = new Item();
		item2.setCategory("other");
		item2.setPrice(200);

		bill.setItems(Arrays.asList(item1, item2));

		bill.setOriginalCurrency(Constants.ORIGINAL_CURRENCY);
		bill.setTargetCurrency(Constants.TARGET_CURRENCY);

		System.out.println("~~~ Calling ::: testEmployeeWithNonGroceriesWithLongerTenure");
		PayableAmount payableAmount = exchangeService.calculatePayableAmount(bill, exchangeRate);

		assertEquals(Math.round(36140.0), Math.round(payableAmount.getPaymentAmount()));

	}

	
	
	
	
	

}