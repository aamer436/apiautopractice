Feature: Verify the oauth2 setup for facebook

Scenario: The scenario validates the flow for oauth2 for FB
Given users hits the authorization url for getting auth code
Then user fetches the auth code from the response
Then create access token url with the generated auth code
And get user details by accessing facebook with authcode