package com.exchange.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.exchange.demo.model.BillRequest;
import com.exchange.demo.model.PayableAmount;
import com.exchange.demo.service.ExchangeRateService;

//Exchange Rate Controllre class provide the end point for this project. 
@RestController
@RequestMapping("/api")
public class ExchangeRateController 
{

    @Autowired
    private ExchangeRateService exchangeRateService;

    @Value("${currency.api.key}")
    private String thirdPartyAPIKey;

    @GetMapping("/calculate")
    public ResponseEntity<PayableAmount> computePayableTotal(@RequestBody BillRequest bill) 
    {
        double exchangeRate = exchangeRateService.fetchExchangeRate(bill.getOriginalCurrency(), bill.getTargetCurrency(), thirdPartyAPIKey);
        PayableAmount payableAmount = exchangeRateService.calculatePayableAmount(bill, exchangeRate);
        return new ResponseEntity<>(payableAmount,HttpStatus.OK);
    }
   
}
