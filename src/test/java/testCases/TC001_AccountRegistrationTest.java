package testCases;

import java.security.SecureRandom;
import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.text.RandomStringGenerator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObjects.AccountRegisterPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass  {
	
	
	@Test(groups = {"regression","Master"})
	public void testAccountRegister() {
		logger.info(" ***** Starting TC001_AccountRegistrationTest *****" );
		try
		{
		HomePage hp = new HomePage(driver);
		
		hp.clickAccount();
		logger.info("clicking on account link");
		
		hp.clickRegister();
		logger.info("clicking on register link");
		
		
		AccountRegisterPage regpage = new AccountRegisterPage(driver);
		
		logger.info("providing customer details ");
		regpage.setFirstName(getRandomAlphabets(5).toUpperCase());
		regpage.setLastName(getRandomAlphabets(5).toUpperCase());
		regpage.setMail(getRandomAlphabets(5) + "@gmail.com");
		regpage.setPhoneNumber(getRandomNumbers(9));
		
		String password = getRandomAlphaNumericWithSpecialChar(6,'@');
		regpage.setPassword(password);
		regpage.setConfrmPassword(password);
		regpage.clickAgree();
		regpage.clickContinue();
		
		logger.info("validating confirmation message ");
		
		String confmessage = regpage.getconfmMesg();
		
		if(confmessage.equals("Your Account Has Been Created!")) {
			Assert.assertTrue(true);
		}
		else {
			logger.error("test failed");
			logger.debug("debug logs..");
			Assert.assertTrue(false);
		}
		
		} catch(Exception e) {
			Assert.fail();
		}
	
		logger.info(" ***** Finished TC001_AccountRegistrationTest *****" );

	}
}
