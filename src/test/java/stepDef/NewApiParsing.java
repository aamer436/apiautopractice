package stepDef;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class NewApiParsing {
	@Given("user launches the api using restassured")
	public void user_launches_the_api_using_restassured() {
		// http://api.plos.org/search?q=title:DNA
		RestAssured.baseURI="http://api.plos.org";
//	String resp=	given().relaxedHTTPSValidation().header("Accept","application/json").queryParam("q", "title:DNA")
//		.log().all()
//		.when().get("/search")
//		.then().assertThat().statusCode(200).extract().asString();
	Response respA=	given().relaxedHTTPSValidation().header("Accept","application/json").queryParam("q", "title:DNA")
			.log().all()
			.when().get("/search");
			//ValidatableResponse a=respA.then().assertThat().statusCode(200);
			//System.out.println("response code is :"+a.toString());
			Assert.assertEquals(respA.getStatusCode(), 200);
	JsonPath js=respA.jsonPath();
//	JsonPath js=new JsonPath(resp);
	int i=js.getInt("response.docs.size()");
	System.out.println(" i value is :"+i);

	for(int j=0;j<i;j++) {
	System.out.println("********within doc "+j+"**********");
	int k=js.getInt("response.docs["+j+"].author_display.size()");
	int p=js.getInt("response.docs["+j+"].abstract.size()");
	System.out.println(" k value is :"+k+" && p value is :"+p);
//		for(int m=0;m<k;m++) {
//			System.out.println("value is :"+js.getString("response.docs["+j+"].author_display["+m+"]"));
//		}
		List<String> arg=js.getList("response.docs["+j+"].author_display");
		for(String z:arg) {
			System.out.println("new Approach to fetch author_display string array :"+z);
		}
//		for(int q=0;q<p;q++) {
//			System.out.println("abstract value is :"+js.getString("response.docs["+j+"].abstract["+q+"]"));
//		}
		List<String> argb=js.getList("response.docs["+j+"].abstract");
		for(String z:argb) {
			System.out.println("new Approach to fetch abstract array :"+z);
		}
	}
	}

	@When("conditionA")
	public void conditiona() {
		// Write code here that turns the phrase above into concrete actions
		System.out.println("within conditionA method");
	}

	@Then("resultA")
	public void resulta() {
		// Write code here that turns the phrase above into concrete actions
		System.out.println("within resulta method");
	}

	@Then("resultB")
	public void resultb() {
		// Write code here that turns the phrase above into concrete actions
		System.out.println("within resultb method");
	}
}