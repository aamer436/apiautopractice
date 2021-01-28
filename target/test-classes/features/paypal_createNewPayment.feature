Feature: Setup paypal authentication and submit payment request

Scenario: scenario is for setting up paypal authentication using rest assured
Given perform authentication with paypal using oauth2 and get access token
Then create payment record
Then parse data from json response for create payment request