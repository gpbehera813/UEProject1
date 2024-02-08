package rough;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import utilities.MonitoringMail;
import utilities.TestConfig;

public class TestHostAdd {
	
	public static void main(String[] args) throws UnknownHostException, AddressException, MessagingException {
		
// This Code is used to send extent reports to desired email by using TestConfig Class & Monitoring Mail Class
		
		MonitoringMail mail = new MonitoringMail();
		String messageBody ="http://"+ InetAddress.getLocalHost().getHostAddress()+":8080/job/UEProject1(Data%20Driven%20Framework)/Extent_20Reports/";
		System.out.println(messageBody);
		mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, messageBody);
		
	}

}
