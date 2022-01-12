package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Video26_Topic11_Handle_Frame_iFrame {
	
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	
	@BeforeClass
	public void beforeClass() {
//		System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
//		driver = new FirefoxDriver();
		
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		jsExecutor = (JavascriptExecutor) driver;
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	//@Test
	public void TC01_iFrame() {
		driver.get("https://kyna.vn/");
		
		// Verify fb iframe displaying
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='face-content']//iframe")).isDisplayed());
		
		// switch to fb iframe and verify fb iframe having 167k likes
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='face-content']//iframe")));
		String fbLikes = driver.findElement(By.xpath("//div[@class='_1drq']")).getText();
		System.out.println("There are " + fbLikes + " in FB page!");
		Assert.assertEquals(fbLikes, "167K likes");
		
		// switch back to main page
		driver.switchTo().defaultContent();
		driver.findElement(By.id("live-search-bar")).sendKeys("test");
		
		// switch to chat iframe
		driver.switchTo().frame("cs_chat_iframe");
		driver.findElement(By.xpath("//div[contains(@class,'meshim_widget_widgets_BorderOverlay')]")).click();
		sleepInSecond(1);
		driver.findElement(By.xpath("//input[@ng-model='login.username']")).sendKeys("abc");
		driver.findElement(By.xpath("//input[@ng-model='login.phone']")).sendKeys("123");
		driver.findElement(By.xpath("//select[@ng-model='login.selectedService']/option[text()='TƯ VẤN TUYỂN SINH']")).click();
		
		//jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//textarea[@ng-model='login.content']")));
		//System.out.println("Scrolled into input message field!");
		driver.findElement(By.xpath("//textarea[@ng-model='login.content']")).sendKeys("test");
		
		driver.findElement(By.xpath("//input[@type='submit' and @value='Gửi tin nhắn']")).click();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@name='form']//div[@class='editing field profile_field']//div[contains(@class,'error_message') and contains(.,'SĐT không hợp lệ')]")).isDisplayed());
		driver.findElement(By.xpath("//div[contains(@class,'meshim_widget_widgets_BorderOverlay')]")).click();
				
		// switch back to main page
		driver.switchTo().defaultContent();
		driver.findElement(By.id("live-search-bar")).clear();
		driver.findElement(By.id("live-search-bar")).sendKeys("Excel");
		driver.findElement(By.id("live-search-bar")).sendKeys(Keys.ENTER);
		sleepInSecond(5);
		
		List<WebElement> allTitlePosts = driver.findElements(By.xpath("//ul[@class='k-box-card-list']/li//img"));
		System.out.println("There are " + allTitlePosts.size() + " results!\n");
		for (WebElement eachTitle : allTitlePosts) {
			System.out.println(eachTitle.getAttribute("title").toString() + "\n");
			Assert.assertTrue(eachTitle.getAttribute("title").toString().contains("Excel"));
		}
	}
	
	@Test
	public void TC02_Frame() {
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		driver.switchTo().frame("login_page");
		driver.findElement(By.xpath("//input[@name='fldLoginUserId']")).sendKeys("abc");
		driver.findElement(By.xpath("//a[@class='btn btn-primary login-btn']")).click();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//input[@name='fldPassword']")).isDisplayed());
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
