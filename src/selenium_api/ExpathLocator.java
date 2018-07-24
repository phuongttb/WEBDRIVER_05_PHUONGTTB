package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ExpathLocator {

	// Ini driver
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		// Init chromebrowser
		driver = new FirefoxDriver();

		// Waiting page loading
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// Zoom-out screen
		driver.manage().window().maximize();

		// Get URL
		driver.get("http://live.guru99.com/");

	}

	// TC_01_Check Page Title
	@Test
	public void TC_01_CheckTitle() {
		// Get title
		String homeTitle = driver.getTitle();
		Assert.assertEquals(homeTitle, "Home page");

	}
	// TC_02_Geturl

	@Test
	public void TC_02_Geturl() {
		// Get URL
		String homeURL = driver.getCurrentUrl();
		Assert.assertEquals("http://live.guru99.com/", homeURL);
	}
	// TC_03_LoginEmpty

	@Test
	public void TC_03_LoginEmpt() {

		driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();
		// let blank user name and password
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		String errormssage = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText();
		 Assert.assertEquals("This is a required field.", errormssage);
	}
	
	@Test
	public void TC_04_Loginwithsemailinvalid() {

//		String url = driver.getCurrentUrl();
//		Assert.assertEquals(url, "http://live.guru99.com/");
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		driver.findElement(By.xpath("//div[@class='footer'] and //a[@title='My Account']")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("123434234@12312.123123");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		String errormessage=driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText();
		Assert.assertEquals("Please enter a valid email address. For example johndoe@domain.com.", errormessage);
	}
	
	@Test
	public void TC_05_Loginwithpasswordincorrect() {


		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@id='email']")).clear();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		String errormessage=driver.findElement(By.xpath("advice-validate-password-pass")).getText();
		Assert.assertEquals("Please enter 6 or more characters without leading or trailing spaces.", errormessage);
	}
	
	@Test
	public void TC_05_Register() {

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//a[@class='button' and title='Create an Account']")).click();
		driver.findElement(By.id("firstname")).sendKeys("Tran");
		driver.findElement(By.id("middlename")).sendKeys("Bich");
		driver.findElement(By.id("lastname")).sendKeys("Phuong");
		driver.findElement(By.id("email")).sendKeys("bichphuong1209@gmail.com");
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.id("confirmation")).sendKeys("123456");
		driver.findElement(By.xpath("//div[@class='links']//a[@title='Log Out']")).click();
		driver.findElement(By.xpath("//input[@id='is_subscribed']")).click();
		String notimessage=driver.findElement(By.xpath("//div[@class='success-msg']")).getText();
		Assert.assertEquals("Thank you for registering with Main Website Store.", notimessage);
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']")).click();
		driver.findElement(By.xpath("//div[@id='header-account']")).click();
		driver.navigate().to("http://live.guru99.com/index.php/");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}