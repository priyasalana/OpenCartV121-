package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
	//constructor
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	//locators
	@FindBy(xpath = "//span[@class='caret']")
	WebElement linkaccount;
	
	@FindBy(xpath = "//a[normalize-space()='Register']")
	WebElement linkRegister;
	
	@FindBy(xpath = "//a[normalize-space()='Login']")
	WebElement linkLogin;
	
	
	//actions
	public void clickAccount() {
		linkaccount.click();
	}
	
	public void clickRegister() {
		linkRegister.click();
	}	
	public void clickLogin() {
		linkLogin.click();
		}

	
	

}
