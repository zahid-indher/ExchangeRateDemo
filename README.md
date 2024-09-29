
# Spring boot currency exchange application
This demo project retrieves real-time currency exchange rates, applies applicable discounts, and returns the final payable amount.

## Features
- calculatePayable Amount API.
- User Login
    - OAuth2 Resource Server
- JUNIT Test Cases
  
##Third-Party API Integration
-  ExchangeRate-API 

#Clone the project

```bash
  git clone https://github.com/zahid-indher/ExchangeRateDemo.git

```
##Configure your API key:
- Update the application.properties file with your currency exchange API key: 9d50db5ce26ee62dcfc706a5
- "The key above is related to my account and was generated online."

##Build the project
``` 
	./mvnw clean install
```


##Run the project
``` 
	../mvnw spring-boot:run
```

#API Endpoint
##Calculate Payable Amount: /api/calculate

###Request Body:
{
  "items": [
    { "category": "groceries", "price": 200 },
    { "category": "other", "price": 0 }
  ],
  "originalCurrency": "USD",
  "targetCurrency": "PKR",
  "userType": "employee",
  "customerTenure": 1
}

###Response
- Response

##Run All Test Cases:
- Right click on ExchangeServiceTest -> Run As -> JUnit Test
- Approximately 22 JUnit tests have been created for the scenario described in the document. Run them all or one by one.


#Conclusion
- This application provides a simple solution for calculating payable amounts in different currencies while applying -
appropriate discounts.