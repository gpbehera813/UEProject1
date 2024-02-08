package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.jsoup.select.Evaluator.IsEmpty;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utilities.ExcelReader;
import utilities.ExtentManager;
import utilities.TestUtil;




public class TestBase {
	
	/*  
	 * Initializing of WebDriver -- done
	 *  Properties                -- done
	 *  logs                      -- log4j Jar files is required, log4j.properties, logger
	 *  extent reports            
	 *  DB
	 *  Excel
	 *  Implementing of Mailing
	 *  ReportNG, Extent Report
	 *  Jenkins
	 */
	
	public static WebDriver driver;
	public static Properties Config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel = new ExcelReader(System.getProperty("user.dir")+"\\src\\test\\resources\\excel\\testData.xlsx");
	public static WebDriverWait wait;
	public ExtentReports report = ExtentManager.getInstance();
	public static ExtentTest test;
	public static String browser;
	
	
	
	
	@BeforeSuite
	public void setUp() throws IOException  {
		
		if (driver==null) {
			
			PropertyConfigurator.configure(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\log4j.properties");
			
			fis = new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\Configuration.properties"));
			Config.load(fis);
			
			log.debug("Configuration file loaded !!!");
			
			// We can use the fileInputStream method like below method also.
			//FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"src\\test\\resources\\properties\\Configuration.properties");
			fis = new FileInputStream(new File(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\ObjectRepo.properties"));
			OR.load(fis);
			log.debug("Object Repo file loaded !!!");
			
			
		if (System.getenv("browser")!= null && !System.getenv("browser").isEmpty()) {
			
			browser = System.getenv("browser");
		
		}else {
			
			browser = Config.getProperty("browser");
		
		}
		
		Config.setProperty("browser", browser);
		
					
		if (Config.getProperty("browser").equals("Chrome")) {
		
			// We can use this below method if we are not using latest version of Eclipse &
			// for that we have to all browser drivers into executables file or folder 
			// System.setProperty("Webdriver.Chrome.driver",System.getProperty("user.dir")+"src\\test\\resources\\executables\\chromedriver.exe");
		
			driver = new ChromeDriver();
			
			log.debug("Chrome browser launched !!!");
		
		}else if (Config.getProperty("browser").equals("Edge Driver")) {
			
			driver = new EdgeDriver();
			
			log.debug("Microsoft Edge browser is launched !!!");
		
		}else {
			
			driver = new InternetExplorerDriver();
		}
		
		driver.navigate().to(Config.getProperty("testenvironment"));
		log.debug("Navigated to: "+Config.getProperty("testenvironment"));
		driver.manage().window().maximize();
		
		// We can add implicity wait like below method
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(Config.getProperty("waitTime")), TimeUnit.SECONDS);
		//Thread.sleep(3000);
		wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		
		
		}	
		
		
	}
	
	
	public void click(String locator) {
		
		if (locator.endsWith("_CSS")) {
		
			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
			
		}else if(locator.endsWith("_XPATH")) {
			
			driver.findElement(By.xpath(OR.getProperty(locator))).click();
			
		}else if(locator.endsWith("_TAGNAME")) {
			
			driver.findElement(By.tagName(OR.getProperty(locator))).click();
			
		}else if(locator.endsWith("_ID")) {
			
			driver.findElement(By.id(OR.getProperty(locator))).click();
			
		}else if(locator.endsWith("_CLASSNAME")) {
			
			driver.findElement(By.className(OR.getProperty(locator))).click();
			
		}else {
			
			driver.findElement(By.name(OR.getProperty(locator))).click();
		}
		
		
		//As we declared individually by separate locators by using if else statemnt
		// driver.findElement(By.cssSelector(OR.getProperty(locator))).click(); 
		
		test.log(LogStatus.INFO,"Clicked on : "+locator);
		
	}
	
	
public void type(String locator,String value) {
		
	if (locator.endsWith("_CSS")) {
		
		driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
		
	}else if (locator.endsWith("_XPATH")) {
		
		driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
		
	}else if (locator.endsWith("_TAGNAME")) {
		
		driver.findElement(By.tagName(OR.getProperty(locator))).sendKeys(value);
		
	}else if (locator.endsWith("_ID")) {
		
		driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
		
	}else if (locator.endsWith("_CLASSNAME")) {
		
		driver.findElement(By.className(OR.getProperty(locator))).sendKeys(value);
		
	}else {
		
		driver.findElement(By.name(OR.getProperty(locator))).sendKeys(value);
		
	}
		
	
		//As we declared individually by separate locators by using if else statemnt
		//driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
		
		test.log(LogStatus.INFO,"Typed in : "+locator+"Entered value as :"+value);
		
	}

	
			static WebElement dropdown;

	public static void select(String locator, String value) {
		
		
		if (locator.endsWith("_CSS")) {
			
			dropdown= driver.findElement(By.cssSelector(OR.getProperty(locator)));
			
		}else if (locator.endsWith("_XPATH")) {
			
			dropdown= driver.findElement(By.xpath(OR.getProperty(locator)));
			
		}else if (locator.endsWith("_TAGNAME")) {
			
			dropdown= driver.findElement(By.tagName(OR.getProperty(locator)));
			
		}else if (locator.endsWith("_ID")) {
			
			dropdown= driver.findElement(By.id(OR.getProperty(locator)));
			
		}else if (locator.endsWith("_CLASSNAME")) {
			
			dropdown= driver.findElement(By.className(OR.getProperty(locator)));
			
		}else {
			
			dropdown= driver.findElement(By.name(OR.getProperty(locator)));
			
		}
		
		Select select = new Select(dropdown);
		select.selectByVisibleText(value);
			
		test.log(LogStatus.INFO,"Selecting from dropdown : "+locator+"value as :"+value);

			
		
	}





	
	public static void verifyEquals(String expected, String actual) throws IOException {
		
		try {
			
			Assert.assertEquals(actual, expected);
			
		}catch(Throwable t) {
			
			TestUtil.captureScreenshot();
			// This code is for report NG report 
			// if failure occure its will generate failure message & screenshot and 
			//it will further execute the remaining codes  
			Reporter.log("<br>"+"Verification failure : "+ t.getMessage()+"<br>");
			Reporter.log("<a target=\"_Blank\" href="+TestUtil.screenshotName+"><img src="+TestUtil.screenshotName+" height=300 width=300></img></a>");
			Reporter.log("<br>");
			Reporter.log("<br>");
			
			
			// same concept report generation for Extent reports if code fails
			
			test.log(LogStatus.FAIL, "Verification failed with exception :"+ t.getMessage());
			
			test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));
			
			
			
			
			
		}
		
		
			
	}




	
	public boolean isElementPresent(By by) {
		
		try {
			driver.findElement(by);
			return true;
			
		}catch (NoSuchElementException e) {
			
			return false;
		
		}
		
	}
	

	

	@AfterSuite
	public void tearDown() {
		
		if (driver!= null) {
			driver.quit();
		}
		
	log.debug("Test Execution completed");
		
	}
	
 
	
}
