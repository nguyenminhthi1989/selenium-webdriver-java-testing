package webdriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Video22_Topic09_Alert {
	
	WebDriver driver;
	Alert alert;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	String authenChromeAutoIT = projectPath + "\\autoIT\\authen_chrome.exe";
	String authenFirefoxAutoIT = projectPath + "\\autoIT\\authen_firefox.exe";
	
	@BeforeClass
	public void beforeClass() {
		
		//System.setProperty("firefox.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		//driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();

		// NOTE: remember that explicitWait must be always initiate after driver
		explicitWait = new WebDriverWait(driver,15);
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	//@Test
	public void TC01_Accept_Alert() {
		driver.get("http://demo.guru99.com/v4/index.php");
		
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		
		// wait until alert appear in xx seconds (defined in explicitWait)
		explicitWait.until(ExpectedConditions.alertIsPresent());
		
		// when alert appears, switch to alert
		alert = driver.switchTo().alert();
		
		// get alert text
		System.out.println(alert.getText());
		sleepInSecond(2);
		
		// Accept alert
		alert.accept();
	}
	
	//@Test
	public void TC02_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		
		// wait until alert appear in xx seconds
		explicitWait.until(ExpectedConditions.alertIsPresent());
		
		// switch to alert when alert appears
		alert = driver.switchTo().alert();
		
		// verify message in the alert
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		
		// accept alert
		alert.accept();
		
		// verify message after clicked OK
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'You clicked an alert successfully ')]")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.cssSelector("#result")).getText(), "You clicked an alert successfully");
	}
	
	//@Test
	public void TC03_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		
		// wait until alert appear in xx seconds, then initiate the alert
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		
		// verify message in the alert
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		sleepInSecond(2);
		
		// verify message after clicked OK
		alert.accept();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'You clicked: Ok')]")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.cssSelector("#result")).getText(), "You clicked: Ok");
		
		//===========================//
		
		// refresh page
		driver.navigate().refresh();
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		
		// wait until alert appear in xx seconds, then initiate the alert
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		
		// verify message in the alert
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		sleepInSecond(2);
		
		// verify message after clicked Cancel
		alert.dismiss();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'You clicked: Cancel')]")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.cssSelector("#result")).getText(), "You clicked: Cancel");
	}
	
	//@Test
	public void TC04_Prompt_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		
		// wait until alert appear in xx seconds
		explicitWait.until(ExpectedConditions.alertIsPresent());
		
		// switch to alert
		alert = driver.switchTo().alert();
		
		// verify message in the alert
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		sleepInSecond(2);
		
		// send message to the alert
		String alertString = "alert testing OK";
		alert.sendKeys(alertString);
		sleepInSecond(2);
		
		// verify message after clicked OK
		alert.accept();
		Assert.assertEquals(driver.findElement(By.cssSelector("#result")).getText(), "You entered: " + alertString);
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'You entered: " + alertString + "')]")).isDisplayed());
		
		//===========================//
		
		// refresh page
		driver.navigate().refresh();
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		
		// wait until alert appear in xx seconds then initiate alert
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		
		// send message to the alert
		alertString = "alert testing Cancel";
		alert.sendKeys(alertString);
		sleepInSecond(2);
				
		// verify message after clicked OK
		alert.dismiss();
		Assert.assertEquals(driver.findElement(By.cssSelector("#result")).getText(), "You entered: null");
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'You entered: null')]")).isDisplayed());
	}
	
	//@Test
	public void TC05_Authentication_Alert_By_Pass_Link() {
		String username = "admin";
		String password = "admin";
		String url = "http://" + username + ":" + password + "@" + "the-internet.herokuapp.com/basic_auth";
		
		driver.get(url);
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials')]")).isDisplayed());
	}
	
	//@Test
	public void TC06_Authentication_Alert_AutoIT() throws IOException{
		String username = "admin";
		String password = "admin";
		String url = "http://the-internet.herokuapp.com/basic_auth";
		
		// this step should always run before opening url (driver.get(url)) --> need to open autoIT before open url
		if (driver.toString().contains("chrome")) {
			Runtime.getRuntime().exec(new String[] {authenChromeAutoIT, username, password});
		} else if (driver.toString().contains("firefox")) {
			Runtime.getRuntime().exec(new String[] {authenFirefoxAutoIT, username, password});
		}
		
		driver.get(url);
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials')]")).isDisplayed());
	}
	
	@Test
	public void TC07_Authentication_Alert_By_Pass_Link_Split_Text() {
		String username = "admin";
		String password = "admin";
		String url = "http://the-internet.herokuapp.com/";
		driver.get(url);
		
		String authenUrl = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		driver.get(getUrlByUserPass(authenUrl, username, password));
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials')]")).isDisplayed());
	}
	
	public String getUrlByUserPass(String authenUrl, String username, String password) {
		System.out.println("Current Url is: " + authenUrl);
		String[] splitUrl = authenUrl.split("//");
		// splitUrl[0] = "https:"
		// splitUrl[1] = "the-internet.herokuapp.com/"
		authenUrl = splitUrl[0] + "//" + username + ":" + password + "@" + splitUrl[1];
		System.out.println("New Url is: " + authenUrl);
		return authenUrl;
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
