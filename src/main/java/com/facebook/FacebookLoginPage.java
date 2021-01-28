package com.facebook;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.global.util.ApiUtils;

public class FacebookLoginPage extends ApiUtils {
public static WebDriver driver;
public FacebookLoginPage(WebDriver driver) {
this.driver=driver;
}
By userNameLocator=By.xpath("//*[@placeholder='Email address or phone number']");
By pwdLocator=By.xpath("//*[@placeholder='Password']");
By loginButton=By.xpath("//*[@name='login']");
By fbPermissionButton=By.xpath("//*[@name='__CONFIRM__'][@type='submit']");
public void logintoFacebook() {
entertext(userNameLocator,getPropValue("fbUserName"));
entertext(pwdLocator,getPropValue("fbPassword"));
clickAnElement(loginButton);
}
public void permissionGrant() {
try {
//Thread.sleep(10000);
//acceptAlert();
Thread.sleep(5000);
} catch (InterruptedException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
clickAnElement(fbPermissionButton);
}
public void entertext(By locator,String textInput) {
driver.findElement(locator).sendKeys(textInput);
}
public void clickAnElement(By locator) {
driver.findElement(locator).click(); }
public void acceptAlert() {
driver.switchTo().alert().accept(); }
}