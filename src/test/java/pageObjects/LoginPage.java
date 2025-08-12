package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
	//constructor
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	//locators
	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txtemail;
	
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txtpwd;
	
	@FindBy(xpath = "//input[@value='Login']")
	WebElement btnlogin;
	//actions
	public void setMailId(String mail) {
		txtemail.sendKeys(mail);
	}
	
	public void setPassword(String pwd) {
		txtpwd.sendKeys(pwd);
	}
	
	public void clickLogin() {
	btnlogin.click();
	}
	
	
}
