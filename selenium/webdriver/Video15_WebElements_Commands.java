package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Video15_WebElements_Commands {
	
	WebDriver driver;
	
	String firstName, lastName, fullName, email, password;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		firstName = "auto";
		lastName = "test";
		fullName = firstName + " " + lastName;
		email = firstName + lastName + getRandomNUmber() + "@gmail.net";
		password = "123456";
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	//@Test
	public void Topic06_TC04_Register_MailChimp() throws InterruptedException {
		driver.get("https://login.mailchimp.com/signup/");
		
		driver.findElement(By.id("email")).sendKeys("nmthi@automationfc.com");
		driver.findElement(By.id("new_username")).sendKeys("nmthi");
		
		WebElement passwordTextBox = driver.findElement(By.id("new_password"));
		WebElement signupButton = driver.findElement(By.id("create-account"));
		
		By lowercaseCharCompletedBy = By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']");
		By uppercaseCharCompletedBy = By.xpath("//li[@class='uppercase-char completed' and text()='One uppercase character']");
		By numberCharCompletedBy = By.xpath("//li[@class='number-char completed' and text()='One number']");
		By specialCharCompletedBy = By.xpath("//li[@class='special-char completed' and text()='One special character']");
		By minCharCompletedBy = By.xpath("//li[@class='8-char completed' and text()='8 characters minimum']");
		
		//lowercase
		passwordTextBox.sendKeys("auto");
		Assert.assertTrue(driver.findElement(lowercaseCharCompletedBy).isDisplayed());
		Assert.assertFalse(signupButton.isEnabled());
		System.out.println("lower-case");
		
		//uppercase
		passwordTextBox.clear();
		passwordTextBox.sendKeys("AUTO");
		Assert.assertTrue(driver.findElement(uppercaseCharCompletedBy).isDisplayed());
		Assert.assertFalse(signupButton.isEnabled());
		System.out.println("upper-case");
				
		//number
		passwordTextBox.clear();
		passwordTextBox.sendKeys("123456");
		Assert.assertTrue(driver.findElement(numberCharCompletedBy).isDisplayed());
		Assert.assertFalse(signupButton.isEnabled());
		System.out.println("number-case");
		
		//special char
		passwordTextBox.clear();
		passwordTextBox.sendKeys("!@#");
		Assert.assertTrue(driver.findElement(specialCharCompletedBy).isDisplayed());
		Assert.assertFalse(signupButton.isEnabled());
		System.out.println("special-case");
		
		//at least 8 char
		passwordTextBox.clear();
		passwordTextBox.sendKeys("😉😉😉😉");
		Assert.assertTrue(driver.findElement(minCharCompletedBy).isDisplayed());
		Assert.assertFalse(signupButton.isEnabled());
		System.out.println("min-case");
		
		//input valid password
		passwordTextBox.clear();
		passwordTextBox.sendKeys("12345678x@X");
		Assert.assertFalse(driver.findElement(lowercaseCharCompletedBy).isDisplayed());
		Assert.assertFalse(driver.findElement(uppercaseCharCompletedBy).isDisplayed());
		Assert.assertFalse(driver.findElement(numberCharCompletedBy).isDisplayed());
		Assert.assertFalse(driver.findElement(specialCharCompletedBy).isDisplayed());
		Assert.assertFalse(driver.findElement(minCharCompletedBy).isDisplayed());
		Assert.assertTrue(signupButton.isEnabled());
		System.out.println("valid-pass-case");
		
		//validate marketing newsletter checkbox selected
		WebElement newsletterCheckbox = driver.findElement(By.id("marketing_newsletter"));
		newsletterCheckbox.click();
		Assert.assertTrue(newsletterCheckbox.isSelected());
		System.out.println("checkbox-case");
		
		Thread.sleep(1000);
	}
	
	@Test
	public void Topic04_TC05_Create_new_account() throws InterruptedException {
		driver.get("http://live.demoguru99.com/");
		driver.findElement(By.xpath("//div[@class='footer-container']//a[text()='My Account']")).click();
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		driver.findElement(By.id("firstname")).sendKeys(firstName);
		driver.findElement(By.id("lastname")).sendKeys(lastName);
		driver.findElement(By.id("email_address")).sendKeys(email);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys(password);
		
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Thank you for registering with Main Website Store.']")).isDisplayed());
		System.out.println("Register msg is verified!");
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='My Dashboard']")).isDisplayed());
		System.out.println("My Dashboard is verified!");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='welcome-msg']//strong[text()='Hello, " + fullName + "!']")).isDisplayed());
		System.out.println("Welcome msg is verified!");
		
		String contactInfo = driver.findElement(By.xpath("//h3[text()='Contact Information']//parent::div//following-sibling::div/p")).getText();
		Assert.assertTrue(contactInfo.contains(fullName));
		//Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']//p[contains(.,'" + fullName + "')]")).isDisplayed());
		System.out.println("FullName is verified!");
		
		Assert.assertTrue(contactInfo.contains(email));
		//Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']//p[contains(.,'" + email + "')]")).isDisplayed());
		System.out.println("Email is verified!");
		
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
		Thread.sleep(7000);
		String homePage = driver.getCurrentUrl();
		Assert.assertEquals(homePage, "http://live.demoguru99.com/index.php/");
	}
	
	@Test
	public void Topic04_TC06_Login() throws InterruptedException {
		driver.findElement(By.xpath("//div[@class='footer-container']//a[text()='My Account']")).click();
		
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.id("pass")).sendKeys(password);
		driver.findElement(By.id("send2")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='My Dashboard']")).isDisplayed());
		System.out.println("My Dashboard is verified!");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='welcome-msg']//strong[text()='Hello, " + fullName + "!']")).isDisplayed());
		System.out.println("Welcome msg is verified!");
		
		String contactInfo = driver.findElement(By.xpath("//h3[text()='Contact Information']//parent::div//following-sibling::div/p")).getText();
		Assert.assertTrue(contactInfo.contains(fullName));
		//Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']//p[contains(.,'" + fullName + "')]")).isDisplayed());
		System.out.println("FullName is verified!");
		
		Assert.assertTrue(contactInfo.contains(email));
		//Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']//p[contains(.,'" + email + "')]")).isDisplayed());
		System.out.println("Email is verified!");
		
		Thread.sleep(2000);
	}
	
	public int getRandomNUmber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
