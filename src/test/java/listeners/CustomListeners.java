package listeners;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;

import com.relevantcodes.extentreports.LogStatus;

import base.TestBase;
import utilities.MonitoringMail;
import utilities.TestConfig;
import utilities.TestUtil;

public class CustomListeners extends TestBase implements ITestListener, ISuiteListener{

	
	String messageBody;
	
	public void onTestStart(ITestResult arg0) {
		
		test = report.startTest(arg0.getName().toUpperCase());
		
		// runmode - Y
				
		
	}

	
	public void onTestSuccess(ITestResult  arg0) {
		
		test.log(LogStatus.PASS, arg0.getName().toUpperCase()+"PASS");		
		report.endTest(test);
		report.flush();
		
		
		
	}

	
	public void onTestFailure(ITestResult arg0) {
		
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		
		try {
			TestUtil.captureScreenshot();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
		test.log(LogStatus.FAIL, arg0.getName().toUpperCase()+"Failed");
		
		test.log(LogStatus.FAIL, test.addScreenCapture(TestUtil.screenshotName));
		
		Reporter.log("Click to see screenshot");  // To add message to the report for this test
				
		Reporter.log("<a target=\"_Blank\" href="+TestUtil.screenshotName+">Screenshot</a>");
				
		Reporter.log("<br>"); // This code will add a new line or give a break
		Reporter.log("<br>");
		
		Reporter.log("<a target=\"_Blank\" href="+TestUtil.screenshotName+"><img src="+TestUtil.screenshotName+" height=300 width=300></img></a>");
				
		report.endTest(test);
		report.flush();
		
		
		
	}

	
	public void onTestSkipped(ITestResult arg0) {
		
		test.log(LogStatus.SKIP, arg0.getName().toUpperCase()+"Skipped the test as the Run Mode is NO ");
		report.endTest(test);
		report.flush();
		
	}

	
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void onStart(ISuite arg0) {
		
	
		
	}

	
	public void onFinish(ISuite arg0) {
		
		MonitoringMail mail = new MonitoringMail();
		
		try {
			messageBody = "http://"+ InetAddress.getLocalHost().getHostAddress()+"8080/job/UEProject1(Data%20Driven%20Framework)/Extent_20Reports/";
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, messageBody);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	
	
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
	
		
	}

	
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}
	
	

}
