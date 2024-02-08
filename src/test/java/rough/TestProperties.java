
package rough;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



public class TestProperties {
	
	public static void main(String[] args) throws IOException, InterruptedException  {
		
		Properties Configu = new Properties();
		Properties OR = new Properties();
		FileInputStream fis = new FileInputStream(new File("src\\test\\resources\\properties\\Configuration.properties"));
		// We can use the fileInputStream method like below method also.
		//FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"src\\\\test\\\\resources\\\\properties\\\\Configuration.properties");
		Configu.load(fis);
		
		fis = new FileInputStream(new File("src\\test\\resources\\properties\\ObjectRepo.properties"));
		OR.load(fis);
		WebDriver driver = new ChromeDriver();
		
		driver.navigate().to(Configu.getProperty("testenvironment"));
		driver.manage().window().maximize();
		driver.findElement(By.cssSelector(OR.getProperty("bmlBtn_CSS"))).click();
		Thread.sleep(3000);
		driver.findElement(By.cssSelector(OR.getProperty("addCustBtn_CSS"))).click();
		Thread.sleep(3000);
		
	}

}


