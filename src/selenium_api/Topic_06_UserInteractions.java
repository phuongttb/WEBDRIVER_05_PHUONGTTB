package selenium_api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_UserInteractions {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		//driver = new FirefoxDriver();
		
		System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	public void launchBrowser(String baseUrl) {
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	// @Test
	public void TC_01_HoverMouse_01() {
		launchBrowser("http://daominhdam.890m.com/");
		WebElement hoverLink = driver.findElement(By.xpath("//a[text()='Hover over me']"));
		Actions action = new Actions(driver);
		// hover mouse
		action.moveToElement(hoverLink).perform();
		WebElement tooltip = driver.findElement(By.xpath("//div[@class='tooltip-inner' and text()='Hooray!']"));
		Assert.assertTrue(tooltip.isDisplayed());
	}

	// @Test
	public void TC_01_HoverMouse_02() {
		launchBrowser("http://www.myntra.com/");
		WebElement hoverMenu = driver.findElement(By.xpath("//div[@class='desktop-userIconsContainer']"));
		Actions action = new Actions(driver);
		action.moveToElement(hoverMenu).perform();
		driver.findElement(By.xpath("//a[text()='login']")).click();
		WebElement formLogin = driver.findElement(By.xpath("//div[@class='login-box']"));
		Assert.assertTrue(formLogin.isDisplayed());
	}

	@Test
	public void TC_02_ClickAndHold() {
		launchBrowser("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		List<WebElement> listNumbers = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		Actions action = new Actions(driver);
		action.clickAndHold(listNumbers.get(0)).moveToElement(listNumbers.get(3)).release().perform();
		List<WebElement> numberSelected = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
		int number = numberSelected.size();
		Assert.assertEquals(number, 4);
	}
	
	@Test
	public void TC_02_ClickAndHold_Random() {
		launchBrowser("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		List<WebElement> listNumbers = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		Actions action = new Actions(driver);
		
		action.keyDown(Keys.CONTROL).build().perform();
		listNumbers.get(0).click();
		listNumbers.get(2).click();
		listNumbers.get(4).click();
		listNumbers.get(6).click();
		action.keyUp(Keys.CONTROL).build().perform();

		List<WebElement> numberSelected = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
		int number = numberSelected.size();
		Assert.assertEquals(number, 4);
	}
	
	@Test
	public void TC_03_DoubleClick() {
		launchBrowser("http://www.seleniumlearn.com/double-click");
		WebElement doubleClickMe = driver.findElement(By.xpath("//button[text()='Double-Click Me!']"));
		
		Actions action = new Actions(driver);
		action.doubleClick(doubleClickMe).perform();
		
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("The Button was double-clicked.", alert.getText());
		alert.accept();	
	}
	
	@Test
	public void TC_04_RightClick() {
		launchBrowser("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		WebElement rightClickMe = driver.findElement(By.xpath("//span[text()='right click me']"));
		
		//Right click
		Actions action = new Actions(driver);
		action.contextClick(rightClickMe);
		
		//Hover to Quit
		WebElement quiteBeforeHover = driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')]/span[text()='Quit']"));
		action.moveToElement(quiteBeforeHover).perform();
		
		WebElement quiteAfterHover = driver.findElement(By.xpath("//li[contains(@class,'context-menu-hover') and contains(@class,'context-menu-visible')]/span[text()='Quit']"));
		
		//Verify hover success
		Assert.assertTrue(quiteAfterHover.isDisplayed());
		action.click(quiteAfterHover).perform();
		
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("clicked: quit", alert.getText());
		alert.accept();		
	}
	
	@Test
	public void TC_05_DragAndDrop_01() {
		launchBrowser("http://demos.telerik.com/kendo-ui/dragdrop/angular");
		
		//Before drag/drop
		//WebElement beforeDrag = driver.findElement(By.xpath("//div[text()='Drag the small circle here.']"));
		//Assert.assertTrue(beforeDrag.isDisplayed());
		
		WebElement sourceElement = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement tagetElement = driver.findElement(By.xpath("//div[@id='droptarget']"));
		
		Actions action = new Actions(driver);
		action.dragAndDrop(sourceElement, tagetElement).build().perform();
		action.release();
		
		WebElement afterDrag = driver.findElement(By.xpath("//div[text()='You did great!']"));
		//Assert.assertTrue(beforeDrag.isDisplayed());
		Assert.assertTrue(afterDrag.isDisplayed());	
	}
	
	@Test
	public void TC_05_DragAndDrop_02() {
		launchBrowser("http://jqueryui.com/resources/demos/droppable/default.html");
		
		WebElement sourceElement = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement tagetElement = driver.findElement(By.xpath("//div[@id='droppable']"));
		
		Actions action = new Actions(driver);
		action.dragAndDrop(sourceElement, tagetElement).build().perform();
		action.release();
		
		WebElement afterDrag = driver.findElement(By.xpath("//div[@id='droppable']/p[text()='Dropped!']"));
		Assert.assertTrue(afterDrag.isDisplayed());	
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}