package testcases;

import java.util.Hashtable;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;
import base.TestBase;
import utilities.TestUtil;

public class AddCustomerTest extends TestBase{

	
	@Test(dataProviderClass= TestUtil.class, dataProvider="dp")
	public void addCustomerTest(Hashtable<String, String> data) throws InterruptedException  {
		
		// By using Hashtable we can reduce parametrization of any method
 		// prameters are String firstName, String lastName, String postCode, String alertTest, String Runmode
		// When ever you want to skip or not to run any specific test for that
				//you have to write this code of Skip Exception in that perticular class
				
				if (!data.get("Runmode").equals("Y")) {
					
					throw new SkipException("Skiping the test case as the Run Mode for data set is NO");
				}

		
		click("addCustBtn_CSS");
		type("firstNmae_CSS", data.get("firstName"));
		type("lastNmae_XPATH",data.get("lastName"));
		type("postCode_CSS",data.get("postCode"));
		click("addBtn_CSS");
		
		
		/*
		// We can write in the above ways for click and given input operation 
		// As we declared all locators in Base Class
		driver.findElement(By.cssSelector(OR.getProperty("addCustBtn_CSS"))).click();
		driver.findElement(By.cssSelector(OR.getProperty("firstNmae_CSS"))).sendKeys(firstName);;
		driver.findElement(By.cssSelector(OR.getProperty("lastNmae_XPATH"))).sendKeys(lastName);
		driver.findElement(By.cssSelector(OR.getProperty("postCode_CSS"))).sendKeys(postCode);
		driver.findElement(By.cssSelector(OR.getProperty("addBtn_CSS"))).click();
		*/
		
		
		Thread.sleep(3000);
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert.getText().contains(data.get("alertTest")));
		log.debug("Customer added sucessfully");
		alert.accept();
		
		
			
	}
	
	
	
	
	
	/*
// Insted of writing this code for each time when data reading is done, we write a common code in Testutil Class
	
	@DataProvider
	public Object[] [] getData(){
		
		//it is used to read the excel file
		
		String sheetName = "AddCustomerTest";
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);
		
		Object [] [] data = new Object[rows-1] [cols];
		
		for (int rowsNum =2; rowsNum<= rows; rowsNum++) {
			
			for (int colsNum =0; colsNum<= cols; colsNum++) {
				
				data [rowsNum-2] [colsNum] = excel.getCellData(sheetName, rowsNum, colsNum);
			}
			
		}
 		
		return data;
		
	}
	*/

	
	
}
