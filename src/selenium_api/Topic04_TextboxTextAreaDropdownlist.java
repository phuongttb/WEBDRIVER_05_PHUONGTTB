package selenium_api;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Topic04_TextboxTextAreaDropdownlist {

	// Ini driver
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {

		// driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	// Test Script 01: Xử lí HTML Dropdown/ list
	@Test()
	@Parameters({ "jobdrop1", "jobdrop2", "jobdrop3" })
	public void Testscript_01(String jobdrop1, String jobdrop2, String jobdrop3) throws InterruptedException {
		driver.get("http://daominhdam.890m.com/");
		Select job_01 = new Select(driver.findElement(By.xpath("//select[@id='job1']")));

		Assert.assertFalse(job_01.isMultiple());
		job_01.selectByVisibleText(jobdrop1);
		Assert.assertEquals(job_01.getFirstSelectedOption().getText(), jobdrop1);

		job_01.selectByValue(jobdrop2);
		Assert.assertEquals(job_01.getFirstSelectedOption().getText(), jobdrop2);

		job_01.selectByIndex(3);
		Assert.assertEquals(job_01.getFirstSelectedOption().getText(), jobdrop3);

		int count = job_01.getOptions().size();

		Assert.assertEquals(count, 5);

	}

	@Test()
	public void Testscript_02() throws InterruptedException {

		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
//		WebElement selectBox = driver.findElement(By.cssSelector(".#number-button"));
		WebElement selectBox = driver.findElement(By.xpath("//span[@id='number-button']//span[@ class='ui-selectmenu-text']"));
		selectBox.click();
		Thread.sleep(3000);
		Select X = new Select(selectBox);
		List<WebElement> options = selectBox.findElements(By.id("//ul[@id='number-menu']"));
		int index = 0;
		for (WebElement option : options) {
			System.out.println(option.getText());
			String value = option.getText();
			if (value.contains("19")) {
				X.selectByIndex(index);

				WebElement clickvalue = driver.findElement(By.id("//div[@id='ui-id-34']"));
				clickvalue.click();
				System.out.print(value);

			}
			index++;
			break;
		}

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}