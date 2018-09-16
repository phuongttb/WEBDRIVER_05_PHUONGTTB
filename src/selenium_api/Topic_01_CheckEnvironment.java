package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_01_CheckEnvironment {

	WebDriver driver;
	String baseUrl = "http://live.guru99.com";
	String actual;
	String expected;

	public void launchBrowserAndMyAccount() {
		driver.navigate().to(baseUrl);
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	}

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
	}

	@Test
	public void TC01_VerifyURLAndTitle() {

		driver.navigate().to(baseUrl);

		actual = driver.getTitle();
		expected = "Home page";
		Assert.assertEquals(actual, expected);

		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

		driver.navigate().back();

		actual = driver.getCurrentUrl();
		expected = "http://live.guru99.com/index.php/customer/account/login/";
		Assert.assertEquals(actual, expected);

		driver.navigate().forward();

		actual = driver.getCurrentUrl();
		expected = "http://live.guru99.com/index.php/customer/account/create/";
		Assert.assertEquals(actual, expected);

	}

	@Test
	public void TC02_LoginEmpty() {

		launchBrowserAndMyAccount();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");
		driver.findElement(By.xpath("//button[@id='send2']")).click();

		actual = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText();
		expected = "This is a required field.";
		Assert.assertEquals(actual, expected);

		actual = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText();
		expected = "This is a required field.";
		Assert.assertEquals(actual, expected);

	}

	@Test
	public void TC03_LoginWithEmailInvalid() {

		launchBrowserAndMyAccount();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("ttcuongem@12312.123123");
		driver.findElement(By.xpath("//button[@id='send2']")).click();

		actual = driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText();
		expected = "Please enter a valid email address. For example johndoe@domain.com.";
		Assert.assertEquals(actual, expected);

	}

	@Test
	public void TC04_LoginWithPasswordIncorrect() {

		launchBrowserAndMyAccount();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("ttcuongem@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123");
		driver.findElement(By.xpath("//button[@id='send2']")).click();

		actual = driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText();
		expected = "Please enter 6 or more characters without leading or trailing spaces.";
		Assert.assertEquals(actual, expected);
	}

	@Test
	public void TC05_CreateAnAccount() {

		launchBrowserAndMyAccount();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("em");
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys("3");
		driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys("em2" + ((int) (Math.random() * 100) + 1) + "@gmail.com");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys("123456");
		driver.findElement(By.xpath("//button[@title='Register']")).click();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		actual = driver.findElement(By.xpath("//span[contains(text(),'Thank you for registering')]")).getText();
		expected = "Thank you for registering with Main Website Store.";
		Assert.assertEquals(actual, expected);

		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();

		(new WebDriverWait(driver, 30)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@alt='Additional Options']")));

		actual = driver.getTitle();
		expected = "Home page";
		Assert.assertEquals(actual, expected);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}