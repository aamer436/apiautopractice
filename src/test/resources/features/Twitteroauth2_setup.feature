Feature: This test case setups oauth2.0 authentication to twitter

Scenario: Verify that twitter login is successful using oauth2.0 and user is able to read tweets
Given user authenticates twitter using access token logic
Then verify that user able to read tweets once authentication is successful
