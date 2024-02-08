package testcases;

import java.util.Hashtable;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

import base.TestBase;
import utilities.TestUtil;

public class OpenAccountTest extends TestBase{

	
	@Test(dataProviderClass= TestUtil.class, dataProvider="dp")
	public void openAccountTest(Hashtable<String, String> data) throws InterruptedException {
		
		// By using Hashtable we can reduce parametrization of any method  
		// and it will replace this paramters String customer, String currency
		// When ever you want to skip or not to run any specific test for that
		//you have to write this code of Skip Exception in that perticular class
		
		if (!TestUtil.isTestRunnable("openAccountTest", excel)) {
			
			throw new SkipException("Skiping the test "+"openAccountTest"+ " as the Run Mode is NO");
		}

		
		
		click("openAccount_CSS");
		select("customer_CSS", data.get("customer"));
		select("currency_CSS", data.get("currency"));
		click("process_CSS");
		
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		alert.accept();
		
		
		
	}
	
	

	
}
