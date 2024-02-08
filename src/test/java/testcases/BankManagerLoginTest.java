package testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.TestBase;

public class BankManagerLoginTest extends TestBase{
	
	@Test
	public void bankManagerLoginTest() throws InterruptedException {
		
		// for adding hyperlink to reportng report
		//System.setProperty("org.uncommons.reportng.escape-output", "false"); 
		
		log.debug("Inside Login Test ");
		
		click("bmlBtn_CSS");
		/*
		// We can write in the above ways for click and given input operation 
		// As we declared all locators in Base Class
		driver.findElement(By.cssSelector(OR.getProperty("bmlBtn_CSS"))).click();
		*/
		
		//Thread.sleep(3000);
		Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("addCustBtn_CSS"))), "Login not successful");
		//Assert.assertTrue(true);
		log.debug("Login executed sucessfully");
		
		
		
		
		
		
		/*
		It is used for only one test but it can be implemented in nth method by using Listeners 
		
		// it is added to give the message whether upper code is successfully implemented
		   
		Reporter.log("Login executed sucessfully");  // To add message to the report for this test
		
		// To add a hyperlink like Screenshot to the report
		//and target=\"_Blank\" is used to open the hyperlink in new tab
		Reporter.log("<a target=\"_Blank\" href=\"C:\\Users\\HP\\OneDrive\\Pictures\\Screenshots\\BMLoginPage.png\">Screenshot</a>");
		
		Reporter.log("<br>"); // This code will add a new line or give a break
		
		
		// Instead of screenshot if we write this code <img src=\"C:\\Users\\HP\\OneDrive\\Pictures\\Screenshots\\BMLoginPage.png\" height=300 width=300></img>
		// It will generate image or thumb nail in reportng report
		Reporter.log("<a target=\"_Blank\" href=\"C:\\Users\\HP\\OneDrive\\Pictures\\Screenshots\\BMLoginPage.png\"><img src=\"C:\\Users\\HP\\OneDrive\\Pictures\\Screenshots\\BMLoginPage.png\" height=300 width=300></img></a>");
		
		*/
		
		
		
		
		
	}
	
	
}
