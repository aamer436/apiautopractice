package stepDef;

import static org.hamcrest.Matchers.containsString;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import com.facebook.FacebookLoginPage;
import com.qa.global.factory.DriverFactory;
import com.qa.global.util.ApiUtils;
import com.qa.global.util.CommonUtils;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;

public class Facebookoauth2setup extends ApiUtils {
	public WebDriver driver;
	public FacebookLoginPage fb;
	public String authCodeUrl;
	public String authCode;
	public String accessToken;
	DriverFactory df=new DriverFactory();
	CommonUtils utils=new CommonUtils();
	@Given("users hits the authorization url for getting auth code")
	public void users_hits_the_authorization_url_for_getting_auth_code() {
		driver=df.init();
		df.launchApp(getPropValue("facebookUrl"),driver);
		fb=new FacebookLoginPage(driver);
		fb.logintoFacebook();
		fb.permissionGrant();
		authCodeUrl=df.getBrowserUrl(driver);
		System.out.println("authcode url :"+authCodeUrl);
		Assert.assertTrue(authCodeUrl.contains("code="));
		
	}

	@Then("user fetches the auth code from the response")
	public void user_fetches_the_auth_code_from_the_response() {
		 authCode=utils.getAuthCode(authCodeUrl);
		System.out.println("authCode value is :"+authCode);
		Assert.assertNotNull(authCode, "authCode is not generated");
	}

	@Then("create access token url with the generated auth code")
	public void create_access_token_url_with_the_generated_auth_code() {
		Response resp=getOauth2AccessTokenUsingAuthCode(getPropValue("fbBaseUrl"),authCode)
				.when().post("/v9.0/oauth/access_token");
		System.out.println(" outcome starts here");
		System.out.println(resp.jsonPath().prettify());
		resp.then().statusCode(200).assertThat().body(containsString("access_token")).log();
		accessToken=resp.jsonPath().getString("access_token");
		
	}

	@And("get user details by accessing facebook with authcode")
	public void get_user_details_by_accessing_facebook_with_authcode() {
		System.out.println("last method "+accessToken);
		Response resp=getoauth2RequestSpecBuilder(getPropValue("fbBaseUrl"),accessToken).when().get("/me");
		resp.then().statusCode(200).assertThat().body(containsString("name"),containsString("id")).log();
		System.out.println(resp.jsonPath().prettify());
	}

}