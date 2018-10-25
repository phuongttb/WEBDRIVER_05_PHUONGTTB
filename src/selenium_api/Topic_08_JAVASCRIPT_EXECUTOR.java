package selenium_api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
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

public class Topic_08_JAVASCRIPT_EXECUTOR {

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
	public void Testscript_01_JavascriptExecutorJE() throws InterruptedException {
		driver.get("http://live.guru99.com/");

		/* Step 02 - Sử dụng JE để get domain của page */
		String domain = (String) executeForBrowser("return document.domain");
		System.out.println("Display Domain:" + domain);
		Assert.assertEquals(domain, "http://live.guru99.com/");

		/* Step 03 - Sử dụng JE để get URL của page */
		String URL = (String) executeForBrowser("return document.URL");
		System.out.println("Display URL:" + URL);
		Assert.assertEquals(URL, "live.guru99.com");

		/* Step 04 - Open MOBILE page (Sử dụng JE) */
		WebElement mobileclick = driver.findElement(By.xpath("//a[text()='Mobile']"));
		clickToElementByJS(mobileclick);
		highlightElement(mobileclick);

		/*
		 * Step 05 - Add sản phẩm SAMSUNG GALAXY vào Cart (click vào ADD TO CART button
		 * bằng JE)
		 */
		WebElement addtocart = driver.findElement(
				By.xpath("/h2[a[text()='Samsung Galaxy']]/following-sibling::div[@class='actions']//button"));
		clickToElementByJS(addtocart);
		highlightElement(addtocart);
		/*
		 * Step 06 - Verify message được hiển thị: Samsung Galaxy was added to your
		 * shopping cart. (Sử dụng JE - Get innertext of the entire webpage )
		 */
		String cartmsg = (String) executeForBrowser("return document.documentElement.innerText;");
		Assert.assertTrue(cartmsg.contains("Samsung Galaxy was added to your shopping cart."));

		/* Step 07 - Open PRIVACY POLICY page (Sử dụng JE) */
		WebElement privacyclick = driver.findElement(By.xpath("//a[text()='Privacy Policy']"));
		clickToElementByJS(privacyclick);
		highlightElement(privacyclick);

		/* Verify title của page = Privacy Policy (Sử dụng JE) */
		String sText = executeForBrowser("return document.title;").toString();
		Assert.assertEquals(sText, "Privacy Policy");

		/* Step 08 - Srcoll xuống cuối page */
		scrollToBottomPage();

		/* Step 09 - Verify dữ liệu có hiển thị với chỉ 1 xpath: */
		WebElement wishlist = driver.findElement(By.xpath("//tr[text()='WISHLIST_CNT']/following-sibling::td"));
		Assert.assertTrue(wishlist.isDisplayed());
		highlightElement(driver.findElement(By.xpath("//tr[text()='WISHLIST_CNT']/following-sibling::td")));
		
		/*Step 10 - Navigate tới domain: http://demo.guru99.com/v4/  (Sử dụng JE)
			Verify domain sau khi navigate = demo.guru99.com*/
		
		navigateToUrlByJS("http://demo.guru99.com/v4/");
		String domainv4 = (String) executeForBrowser("return document.domain");
		Assert.assertEquals(domainv4, "http://demo.guru99.com/v4/");
		
	}
	
	    /*Test Script 02
		Step 01 - Truy cập vào trang: https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_input_disabled
		Step 02 - Remove thuộc tính disabled của field Last name
		Switch qua iframe nếu có
		Step 03 - Sendkey vào field Last name
		Eg. Automation Testing
		Step 04 - Click Submit button
		Step 05 - Verify dữ liệu sau khi submit chứa đoạn text đã fill trong field Lastname (Automation Testing)*/
		@Test()
		public void Testscript_02_Remove_attribute() throws InterruptedException {
			
			driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_input_disabled");
			WebElement iframe = driver.findElement(By.xpath("//iframe[@id='iframeResult']"));
			driver.switchTo().frame(iframe);
			WebElement lastName = driver.findElement(By.xpath("//input[@name='lname']"));
			removeAttributeInDOM(lastName, "disabled");
			lastName.sendKeys("phuongttb");
			driver.findElement(By.xpath("//input[@value='Submit']")).click();
			
			WebElement result = driver.findElement(By.xpath("//div[contains(text(),'phuongttb')]"));
			Assert.assertTrue(result.isDisplayed());
			
		}
		@Test()
		public void Testscript_03_CreateanAccount() throws InterruptedException {
			String firstname ="phuong" , middlename="tran" ,lastname="phuongttb" ,email_address="phuong@gmail.com",password="123456",confirmation="123456";
			driver.get("http://live.guru99.com/");
			WebElement accountlink = driver.findElement(By.xpath("//a[text()='My Account']"));
			clickToElementByJS(accountlink);
			WebElement createanacc= driver.findElement(By.xpath("//a[text()='Create an Account']/following-sibling::span"));
			clickToElementByJS(createanacc);
			WebElement firstnametextbox=driver.findElement(By.xpath(""));
			sendkeyToElementByJS(firstnametextbox,firstname);
			WebElement middlenametextbox=driver.findElement(By.xpath(""));
			sendkeyToElementByJS(middlenametextbox,middlename);
			WebElement lastnametextbox=driver.findElement(By.xpath(""));
			sendkeyToElementByJS(lastnametextbox,lastname);
			WebElement email_addresstextbox=driver.findElement(By.xpath(""));
			sendkeyToElementByJS(email_addresstextbox,email_address);
			WebElement passwordtextbox=driver.findElement(By.xpath(""));
			sendkeyToElementByJS(passwordtextbox,password);
		}


	public void highlightElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].style.border='6px groove red'", element);
	}

	public Object executeForBrowser(String javaSript) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript(javaSript);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	public Object clickToElementByJS(WebElement element) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript("arguments[0].click();", element);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	public Object sendkeyToElementByJS(WebElement element, String value) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	public Object removeAttributeInDOM(WebElement element, String attribute) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript("arguments[0].removeAttribute('" + attribute + "');", element);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	public Object scrollToBottomPage() {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	public Object navigateToUrlByJS(String url) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return js.executeScript("window.location = '" + url + "'");
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}