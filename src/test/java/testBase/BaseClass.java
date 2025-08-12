package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.text.RandomStringGenerator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class BaseClass {
	
public static WebDriver driver;
public Logger logger;
public Properties p;
	
	
	
	@BeforeClass(groups = {"sanity","regression","Master"})
	@Parameters({"os","browser"})
	public void setUp(String os, String br) throws InterruptedException, IOException {
		
		//Reading properties file
		FileInputStream file = new FileInputStream("./src//test//resources//config.properties");
		
		//creating object for Properties class to load the file
		p = new Properties();
		p.load(file);
		
		logger = LogManager.getLogger(this.getClass());  //to load the LOG4J2 xml file
		
		//REMOTE EXECUTION
		DesiredCapabilities cap = new DesiredCapabilities();
	//	String hubUrl = " http://192.168.1.3:4444/wd/hub";
		
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {
			
			  //OS
			if(os.equalsIgnoreCase("Windows")) {
			cap.setPlatform(Platform.WIN10);
			}
			else if(os.equalsIgnoreCase("mac")) {
				cap.setPlatform(Platform.MAC);
			}
			else if(os.equalsIgnoreCase("linux")) {
				cap.setPlatform(Platform.LINUX);
			}
			else {
				System.out.println("no os is matching");
				return;
			}
			
			//browser
			if(br.equalsIgnoreCase("chrome")) {
			cap.setBrowserName("chrome");
			}
			else if(br.equalsIgnoreCase("edge"))
			{
				cap.setBrowserName("MicrosoftEdge");
			}
			else if(br.equalsIgnoreCase("firefox"))
			{
				cap.setBrowserName("firefox");
			}
			else {
				System.out.println("no matching browser");
				return;
			}
				
			
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
			
		}
		
		//  LOCAL EXECUTION
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
				{
		switch(br.toLowerCase()) {
		
		case "chrome" : driver = new ChromeDriver();break;
		case "edge" : driver = new EdgeDriver();break;
		case "firefox" : driver = new FirefoxDriver();break;
		default:System.out.println("invalid browser name");return;  //return statement used to  exits from entire execution
		}
	}
		
		
		
	    driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appURL"));
		driver.manage().window().maximize();
		Thread.sleep(5000);
	}	
	
	@AfterClass(groups = {"sanity","regression","Master"})
	public void tearDown() {
		driver.quit();
	}
	
	//Userdefined methods
		public String getRandomAlphabets(int length) {
		    RandomStringGenerator generator = new RandomStringGenerator.Builder()   //this method() gives the random alphabets
		        .withinRange('A', 'z')
		        .filteredBy(Character::isLetter)
		        .build();

		    return generator.generate(length);
		}
		
		public String getRandomNumbers(int length) {
		    RandomStringGenerator generator = new RandomStringGenerator.Builder()
		        .withinRange('0', '9')
		        .build();

		    return generator.generate(length);
		}
		
		public String getRandomAlphaNumericWithSpecialChar(int length, char specialChar) {
		    if (length < 2) {
		        throw new IllegalArgumentException("Length must be at least 2 to include special character.");
		    }

		    // Generate (length - 1) alphanumeric characters
		    RandomStringGenerator generator = new RandomStringGenerator.Builder()
		        .withinRange('0', 'z')
		        .filteredBy(Character::isLetterOrDigit)
		        .build();

		    String alphanumeric = generator.generate(length - 1);

		    // Insert the special character at a random position
		    int insertPosition = new SecureRandom().nextInt(alphanumeric.length() + 1);
		    return new StringBuilder(alphanumeric).insert(insertPosition, specialChar).toString();
		}
		
		public String captureScreen(String tname) throws IOException {

			String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
					
			TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
			File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
			
			String relativePath = "screenshots/" + tname + "_" + timeStamp + ".png";
		    String fullPath = System.getProperty("user.dir") + File.separator + "reports" + File.separator + relativePath;
		    
			
		//	String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
			File targetFile=new File(fullPath);
			
			//sourceFile.renameTo(targetFile);
			 FileUtils.copyFile(sourceFile, targetFile);
				
			return relativePath;

		}



}
