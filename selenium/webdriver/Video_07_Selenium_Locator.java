package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Video_07_Selenium_Locator {
	WebDriver driver;
	int tagnamenumber;

	@BeforeClass
	public void beforeClass() {
		//System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
	}
	
	//@Test
	public void TC_01_Find_Locator_By_ID() throws InterruptedException {
		System.out.println("1 - SendKey to Email textbox by: ID");
		driver.findElement(By.id("email")).sendKeys("test@gmail.com");
		Thread.sleep(3000);
		driver.findElement(By.id("email")).clear();
	}
	
	//@Test
	public void TC_02_Find_Locator_By_Class() throws InterruptedException {
		System.out.println("2 - SendKey to Password textbox by: Class");
		driver.findElement(By.className("validate-password")).sendKeys("123456");
		Thread.sleep(3000);
		driver.findElement(By.className("validate-password")).clear();
	}
	
	//@Test
	public void TC_03_Find_Locator_By_Name() throws InterruptedException {
		System.out.println("3 - Click on Login button by: Name");
		driver.findElement(By.name("send")).click();
		Thread.sleep(3000);
		Assert.assertTrue(driver.findElement(By.id("advice-required-entry-email")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.id("advice-required-entry-pass")).isDisplayed());
	}
	
	//@Test
	public void TC_04_Find_Locator_By_LinkText() throws InterruptedException {
		System.out.println("4 - Click on ABOUT US link by: LinkText");
		// the text should be the same when displaying in page
		driver.findElement(By.linkText("ABOUT US")).click();
		Thread.sleep(3000);
		Assert.assertTrue(driver.findElement(By.className("cms_page")).isDisplayed());
	}
	
	//@Test
	public void TC_05_Find_Locator_By_Partial_LinkText() throws InterruptedException {
		System.out.println("5 - Click on ADVANCED SEARCH link by: Partial LinkText");
		driver.findElement(By.partialLinkText("ADVANCED")).click();
		Thread.sleep(3000);
		Assert.assertTrue(driver.findElement(By.className("search")).isDisplayed());
	}
	
	//@Test
	public void TC_06_Find_Locator_By_TagName() {
		System.out.println("6 - Count how many Tagname in a webpage");
		
		//tagName should be found by Elements
		tagnamenumber = driver.findElements(By.tagName("input")).size();
		System.out.println("'input' tagname number is: " + tagnamenumber);
		
		System.out.println("'a' tagname number is: " + driver.findElements(By.tagName("a")).size());
	}
	
	//@Test
	public void TC_07_Find_Locator_By_CSS() throws InterruptedException {
		//ID with CSS
		System.out.println("7 - SendKey to Email textbox by: ID with CSS");
		driver.findElement(By.cssSelector("input[id='email']")).sendKeys("CSSID@gmail.com");
		
		//Name with CSS
		System.out.println("7 - SendKey to Password textbox by: Name with CSS");
		driver.findElement(By.cssSelector("input[name='login[password]']")).sendKeys("123");
		
		//ClassName with CSS
		System.out.println("7 - Click on Login button by: ClassName with CSS");
		driver.findElement(By.cssSelector("button[class='button']")).click();
		Assert.assertTrue(driver.findElement(By.id("advice-validate-password-pass")).isDisplayed());
		Thread.sleep(3000);
	}
	
	//@Test
	public void TC_08_Find_Locator_By_Xpath() throws InterruptedException {
		//ID with Xpath
		System.out.println("8 - SendKey to Email textbox by: ID with Xpath");
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("XpathID@gmail.com");
		
		//ClassName with Xpath
		System.out.println("8 - SendKey to Email textbox by: ClassName with Xpath");
		driver.findElement(By.xpath("//input[@class='input-text required-entry validate-password']")).sendKeys("123");
		
		//Name with Xpath
		System.out.println("8 - SendKey to Email textbox by: Name with Xpath");
		driver.findElement(By.xpath("//button[@name='send']")).click();
		Assert.assertTrue(driver.findElement(By.id("advice-validate-password-pass")).isDisplayed());
		Thread.sleep(3000);
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
