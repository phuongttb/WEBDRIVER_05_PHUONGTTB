package selenium_api;

import java.awt.datatransfer.StringSelection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.sun.glass.events.KeyEvent;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;

public class Topic_09_UploadFile {

	// Ini driver
	WebDriver driver;
	// Get auto project directory
	String workingDirectory = System.getProperty("user.dir");
	String fileName = "images.jpg";
	String fileName1 = "Picture002.jpg";
	String fileName2 = "Picture003.jpg";
	String filePath = workingDirectory + "\\UploadFile\\" + fileName;
	String filePath1 = workingDirectory + "\\UploadFile\\" + fileName1;
	String filePath2 = workingDirectory + "\\UploadFile\\" + fileName2;
	String folderName = "Phuong Tran " + randomNumber();
	String email = "phuongttb@gmail.com";
	String firstName = "phuong";

	@BeforeClass
	public void beforeClass() {
		/*
		 * System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		 * driver = new ChromeDriver();
		 */
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	// Test Script 01
	@Test()
	public void Testscript_01_UploadFileSendkey() throws InterruptedException {
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		WebElement addfile = driver.findElement(By.xpath("//input[@type='file']"));
		addfile.sendKeys(filePath, fileName1, fileName2);
		Thread.sleep(3000);
		
	}

	@Test()
	public void Testscript_02_UploadMultipleFileSendkey() throws InterruptedException {
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		String filename[] = { fileName, fileName1, fileName2 };
		String filepath[] = { filePath, filePath1, filePath2 };
		int sizepath = filepath.length;
		int sizename = filename.length;
		System.out.println("Tong so phan tu cua mang:" + sizepath);
		for (int i = 0; i < sizepath; i++) {
			WebElement addfile = driver.findElement(By.xpath("//input[@type='file']"));
			System.out.println(filepath[i]);
			addfile.sendKeys(filepath[i]);
			Thread.sleep(3000);
		}

		// verify 3 file chon upload thanh cong
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+fileName+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+fileName1+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name' and text()='"+fileName2+"']")).isDisplayed());
		// click vao button de upload file
		List <WebElement> clickbtn=driver.findElements(By.xpath("//table//button[@class='btn btn-primary start']"));
		for(WebElement btn : clickbtn)
		{
			btn.click();
			Thread.sleep(1000);
		}
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='"+fileName+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='"+fileName1+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='name']/a[text()='"+fileName2+"']")).isDisplayed());
	
	}
	
	// Upload nhieu file cung mot luc
		@Test()
		public void Testscript_03_UploadFileByAutoIT() throws InterruptedException, AWTException {
			driver.get("http://blueimp.github.com/jQuery-File-Upload/");
			//Specify the file location with extension
			StringSelection select = new StringSelection(filePath);

			//Copy to clipboard
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);

			//Click
			WebElement addFiles = driver.findElement(By.cssSelector(".fileinput-button"));
			addFiles.click();

			Robot robot = new Robot();
			Thread.sleep(1000);

			// Nhan phim Enter
			robot.keyPress(KeyEvent.VK_ENTER);
			// Nha phim Enter
			robot.keyRelease(KeyEvent.VK_ENTER);

			// Nhan phim Ctrl V
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);

			// Nha phim Ctrl V
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyRelease(KeyEvent.VK_V);
			Thread.sleep(1000);

			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			
			WebElement loadedFiles = driver.findElement(By.xpath("//p[@class='name' and text()='" + fileName + "']"));
			Assert.assertTrue(loadedFiles.isDisplayed());
		}
		
	
	private int randomNumber() {
		Random random = new Random();
		int number = random.nextInt(999999);
		return number;
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}