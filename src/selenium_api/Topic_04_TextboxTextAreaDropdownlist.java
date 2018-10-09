package selenium_api;

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

public class Topic_04_TextboxTextAreaDropdownlist {

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
		System.out.print(job_01.getFirstSelectedOption().getText());
		Assert.assertEquals(job_01.getFirstSelectedOption().getText(), jobdrop1);
		job_01.selectByValue(jobdrop2);
		Assert.assertEquals(job_01.getFirstSelectedOption().getAttribute("value"), jobdrop2);
		job_01.selectByIndex(3);
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
		WebElement span = driver.findElement(By.xpath("//span[@id='number-button']"));

		System.out.println(
				"da chon thanh cong gia tri:" + selectvalue.getText() + " =" + span.getText() + "-" + valuedrop);
		Assert.assertEquals(span.getText(), valuedrop);
		Thread.sleep(4000);

	}

	@Test()
		@Parameters({ "username","password","customer_name","gender","dob","address","city","state","pin","mobile_phone" ,"email","password1" ,"account_no","address_edit" ,"city_edit"})
		public void Testscript_03(String username , String password,String customer_name, String gender,String dob, String address, String city, String state, String pin, String mobile_phone, String email, String password1 ,String account_no ,String address_edit ,String city_edit) throws InterruptedException {

			driver.get("http://demo.guru99.com/v4");
			driver.findElement(By.xpath("input[@name='uid']")).sendKeys(username);
			driver.findElement(By.xpath("input[@name='password']")).sendKeys(password);
			driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
			
			// VERIFY CUSTOMER CREATED SUCCESS
			WebElement createdSuccessMsg = driver.findElement(By.xpath("//p[@class='heading3' and text()='Customer Registered Successfully!!!']"));
			createdSuccessMsg.isDisplayed();
			Assert.assertTrue(createdSuccessMsg.isDisplayed());
			driver.findElement(By.xpath("//ul[@class='menusubnav']/li/a[contains(text(),'New Customer')]")).click();
			
			WebElement custname=driver.findElement(By.xpath("//input[@name='name']"));
					custname.sendKeys(customer_name);
			driver.findElement(By.xpath("//input[@name='rad1']")).isSelected();
			driver.findElement(By.xpath("//input[@id='dob']")).sendKeys(dob);
			
			WebElement addres=driver.findElement(By.xpath("//textarea[@name='addr']"));
			 addres.sendKeys(address);
			driver.findElement(By.xpath("//input[@name='city']")).sendKeys(city);
			driver.findElement(By.xpath("//input[@name='state']")).sendKeys(state);
			driver.findElement(By.xpath("//input[@name='pinno']")).sendKeys(pin);
			driver.findElement(By.xpath("//input[@name='telephoneno']")).sendKeys(mobile_phone);
			driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(email);
			driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password1);
			driver.findElement(By.xpath("//input[@name='sub']")).click();
			
			// GET CUSTOMER ID TEXT -> NEXT TESTCASE
			driver.findElement(By.xpath("//table[@id='customer']/tr/td/following-sibling::td")).getText();
			
			// VERIFY CUSTOMER INFORMATION CREATED
			Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), customer_name);
			Assert.assertEquals(driver.findElement(By.xpath("//td[contains(text(),'Gender')]//following-sibling::td")).getText(), gender);
			Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dob);
			Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), address);
			Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
			Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), state);
			Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
			Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), mobile_phone);
			Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), email);
			
			//EDIT CUSTOMER
			driver.findElement(By.xpath("//ul[@class='menusubnav']/a[contains(text),'Edit Account')]")).click();
			driver.findElement(By.xpath("//input[@name='accountno']")).sendKeys(account_no);
			driver.findElement(By.xpath("//input[@name='AccSubmit']")).click();
			
			//Verify giá trị tại 2 field: Customer Name và Address đúng với dữ liệu khi tạo mới New Customer tại Step 04
			Assert.assertEquals(driver.findElement(By.xpath("//input[@name='name']")).getAttribute("value"), customer_name);
			Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), address);
			
			//EDIT CUSTOMER SUCCESSFULLY
			WebElement addressedit=driver.findElement(By.xpath("//textarea[@name='addr']"));
			addressedit.clear();
			addressedit.sendKeys(address_edit);
			Assert.assertEquals(driver.findElement(By.xpath("//textarea[@name='addr']")).getText(), address_edit);
			WebElement editcity=driver.findElement(By.xpath("//input[@name='city']"));
			editcity.clear();
			editcity.sendKeys(city_edit);
			Assert.assertEquals(driver.findElement(By.xpath("//input[@name='city']")).getText(), city_edit);
			
			//DISPLAY MESSAGE WHEN EDIT CUSTOMER INFOR
			WebElement editSuccessMsg = driver.findElement(By.xpath("//p[@class='heading3' and text()='Customer Registered Successfully!!!']"));
			editSuccessMsg.isDisplayed();
			Assert.assertTrue(editSuccessMsg.isDisplayed());
			
			// VERIFY CUSTOMER INFORMATION EDITED
			Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), address_edit);
			Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city_edit);
			
		}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}