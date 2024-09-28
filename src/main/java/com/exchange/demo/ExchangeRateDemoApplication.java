package com.exchange.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Currency Exchange Rate Demo Class to Start the application by calling run method of SpringApplication.
@SpringBootApplication
public class ExchangeRateDemoApplication {

	public static void main(String[] args) 
	{
		System.out.print("~~~~~~~~~~~~~~~~~~~~~ Spring boot currency Exhange Application. STARTING ~~~~~~~~~~~~~~~~~~~~~");
		SpringApplication.run(ExchangeRateDemoApplication.class, args);
		System.out.print("~~~~~~~~~~~~~~~~~~~~~ Spring boot currency Exhange Application. STARTED ~~~~~~~~~~~~~~~~~~~~~");
	}

}
