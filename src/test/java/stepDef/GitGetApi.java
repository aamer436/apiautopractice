package stepDef;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.Map;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class GitGetApi {
	@Given("user launches the api using restassuredgit")
	public void user_launches_the_api_using_restassuredgit() {
		// http://api.plos.org/search?q=title:DNA
		RestAssured.baseURI = "https://api.github.com";
		String resp = given().relaxedHTTPSValidation().header("Accept", "application/vnd.github.mercy-preview+json")
				.queryParam("q", "tetris+language:assembly").queryParam("sort", "stars").queryParam("order", "desc")
				.log().all().when().get("/search/repositories").then().assertThat().statusCode(200).extract()
				.asString();
		JsonPath js = new JsonPath(resp);
		int i = js.getInt("items.size()");
		System.out.println("owner array: "+js.getJsonObject("items[0].owner"));
		System.out.println(" item json array size is :" + i);
		for (int j = 0; j < 1; j++) {
			System.out.println("********within doc " + j + "**********");
			Map<String, Object> firstMap = js.getMap("items[" + j + "].owner");
			System.out.println("firstMap size is :" + firstMap.size());
//			for (Object str : firstMap.keySet()) {
//				System.out.println(str + " : " + firstMap.get(str).toString());
//			}
			for(Map.Entry<String, Object> m:firstMap.entrySet()) {
				System.out.println(m.getKey()+" : "+m.getValue());
			}
		}

	}

	@When("conditionAgit")
	public void conditionagit() {
		// Write code here that turns the phrase above into concrete actions
		System.out.println("within conditionA method");
	}

	@Then("resultAgit")
	public void resultagit() {
		// Write code here that turns the phrase above into concrete actions
		System.out.println("within resulta method");
	}

	@Then("resultBgit")
	public void resultbgit() {
		// Write code here that turns the phrase above into concrete actions
		System.out.println("within resultb method");
	}
}