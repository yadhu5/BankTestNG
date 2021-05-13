package practice_assignment;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class loginPage {

	WebDriver driver;
	
	String webBrowser = "null";

	@BeforeClass
	public void configRead() {
		Properties prop = new Properties();
		
		try {
		//  read the file: inputstream
			
			InputStream input = new FileInputStream("src\\main\\java\\config\\config.properties");
			prop.load(input);
			webBrowser = prop.getProperty("webBrowser");
		}
		catch(IOException e) {
			
			e.printStackTrace();
		}
		
		
		
	}
	@BeforeMethod
	public void searchBrowser() {

		if(webBrowser.equalsIgnoreCase("chrome")) {
		System.setProperty("webdriver.chrome.driver", "Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		}
		else if(webBrowser.equalsIgnoreCase("Firefox")) {
			
			System.setProperty("webdriver.gecko.driver", "Drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
			
		}
		
		driver.get("https://techfios.com/billing/?ng=login/");
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void webSearch() {
		By USER_NAME_FIELD = By.id("username");
		By PASSWORD_FIELD = By.id("password");
		By SIGNIN_BUTTON_FIELD = By.xpath("//button[@name='login']");
		By BANK_CASH_BUTTON = By.xpath("//span[contains(text(),'Bank & Cash')]");
		By NEW_ACCOUNT = By.xpath("//a[contains(text(),'New Account')]");
		
		
		
		String name = "demo@techfios.com";
		String password = "abc123";

		driver.findElement(USER_NAME_FIELD).sendKeys(name);
		driver.findElement(PASSWORD_FIELD).sendKeys("abc123");
		driver.findElement(SIGNIN_BUTTON_FIELD).click();
		
		Assert.assertEquals("Dashboard- iBilling", driver.getTitle());
		
		driver.findElement(BANK_CASH_BUTTON).click();
		driver.findElement(NEW_ACCOUNT).click();
		
		Assert.assertEquals("Accounts- iBilling", driver.getTitle(), "You landed in the wrong page!");
		
	}
	
//	@AfterMethod
	public void tearDown() {
		
		driver.close();
		driver.quit();
	}
}
