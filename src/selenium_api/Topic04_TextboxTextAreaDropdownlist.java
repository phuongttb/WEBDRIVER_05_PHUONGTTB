package selenium_api;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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
		Assert.assertEquals(job_01.getFirstSelectedOption().getAttribute("value"), jobdrop2);

//		job_01.selectByIndex(3);
//		Assert.assertEquals(job_01.getFirstSelectedOption().getText(),
//				driver.findElement(By.xpath("//*[@value=' mobile ']")).getText());

		int count = job_01.getOptions().size();

		Assert.assertEquals(count, 5);

	}

	@Test()
	@Parameters({ "valuedrop" })
	public void Testscript_02(String valuedrop) throws InterruptedException {

		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		driver.findElement(By.xpath("//span[@id='number-button']//span[@ class='ui-selectmenu-text']")).click();
		Thread.sleep(3000);
		WebElement selectvalue = driver
				.findElement(By.xpath("//ul[@id='number-menu']/li/div[contains(.,'" + valuedrop + "')]"));
		selectvalue.click();

		Thread.sleep(3000);
		WebElement span = driver
				.findElement(By.xpath("//span[@id='number-button']"));
		
		System.out.println("da chon thanh cong gia tri:" + selectvalue.getText() + " =" + span.getText() + "-" +valuedrop);
		Assert.assertEquals( span.getText(),valuedrop);
		Thread.sleep(4000);

	}
	
	@Test()
	@Parameters({ "username","password" })
	public void Testscript_03(String username , String password) throws InterruptedException {

		driver.get("http://demo.guru99.com/v4");
		driver.findElement(By.xpath("input[@name='uid']")).sendKeys(username);
		driver.findElement(By.xpath("input[@name='password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		//assert.assertTrue(driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}