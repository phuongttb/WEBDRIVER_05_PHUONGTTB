package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_01_CheckEnvironment {
    WebDriver driver;

	@Test
	public void TC_01_CheckUrlAndTitle() {
		System.out.println("Check homepage title");
		String homePageTitle = driver.getTitle();
		Assert.assertEquals(homePageTitle, "Tripi.vn - Đặt vé máy bay và khách sạn thuận tiện với giá tốt nhất");

		System.out.println("Check homepage url");
		String homePageUrl = driver.getCurrentUrl();
		Assert.assertEquals(homePageUrl, "https://dev.tripi.vn/");
		
		WebElement element = driver.findElement(By.id("tripi-holiday-from-airport-value"));
		element.sendKeys("Hồ chí Minh");
		System.out.println("Test: "+element.getText());
		WebElement element = driver.findElement(By.id("tripi-holiday-from-airport-value"));
		element.sendKeys("Hồ chí Minh");
		System.out.println("Test: "+element.getText());
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.get("https://dev.tripi.vn/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}