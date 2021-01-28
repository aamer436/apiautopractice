package stepDef;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

import com.paypal.base.Items;
import com.paypal.base.OrderObject;
import com.paypal.base.PayPal_PaymentObj;
import com.qa.global.util.ApiUtils;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;

public class Paypal_Steps extends ApiUtils {
	public String accessToken;
	PayPal_PaymentObj result;
	private String orderID;
	@Given("perform authentication with paypal using oauth2 and get access token")
	public void perform_authentication_with_paypal_using_oauth2_and_get_access_token() {
	Response resp=getOauth2AccessTokenUsingClientCreds(getPropValue("paypalappurl"))
				.when().post("https://api-m.sandbox.paypal.com/v1/oauth2/token");
		System.out.println(resp.jsonPath().prettify());
		resp.then().statusCode(200).assertThat().body(containsString("access_token")).log();
		accessToken=resp.jsonPath().getString("access_token");
	}

	@Then("create payment record")
	public void create_payment_record() {
		System.out.println("methodB");
		Response resp=getoauth2RequestSpecBuilder(getPropValue("paypalappurl"),accessToken)
				.when().body(PayPal_PaymentObj.createPaymentBody()).post("/payments/payment");
		System.out.println("POJO resp starts here");
		System.out.println("*******************8");
		System.out.println(resp.jsonPath().prettify());
		//resp.then().statusCode(201).assertThat().body(containsString("id")).body(arguments, responseAwareMatcher).log();
		result=resp.as(PayPal_PaymentObj.class);
	}

	@Then("parse data from json response for create payment request")
	public void parse_data_from_json_response_for_create_payment_request() {
		System.out.println("methodC");
		System.out.println("create time is :"+result.getCreate_time());
		System.out.println("id :"+result.getId()+" and state :"+ result.getState());
		Items item=result.getTransactions().get(0).getItem_List().getItems().get(0);
		System.out.println("item details are: "+
		item.getCurrency()+" "+item.getTax());
		System.out.println("payment method :"+result.getTransactions().get(0).getPayment_options().getAllowed_payment_method());
		System.out.println("recurring flag :"+result.getTransactions().get(0).getPayment_options().isRecurring_flag());
		System.out.println("shpping discount :"+result.getTransactions().get(0).getAmount().getDetails().getShipping_discount());
		System.out.println("shpping discount :"+result.getTransactions().get(0).getDescription());
		

	}
	@Then("create an order on paypal")
	public void create_an_order_on_paypal() {
		Response resp=getoauth2RequestSpecBuilder(getPropValue("paypalv2"),accessToken).log().all()
				.when().body(OrderObject.createOrderRequest()).post("/checkout/orders");
		System.out.println("Create Order response "+resp.jsonPath().prettify());
		resp.then().statusCode(201).body("status",equalTo("CREATED")).body(containsString("id")).log();
	orderID=resp.jsonPath().getString("id");
	System.out.println("order id is :"+orderID);
	}
	
	@Then("get details of newly created order")
	public void get_details_of_newly_created_order() {
	Response resp=getoauth2RequestSpecBuilder(getPropValue("paypalv2"),accessToken).pathParam("orderid", orderID).log().all()
				.when().get("/checkout/orders/{orderid}");
		System.out.println("get order details :"+ resp.jsonPath().prettify());
		resp.then().statusCode(200).body("status",equalTo("CREATED")).body(containsString("id")).log();
		String newOrderid=resp.jsonPath().getString("id");
		resp.then().statusCode(200).body("id",equalTo(newOrderid)).log();
	}
	
	@Then("testapipostc")
	public void testc() {
		Response resp=getoauth2RequestSpecBuilder(getPropValue("paypalv2"),accessToken).pathParam("orderid", orderID).log().all()
				.when().body("purchase_units[].amount: 20").patch("/checkout/orders/{orderid}");
		System.out.println("patching more details to newly created order :"+ resp.jsonPath().prettify());
	}
	
	
	
	
	
}