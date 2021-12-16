package webdriver;

import java.awt.Frame;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Video23_Topic10_User_Interaction {
	
	WebDriver driver;
	Actions action;
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;
	
	@BeforeClass
	public void beforeClass() {
		//System.setProperty("firefox.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		//driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;
		explicitWait = new WebDriverWait(driver, 15);
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	//@Test
	public void TC01_Hover_To_Element() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		action.moveToElement(driver.findElement(By.id("age"))).perform();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class=\"ui-tooltip-content\"]")).getText(), "We ask for your age only for statistical purposes.");
	}
	
	//@Test
	public void TC02_Hover_To_Element() {
		driver.get("https://www.myntra.com/");
		action.moveToElement(driver.findElement(By.xpath("//a[@data-group='kids']"))).perform();
		sleepInSecond(2);
		driver.findElement(By.xpath("//a[@class='desktop-categoryName' and text()='Home & Bath']")).click();
		//Move to element and click
		//action.click(driver.findElement(By.xpath("//a[@class='desktop-categoryName' and text()='Home & Bath']"))).perform();
		sleepInSecond(2);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='title-container']/h1")).getText(), "Kids Home Bath");
	}
	
	@Test
	public void TC03_Hover_To_Element() {
		driver.get("https://www.fahasa.com/");
		
		// switch to new pop-up iframe if it appears
		
		//driver.switchTo().frame("preview-notification-frame");
		//jsExecutor.executeScript("arguments[0].click()", driver.findElement(By.xpath("//a[@id='NC_CLOSE_ICON']")));
		//driver.findElement(By.xpath("//a[@id='NC_CLOSE_ICON']")).click();
		
		sleepInSecond(2);
		
		// switch back to default main page
		driver.switchTo().defaultContent();
		action.moveToElement(driver.findElement(By.xpath("//div[@class='row custom-menu-homepage']//div[@class='block-content']//a[@title='Đồ Chơi']"))).perform();
		action.click(driver.findElement(By.xpath("//div[@class='row custom-menu-homepage']//div[@class='block-content']//a[@title='Đồ Chơi']/following-sibling::div//a[text()='Mô Hình Gỗ']"))).perform();
		
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//ol[@class='breadcrumb']/li[contains(.,'Mô Hình Gỗ')]")).isDisplayed());
		
	}
	
	//@Test
	public void TC04_Click_and_Hold_Block_Element() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		
		// 1st option
		action.clickAndHold(driver.findElement(By.xpath("(//li[contains(@class,'ui-selectee')])[1]"))).perform();
		sleepInSecond(2);
		action.release(driver.findElement(By.xpath("(//li[contains(@class,'ui-selectee')])[7]"))).perform();
		System.out.println("There are " + driver.findElements(By.xpath("//li[contains(@class,'ui-selected')]")).size() + " selected items!");
		Assert.assertEquals(driver.findElements(By.xpath("//li[contains(@class,'ui-selected')]")).size(), 6);
		
		// 2nd option (better)
		driver.navigate().refresh();
		List<WebElement> allSelectableNumber = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		System.out.println("First element is " + allSelectableNumber.get(0).getText());
		System.out.println("Last element is " + allSelectableNumber.get(11).getText());
		//click and hold from number 6 to number 11
		action.clickAndHold(allSelectableNumber.get(5)).moveToElement(allSelectableNumber.get(10)).release().perform();
		sleepInSecond(2);
		List<WebElement> selectedNumber = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
		System.out.println("There are " + selectedNumber.size() + " selected items!");
		Assert.assertEquals(selectedNumber.size(), 4);
	}
	
	//@Test
	public void TC05_Click_and_Hold_Random_Element() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> allSelectableNumber = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		
		action.click(allSelectableNumber.get(0))
		.keyDown(Keys.CONTROL)
		.click(allSelectableNumber.get(11))
		.click(allSelectableNumber.get(5)).perform();
		
		// release Ctrl
		action.keyUp(Keys.CONTROL);
		
		sleepInSecond(2);
		
		List<WebElement> selectedNumber = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
		System.out.println("There are " + selectedNumber.size() + " selected items!");
		Assert.assertEquals(selectedNumber.size(), 3);
		
		// click number 9 after releasing Ctrl
		action.click(allSelectableNumber.get(8)).perform();
	}
	
	//@Test
	public void TC06_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		// scroll into view before click
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//button[@ondblclick='doubleClickMe()']")));
		action.doubleClick(driver.findElement(By.xpath("//button[@ondblclick='doubleClickMe()']"))).perform();
		//action.click(driver.findElement(By.xpath("//button[@ondblclick='doubleClickMe()']"))).click(driver.findElement(By.xpath("//button[@ondblclick='doubleClickMe()']"))).perform();
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Hello Automation Guys!']")).isDisplayed());
	}

	public void sleepInSecond(int sleep) {
		try {
			Thread.sleep(sleep * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}

}
