package com.qa.global.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {
public WebDriver driver=null;
public WebDriver init() {
WebDriverManager.chromedriver().setup();
ChromeOptions options = new ChromeOptions();
options.addArguments("--disable-notifications");
WebDriver driver=new ChromeDriver(options);
return driver;
}
public void launchApp(String url,WebDriver driver) {
//this.driver=driver;
driver.get(url);
}
public void closeBrowser(WebDriver driver) {
driver.close();
}
public String getBrowserUrl(WebDriver driver) {
return driver.getCurrentUrl();
}
}