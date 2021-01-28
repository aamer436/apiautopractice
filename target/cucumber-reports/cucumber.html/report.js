$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/resources/features/paypal_createNewOrder.feature");
formatter.feature({
  "name": "create an order on paypal",
  "description": "",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "scenario is for creating an order on paypal and verifying the order details",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "perform authentication with paypal using oauth2 and get access token",
  "keyword": "Given "
});
formatter.match({
  "location": "Paypal_Steps.perform_authentication_with_paypal_using_oauth2_and_get_access_token()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "create an order on paypal",
  "keyword": "Then "
});
formatter.match({
  "location": "Paypal_Steps.create_an_order_on_paypal()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "get details of newly created order",
  "keyword": "Then "
});
formatter.match({
  "location": "Paypal_Steps.get_details_of_newly_created_order()"
});
formatter.result({
  "status": "passed"
});
});