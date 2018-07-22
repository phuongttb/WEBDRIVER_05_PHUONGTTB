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
		// let blank username and password
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		String errormssage = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText();
		Assert.assertEquals("This is a required field", errormssage);
	}

	@Test
	public void TC_05_Register() {

		// Click vào link "My Account" để tới trang đăng nhập
		driver.findElement(By.xpath(".//*[@id='top']/body/div/div/div[3]/div/div[4]/ul/li[1]/a")).click();
		driver.findElement(By.xpath("//div[@class='footer']//a[contains(text(),'My Account')]")).click();
		driver.findElement(By.xpath("//a[@title='Create an Account' and @class='button']")).click();
		driver.findElement(By.id("firstname")).sendKeys("Tran");
		driver.findElement(By.id("middlename")).sendKeys("Bich");
		driver.findElement(By.id("lastname")).sendKeys("Phuong");
		driver.findElement(By.id("email")).sendKeys("bichphuong1209@gmail.com");
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.id("confirmation")).sendKeys("123456");

		driver.findElement(By.xpath("//div[@class='links']//a[@title='Log Out']")).click();
		driver.navigate().to("http://live.guru99.com/index.php/");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}