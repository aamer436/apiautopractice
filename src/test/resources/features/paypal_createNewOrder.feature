Feature: create an order on paypal

Scenario: scenario is for creating an order on paypal and verifying the order details
Given perform authentication with paypal using oauth2 and get access token
Then create an order on paypal
Then get details of newly created order