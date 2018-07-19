package com.qait.demo.tests;

import static com.qait.automation.utils.YamlReader.getYamlValue;

import org.junit.experimental.theories.suppliers.TestedOn;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qait.automation.TestSessionInitiator;

import junit.framework.Assert;

public class HrisTestFile {
	TestSessionInitiator  testsessioninitiator;
	
	//this will load all the action class .
	@BeforeTest
	public void loadProjectFile() {
		System.out.println("This is the class name : "+this.getClass().getName());
		 testsessioninitiator = new TestSessionInitiator(this.getClass().getName());
	}
	
  //Verify the login credential is correct or not 
  @Test
  public void IsHrisLoginSuccessfull()  {
         testsessioninitiator.launchApplication();
         testsessioninitiator.hrisloginpageaction.clickOnLoginPanel();
         testsessioninitiator.hrisloginpageaction.submitLoginCredential();
         Assert.assertEquals(testsessioninitiator.timesheetaction.getProfileName(), "Krishna");
  }
  
 // Verify the page is time sheet by url or title of the page or element on the page
 @Test(dependsOnMethods ={"IsHrisLoginSuccessfull"})
  public void IsTimeSheetPageDisplayed() throws InterruptedException {
	  testsessioninitiator.timesheetaction.clickOnMyProfile();
	  Assert.assertEquals(testsessioninitiator.timesheetaction.VerifyByEmployeeId(), "4495");
  }
  
  //verify the name match with the yaml file in the profile
  @Test(dependsOnMethods = {"IsTimeSheetPageDisplayed"})
  public void checkPersonalDetailsonHris() {
	String username =  testsessioninitiator.persondetailaction.getFirstName();
	String expectedusername = "Krishna Chauhan";
	Assert.assertTrue(username.equals(expectedusername));
  }
  
  @AfterTest
  public void closeBrowser() throws InterruptedException {
	  Thread.sleep(5000);
	  testsessioninitiator.closeBrowserSession();
  }
}
