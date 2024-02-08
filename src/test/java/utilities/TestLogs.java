
package utilities;




import java.util.Date;

import org.apache.log4j.PropertyConfigurator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import base.TestBase;



public class TestLogs extends TestBase{
	
	
	public static Logger log = LogManager.getLogger(TestLogs.class.getName());

	public static void main(String[] args) {
		
Date d = new Date();
		
		System.setProperty("current.date", d.toString().replace(":", "_").replace(" ", "_"));
		PropertyConfigurator.configure(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\log4j.properties");
		
		log.info("This is the information log");
		
		log.error("Here the error logs will be printed");

		
		//String log4jConfPath = "src\\test\\resources\\properties\\log4j.properties";
		//PropertyConfigurator.configure(log4jConfPath);
		
		

	}

}


