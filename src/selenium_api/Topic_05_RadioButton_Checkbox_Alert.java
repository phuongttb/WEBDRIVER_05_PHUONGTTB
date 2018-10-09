package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Topic_05_RadioButton_Checkbox_Alert {

	// Ini driver
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	// Test Script 01
	@Test()
	public void Testscript_01_JavascriptExecutorcode() throws InterruptedException {
		driver.get("http://live.guru99.com/");

		// Click vào link My Account dưới footer
		WebElement hylink = driver
				.findElement(By.xpath("//div[@class='footer']//li[@class='first']//a[text()='My Account']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", hylink);
		System.out.println("Current URL" + driver.getCurrentUrl());
		String actual = driver.getCurrentUrl();
		String expected = "http://live.guru99.com/index.php/customer/account/login/";
		Assert.assertEquals(actual, expected);

		// Click vào link CREATE AN ACCOUNT dưới footer
		WebElement accountlink = driver.findElement(By.xpath("//a[@class='button']//span[text()='Create an Account']"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", accountlink);
		System.out.println("Current URL" + driver.getCurrentUrl());
		String actual1 = driver.getCurrentUrl();
		String expected1 = "http://live.guru99.com/index.php/customer/account/create/";
		Assert.assertEquals(actual1, expected1);
	}

	// Test Script 02
	@Test()
	public void Testscript_02_JavascriptExecutorcode() throws InterruptedException {
		driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
		driver.findElement(By.cssSelector("#example > div > ul > li:nth-child(5) > label")).click();
		Assert.assertFalse(
				driver.findElement(By.cssSelector("#example > div > ul > li:nth-child(5) > label")).isSelected());
		WebElement equitmentcheckbox = driver
				.findElement(By.cssSelector("#example > div > ul > li:nth-child(5) > label"));
		if (!equitmentcheckbox.isSelected()) {
			equitmentcheckbox.click();
		}
	}

	// Test Script 03
	@Test()
	public void Testscript_03_JavascriptExecutorcode() throws InterruptedException {
		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
		WebElement radioButton = driver
				.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", radioButton);
		Assert.assertTrue(driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input"))
				.isSelected());
		WebElement equitmentradiobtn = driver
				.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input"));
		if (!equitmentradiobtn.isSelected()) {
			equitmentradiobtn.click();
		}
	}

	// Test Script 05
		@Test()
		public void Testscript_04_JavascriptExecutorcode() throws InterruptedException {
			driver.get("http://daominhdam.890m.com/");
			driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
			
		}
		
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}