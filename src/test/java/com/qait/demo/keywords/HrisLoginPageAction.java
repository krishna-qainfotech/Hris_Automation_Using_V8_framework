package com.qait.demo.keywords;

import org.openqa.selenium.WebDriver;

import com.qait.automation.getpageobjects.GetPage;
import com.qait.automation.utils.YamlReader;

public class HrisLoginPageAction extends GetPage {
	
	WebDriver driver;
public HrisLoginPageAction(WebDriver driver) {
	super(driver, "homePage");
}


public void clickOnLoginPanel() {
	wait.waitForPageToLoadCompletely();
    isElementDisplayed("btn_login_container");
    element("btn_login_container").click();
    logMessage("Clicked On login container");
}

public void submitLoginCredential() {

    isElementDisplayed("txt_login");
    element("txt_login").clear();
    element("txt_login").sendKeys(YamlReader.getData("username_hris"));
    isElementDisplayed("txt_password");
    element("txt_password").clear();
    element("txt_password").sendKeys(YamlReader.getData("password_hris"));
    isElementDisplayed("btn_sign_in");
    element("btn_sign_in").click();	
}
}
