package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Topic02_ExpathLocator {

	// Ini driver
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.guru99.com/");
	}

	// Test Script 01: Verify URL and title
	@Test(priority = 1)
	public void TC_01_CheckURTitle() throws InterruptedException {

		String pagetitle = driver.getTitle();
		Assert.assertEquals(pagetitle, "Home page");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[contains(text(),'Create an Account')]")).click();
		driver.navigate().back();
		String url = driver.getCurrentUrl();
		Assert.assertEquals(url, "http://live.guru99.com/index.php/customer/account/login/");
		driver.navigate().forward();
		String url2 = driver.getCurrentUrl();
		Assert.assertEquals(url2, "http://live.guru99.com/index.php/customer/account/create/");
	}

	// Test Script 02: Login empty

	@Test(priority = 2)
	public void TC_02_LoginEmpty() {

		driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();
		// let blank user name and password
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		String errormssage = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText();
		Assert.assertEquals("This is a required field.", errormssage);
	}

	// Test Script 03: Login with Email invalid
	@Test
	public void TC_03_Loginwithsemailinvalid() throws InterruptedException {
		driver.get("http://live.guru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("123434234@12312.123123");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		String errormessage = driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText();
		Assert.assertEquals(errormessage, "Please enter a valid email address. For example johndoe@domain.com.");
	}

	// Test Script 04: Login with Password incorrect
	@Test(priority = 4)
	@Parameters({ "email", "password" })
	public void TC_04_Loginwithpasswordincorrect(String email, String password) {

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@id='email']")).clear();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(password);
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		driver.findElement(By.xpath("//div[@class='content fieldset']"));
		String errormessage = driver.findElement(By.xpath("advice-validate-password-pass")).getText();
		Assert.assertEquals(errormessage, "Please enter 6 or more characters without leading or trailing spaces.");

	}

	// Test Script 05: Create an account
	@Test(priority = 5)
	@Parameters({ "first_name", "middle_name", "last_name", "email", "password", "confirmation", "successmsg" })
	public void TC_05_Register(String first_name, String middle_name, String last_name, String email, String password,
			String confirmation, String successmsg) {

		driver.get("http://live.guru99.com/index.php/customer/account/login/");
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.id("firstname")).sendKeys(first_name);
		driver.findElement(By.id("middlename")).sendKeys(middle_name);
		driver.findElement(By.id("lastname")).sendKeys(last_name);
		driver.findElement(By.xpath(".//*[@id='email_address']")).sendKeys(email);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys(confirmation);
		driver.findElement(By.xpath("//input[@id='is_subscribed']")).click();
		driver.findElement(By.xpath(".//*[@id='form-validate']/div[2]/button")).click();
		driver.findElement(By.xpath("//ul[@class='messages']//*[text()='" + successmsg + "']"));
		String notimessage = driver
				.findElement(By.xpath(".//*[@id='top']/body/div/div/div[2]/div/div[2]/div/div/ul/li/ul/li/span"))
				.getText();
		Assert.assertEquals(notimessage, "Thank you for registering with Main Website Store.");
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']")).click();
		driver.findElement(By.xpath("//div[@id='header-account']")).click();
		driver.navigate().to("http://live.guru99.com/index.php/");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}