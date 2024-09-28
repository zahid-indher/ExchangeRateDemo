package com.exchange.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import com.exchange.demo.model.BillRequest;
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

    //@PostMapping("/calculate")
    @GetMapping("/calculate")
    public double computePayableTotal(@RequestBody BillRequest bill) 
    {
        double exchangeRate = exchangeRateService.fetchExchangeRate(bill.getOriginalCurrency(), bill.getTargetCurrency(), thirdPartyAPIKey);
        return exchangeRateService.calculatePayableAmount(bill, exchangeRate);
    }
   
}
