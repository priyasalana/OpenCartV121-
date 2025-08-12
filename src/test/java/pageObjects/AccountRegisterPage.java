package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegisterPage extends BasePage {
	//constructor
	public AccountRegisterPage(WebDriver driver) {
		super(driver);
	}
	
	//locators
	@FindBy(xpath = "//input[@id='input-firstname']")
	WebElement txtfirstname;
   
	@FindBy(xpath = "//input[@id='input-lastname']")
	WebElement txtlastname;
	
	@FindBy(xpath = "//input[@id='input-email']")
	WebElement txtmail;
	
	@FindBy(xpath = "//input[@id='input-telephone']")
	WebElement txtphonenum;
	
	@FindBy(xpath = "//input[@id='input-password']")
	WebElement txtpassword;
	
	@FindBy(xpath = "//input[@id='input-confirm']")
	WebElement txtconfmpassword;
	
	@FindBy(xpath = "//input[@name='agree']")
	WebElement chkdagree;
	
	@FindBy(xpath = "//input[@value='Continue']")
	WebElement btncontinue;
	
	@FindBy(xpath= "//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement confmessage;
	
	//actions
	public void setFirstName(String fname) {
		txtfirstname.sendKeys(fname);
	}
	
	public void setLastName(String lname) {
		txtlastname.sendKeys(lname);
	}
	
	public void setMail(String mail) {
		txtmail.sendKeys(mail);
	}
	
	public void setPhoneNumber(String number) {
		txtphonenum.sendKeys(number);
	}
	
	public void setPassword(String password) {
		txtpassword.sendKeys(password);
	}
	
	public void setConfrmPassword(String cnfmpassword) {
		txtconfmpassword.sendKeys(cnfmpassword);
	}
	
	public void clickAgree() {
		chkdagree.click();
	}
	
	public void clickContinue() {
		btncontinue.click();
	}
	
	public String getconfmMesg() {
		try {
		return(confmessage.getText());
	}catch(Exception e) {
		return(e.getMessage());
	}
	
}
}
