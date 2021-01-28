package stepDef;

import static io.restassured.RestAssured.given;

import org.testng.Assert;

import com.qa.global.util.ApiUtils;
import com.supportingclasses.reqres.ObjectParser;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;

public class ReqResAPIParsing extends ApiUtils {
	@Given("Hit Get API and verify the response")
	public void hit_get_api_and_verify_the_response() {
		Response resp=given().relaxedHTTPSValidation().spec(requestSpecification(getPropValue("reqresurl"))).log().all().when().get("/api/unknown");
		resp.then().log().all();
		ObjectParser res = resp.as(ObjectParser.class);
		// ObjectParser res=given().accept(ContentType.JSON).expect().defaultParser(Parser.JSON)
		// .when().get("/api/unknown").as(ObjectParser.class);
		System.out.println(res.getSupport().getUrl());
		System.out.println(res.getSupport().getText());
		System.out.println(res.getPage());
		System.out.println(res.getPer_page());
		System.out.println(res.getTotal());
		System.out.println(res.getTotal_pages());
		for (int i = 0; i < res.getData().size(); i++) {
			System.out.println("In array number-->  " + i);
			System.out.println(res.getData().get(i).getId());
			System.out.println(res.getData().get(i).getPantone_value());
			System.out.println(res.getData().get(i).getYear());
			System.out.println(res.getData().get(i).getName());
			System.out.println(res.getData().get(i).getColor());
			System.out.println("end of array " + i);
		}
		
		Assert.assertEquals(res.getTotal(),12);
	}
	@Then("verify response code is fine")
	public void verify_response_code_is_fine() {
		System.out.println("test is complete");
	}
	
}
