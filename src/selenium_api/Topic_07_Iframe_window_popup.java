package selenium_api;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Iframe_window_popup {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
	}

	public void launchBrowser(String baseUrl) {
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Iframe() {
		launchBrowser("http://www.hdfcbank.com/");

		// Element not displayed
		List<WebElement> iframeClosePopup = driver
				.findElements(By.xpath("//iframe[@id='vizury-notification-template']"));
		System.out.println("Count element = " + iframeClosePopup.size());

		if (iframeClosePopup.size() > 0) {
			driver.switchTo().frame(iframeClosePopup.get(0));
			driver.findElement(By.xpath("//div[@id='div-close']")).click();
		}
		driver.switchTo().defaultContent();

		WebElement lookingForIframe = driver.findElement(By.xpath("//div[@class='flipBannerWrap']//iframe"));
		driver.switchTo().frame(lookingForIframe);

		WebElement lookingForText = driver.findElement(By.xpath("//span[@id='messageText']"));
		Assert.assertEquals(lookingForText.getText(), "What are you looking for?");

		// Switch to Parent page (Top windows)
		driver.switchTo().defaultContent();

		WebElement bannerImageIframe = driver.findElement(By.xpath("//div[@class='slidingbanners']//iframe"));
		driver.switchTo().frame(bannerImageIframe);

		List<WebElement> images = driver.findElements(By.xpath("//div[@id='bannercontainer']//img"));
		int numberImages = images.size();
		Assert.assertEquals(numberImages, 6);
		driver.switchTo().defaultContent();

		WebElement flipperBanner = driver.findElement(By.xpath("//div[@class='flipBanner']"));
		Assert.assertTrue(flipperBanner.isDisplayed());

		List<WebElement> imagesFlipperBanner = driver
				.findElements(By.xpath("//div[@class='flipBanner']//img[@class='front icon']"));
		numberImages = imagesFlipperBanner.size();
		Assert.assertEquals(numberImages, 8);
	}

	@Test
	public void TC_02_Windows() {
		launchBrowser("http://daominhdam.890m.com/");

		String parentWindowID = driver.getWindowHandle();
		System.out.println("Parent ID = " + parentWindowID);

		driver.findElement(By.xpath("//a[text()='Click Here']")).click();

		switchToChildWindow(parentWindowID);

		String googleTitle = driver.getTitle();
		Assert.assertEquals(googleTitle, "Google");

	}

	@Test
	public void TC_03_OpenWindows() {
		launchBrowser("https://www.hdfcbank.com/");

		List<WebElement> iframeClosePopup = driver
				.findElements(By.xpath("//iframe[@id='vizury-notification-template']"));
		System.out.println("Count element = " + iframeClosePopup.size());

		if (iframeClosePopup.size() > 0) {
			driver.switchTo().frame(iframeClosePopup.get(0));
			driver.findElement(By.xpath("//div[@id='div-close']")).click();
		}
		driver.switchTo().defaultContent();
		
		String parentWindowID = driver.getWindowHandle();

		driver.findElement(By.xpath("//a[text()='Agri']")).click();
		switchToWindowByTitle("HDFC Bank Kisan Dhan Vikas e-Kendra");

		driver.findElement(By.xpath("//p[text()='Account Details']")).click();
		switchToWindowByTitle("Welcome to HDFC Bank NetBanking");

		WebElement privacyPolicyFrame = driver.findElement(By.xpath("//frame[@name='footer']"));
		driver.switchTo().frame(privacyPolicyFrame);
		driver.findElement(By.xpath("//a[text()='Privacy Policy']")).click();
		driver.switchTo().defaultContent();
		switchToWindowByTitle(
				"HDFC Bank - Leading Bank in India, Banking Services, Private Banking, Personal Loan, Car Loan");

		driver.findElement(By.xpath("//a[text()='CSR']")).click();
		switchToWindowByTitle("\"HDFC Bank: Personal Banking Services\"");
		
		closeAllWithoutParentWindows(parentWindowID);
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.hdfcbank.com/");
	}

	// >= 2 windows
	public void switchToWindowByTitle(String title) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			driver.switchTo().window(runWindows);
			String currentWin = driver.getTitle();
			if (currentWin.equals(title)) {
				break;
			}
		}
	}

	// Only 2 windows tab
	public void switchToChildWindow(String parent) {
		// Get all windows ID
		Set<String> allWindows = driver.getWindowHandles();
		// For-each
		for (String runWindow : allWindows) {
			// Kiem tra ID nao ma khac Parent ID thi minh switch qua
			if (!runWindow.equals(parent)) {
				// Switch qua window ID # parent window ID
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}

	public boolean closeAllWithoutParentWindows(String parentWindow) {
		// Get all windows ID
		Set<String> allWindows = driver.getWindowHandles();
		// Duyet qua tung ID
		for (String runWindows : allWindows) {
			// Neu ID # parent ID
			if (!runWindows.equals(parentWindow)) {
				// switch qua ID do
				driver.switchTo().window(runWindows);
				// dong ID
				driver.close();
			}
		}
		driver.switchTo().window(parentWindow);
		if (driver.getWindowHandles().size() == 1)
			return true;
		else
			return false;
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}

}