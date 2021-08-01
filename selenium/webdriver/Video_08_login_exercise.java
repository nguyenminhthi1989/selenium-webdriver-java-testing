package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Video_08_login_exercise {
	
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		//System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		//Wait for 30s before web elemnets are finished to render --> need to execute before running testcase
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
	}
	
	@Test
	public void TC_01_empty_Email_and_Password() throws InterruptedException {
		System.out.println("TC01 - Login with empty Email and Password");
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.name("login[password]")).sendKeys("");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		Thread.sleep(1000);
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(), "This is a required field.");
	}
	
	@Test
	public void TC_02_invalid_Email() throws InterruptedException {
		System.out.println("TC02 - Login with invalid Email");
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("1234@123.123");
		driver.findElement(By.name("login[password]")).clear();
		driver.findElement(By.name("login[password]")).sendKeys("123456");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		Thread.sleep(1000);
		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
	}
	
	@Test
	public void TC_03_invalid_Password() throws InterruptedException {
		System.out.println("TC03 - Login with invalid Password");
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		driver.findElement(By.name("login[password]")).clear();
		driver.findElement(By.name("login[password]")).sendKeys("123");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		Thread.sleep(1000);
		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
	}
	
	@Test
	public void TC_04_incorrect_Email_and_Password() throws InterruptedException {
		System.out.println("TC04 - Login with incorrect Email and Password");
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		driver.findElement(By.name("login[password]")).clear();
		driver.findElement(By.name("login[password]")).sendKeys("123123123");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		Thread.sleep(1000);
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(), "Invalid login or password.");
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
