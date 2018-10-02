package selenium_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Topic03_WebElement_Webbrowser {

	// Ini driver
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {

		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://daominhdam.890m.com/");
	}

	public void isControlDisplayed(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if (element.isDisplayed()) {
			element.sendKeys("cuong em");
		} else
			System.out.println("Element " + "{" + locator + "}" + " is displayed");
	}

	public void isControlEnable(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if (element.isEnabled()) {
			System.out.println("Element " + "{" + locator + "}" + " is enabled");
		} else
			System.out.println("Element " + "{" + locator + "}" + " is disabled");
	}

	public void selectElement(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		element.click();
		if (!element.isSelected()) {
			element.click();
		}
	}

	@Test
	public void TC01_ElementIsDisplayedOnPage() {

		isControlDisplayed("//input[@id='mail']");

		isControlDisplayed("//input[@id='under_18']");

		isControlDisplayed("//textarea[@id='edu']");

	}

	@Test
	public void TC02_ElementIsEnabledOnPage() {
		
		isControlEnable("//input[@id='mail']");

		isControlEnable("//input[@id='password']");

		isControlEnable("//input[@id='under_18']");

		isControlEnable("//input[@id='radio-disabled']");

		isControlEnable("//textarea[@id='edu']");

		isControlEnable("//textarea[@id='bio']");

		isControlEnable("//select[@id='job1']");

		isControlEnable("//select[@id='job2']");

		isControlEnable("//input[@id='development']");

		isControlEnable("//input[@id='check-disbaled']");

		isControlEnable("//input[@id='slider-1']");

		isControlEnable("//input[@id='slider-2']");

		isControlEnable("//button[@id='button-disabled']");

		isControlEnable("//button[@id='button-enabled']");
	}

	@Test
	public void TC03_ElementIsSelectedOnPage() {


		selectElement("//input[@id='under_18']");

		selectElement("//input[@id='development']");

}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}