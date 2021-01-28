package stepDef;

import static io.restassured.RestAssured.given;

import java.util.Map;

import com.qa.global.util.ApiUtils;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Twitter_ExploreTwitter extends ApiUtils{
	public static RequestSpecification twitterReq;
	@Given("User logs in and post a tweet")
	public void user_logs_in_and_post_a_tweet() {
		twitterReq=twitterRequestSpecificationBuilder(getPropValue("twitterurl"));
		Response resp=twitterReq.when().post("/1.1/statuses/update.json?status=test+tweet+ok+test");
		System.out.println(resp.jsonPath().prettify());

	}

	@Then("Tweet is displayed on user's timeline")
	public void tweet_is_displayed_on_user_s_timeline() {
		System.out.println("method b");
		//List<String> tweetIdList= new ArrayList<String>();
		//tweetIdList.add("1333682009701371905");
		//tweetIdList.add("1333690640899649536");
		Response resp=twitterReq
				//.queryParam("id",tweetIdList )
				//.queryParam("id", "1333682009701371905")
				//.queryParam("id", "1333690640899649536")
				.when().get("/1.1/statuses/lookup.json?id=1333682009701371905,1333690640899649536");
		System.out.println(resp.jsonPath().prettify());
	}
	
	
	private static String woeid;
	//RequestSpecification twitterReq;
	@Given("Fetch list of all available trends and get woeid for Country {string}")
	public void fetch_list_of_all_available_trends_and_get_woeid_for_Country(String countryName) {
		twitterReq=twitterRequestSpecificationBuilder(getPropValue("twitterurl"));
		Response resp=twitterReq.when().get("/1.1/trends/available.json");
		//System.out.println(resp.jsonPath().prettify());
		JsonPath js=resp.jsonPath();
		//Json
		int count=js.getInt("size()");
		//String woeid;
	//	List<JsonList> result
		System.out.println("size of the available trends array is :"+count);
		for(int i=0;i<count;i++) {
			//System.out.println("country :"+js.getString("["+i+"].country"));
			if(js.getString("["+i+"].country").equalsIgnoreCase(countryName) && js.getString("["+i+"].parentid").equalsIgnoreCase("1")) {
				woeid=js.getString("["+i+"].woeid");
				System.out.println("woe id matched at ith position: "+i+" with value :"+woeid);
			}
		}

	}

	@Then("Display trends for a given country")
	public void display_trends_for_a_given_country() {
		System.out.println("Dummy method with woeid :"+woeid);
		Response resp=twitterReq.queryParam("id", woeid).when().get("/1.1/trends/place.json");
		//System.out.println(resp.jsonPath().prettify());
		JsonPath js=resp.jsonPath();
		//Json
		int count=js.getInt("size()");
		int sizeOfTrendsArray=js.getInt("[0].trends.size()");
		System.out.println("size of the available trends array is :"+count);
		//Map<String, Object> firstMap
		for(int i=0;i<sizeOfTrendsArray;i++) {
			//System.out.println( "response :"+js.getString("[0].trends["+i+"]"));
			System.out.println("printing "+i+" th JSON Array");
			Map<String, Object> firstMap=js.getMap("[0].trends["+i+"]");
			System.out.println("firstMap size is :" + firstMap.size());
			System.out.println("Hashtag name= "+firstMap.get("name")+" Hashtag count at the moment: "+firstMap.get("tweet_volume"));
			System.out.println("*************");
			
			//=js.getJsonObject("[0].trends["+i+"]");
			
			
		}
	}
	
	public static String access_token;
	@Given("user authenticates twitter using access token logic")
	public void user_authenticates_twitter_using_access_token_logic() {
		Response resp=given().relaxedHTTPSValidation().urlEncodingEnabled(false).queryParam("grant_type", "client_credentials").auth().preemptive().basic(getPropValue("apiKey"), getPropValue("apiSecret"))
		.when().post("https://api.twitter.com/oauth2/token");
		System.out.println(" response is :"+resp.jsonPath().prettify());
		JsonPath js=resp.jsonPath();
		access_token=js.getString("access_token");
		System.out.println("access token with oauth2 is :"+access_token);
		}

	@Then("verify that user able to read tweets once authentication is successful")
	public void verify_that_user_able_to_read_tweets_once_authentication_is_successful() {
		System.out.println(" in second method");
		Response res=given().relaxedHTTPSValidation().urlEncodingEnabled(false).auth().oauth2(access_token).when().post("https://api.twitter.com/1.1/statuses/update.json?status=test+tweet+test");
		System.out.println("*******use of access_token");
		System.out.println(res.jsonPath().prettify());
	}
	
}