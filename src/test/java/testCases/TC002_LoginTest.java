package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {
	
	@Test(groups = {"sanity","Master"})
	public void testLogin() { 
		logger.info("***** Starting TC002_LoginTest ***** ");
	
		try {
		HomePage hp = new HomePage(driver);
		hp.clickAccount();
		logger.info("clicking on link account");
		hp.clickLogin();
		logger.info("clicking on link login");
		
		logger.info("Providing user details");
		LoginPage lp = new LoginPage(driver);
		
		lp.setMailId(p.getProperty("mailid"));
		lp.setPassword(p.getProperty("password"));
		lp.clickLogin();
		
		logger.info("validating message");
		MyAccountPage pg = new MyAccountPage(driver);
		boolean actual_page=pg.isMyAccountPageExists();
	//	Assert.assertEquals(actual_page, "My Account","Login failed");
		Assert.assertTrue(actual_page);
	
	} catch(Exception e) {
		Assert.fail();
	}
		logger.info("***** Finished TC002_LoginTest ***** ");
}
}
