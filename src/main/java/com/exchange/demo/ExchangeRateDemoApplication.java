package com.exchange.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.exchange.demo.config.RsaKeyConfigProperties;

//Currency Exchange Rate Demo Class to Start the application by calling run method of SpringApplication.
@EnableConfigurationProperties(RsaKeyConfigProperties.class)
@SpringBootApplication
public class ExchangeRateDemoApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		System.out.print(
				"~~~~~~~~~~~~~~~~~~~~~ Spring boot currency Exhange Application. STARTING ~~~~~~~~~~~~~~~~~~~~~");
		SpringApplication.run(ExchangeRateDemoApplication.class, args);
		System.out
				.print("~~~~~~~~~~~~~~~~~~~~~ Spring boot currency Exhange Application. STARTED ~~~~~~~~~~~~~~~~~~~~~");
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Encoded Password ::: " + passwordEncoder.encode("indher"));

	}

}
