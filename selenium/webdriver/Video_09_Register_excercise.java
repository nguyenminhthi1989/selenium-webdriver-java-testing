package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Video_09_Register_excercise {
	
	WebDriver driver;
	
	// elements to reuse
	By nameTextboxBy = By.id("txtFirstname");
	By emailTextboxBy = By.id("txtEmail");
	By confirmEmailTextboxBy = By.id("txtCEmail");
	By passwordTextboxBy = By.id("txtPassword");
	By confirmPasswordTextboxBy = By.id("txtCPassword");
	By phoneTextboxBy = By.id("txtPhone");
	By registerButtonBy = By.xpath("//form[@id='frmLogin']//button");
	
	// error messages
	By nameErrorMsgBy = By.id("txtFirstname-error");
	By emailErrorMsgBy = By.id("txtEmail-error");
	By confirmEmailErrorMsgBy = By.id("txtCEmail-error");
	By passwordErrorMsgBy = By.id("txtPassword-error");
	By confirmPasswordErrorMsgBy = By.id("txtCPassword-error");
	By phoneErrorMsgBy = By.id("txtPhone-error");
	
	@BeforeClass
	public void beforeClass() {
		//System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		//Wait for 30s before web elemnets are finished to render --> need to execute before running testcase
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@BeforeMethod
	// In order to run this method before each testcase
	public void beforeMethod() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	}
	
	@Test
	public void TC_01_Register_empty_data() throws InterruptedException {
		System.out.println("TC01 - Register with empty data");
		driver.findElement(registerButtonBy).click();
		
		// Result comparison
		Assert.assertEquals(driver.findElement(nameErrorMsgBy).getText(), "Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(emailErrorMsgBy).getText(), "Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(confirmEmailErrorMsgBy).getText(), "Vui lòng nhập lại địa chỉ email");
		Assert.assertEquals(driver.findElement(passwordErrorMsgBy).getText(), "Vui lòng nhập mật khẩu");
		Assert.assertEquals(driver.findElement(confirmPasswordErrorMsgBy).getText(), "Vui lòng nhập lại mật khẩu");
		Assert.assertEquals(driver.findElement(phoneErrorMsgBy).getText(), "Vui lòng nhập số điện thoại.");
		Thread.sleep(1000);
	}
	
	@Test
	public void TC_02_Register_invalid_email() throws InterruptedException {
		System.out.println("TC02 - Register with invalid email");
		
		// Input info
		driver.findElement(nameTextboxBy).sendKeys("nmthi");
		driver.findElement(emailTextboxBy).sendKeys("nmthi@");
		driver.findElement(confirmEmailTextboxBy).sendKeys("nmthi@");
		driver.findElement(passwordTextboxBy).sendKeys("123456");
		driver.findElement(confirmPasswordTextboxBy).sendKeys("123456");
		driver.findElement(phoneTextboxBy).sendKeys("0987654321");
		driver.findElement(registerButtonBy).click();
		
		// Result comparison
		Assert.assertEquals(driver.findElement(emailErrorMsgBy).getText(), "Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(confirmEmailErrorMsgBy).getText(), "Email nhập lại không đúng");
		Thread.sleep(1000);
	}
	
	@Test
	public void TC_03_Register_incorrect_confirm_email() throws InterruptedException {
		System.out.println("TC03 - Register with incorrect confirm email");
		
		// Input info
		driver.findElement(nameTextboxBy).sendKeys("nmthi");
		driver.findElement(emailTextboxBy).sendKeys("nmthi@gmail.com");
		driver.findElement(confirmEmailTextboxBy).sendKeys("nmthi@gmail.canh");
		driver.findElement(passwordTextboxBy).sendKeys("123456");
		driver.findElement(confirmPasswordTextboxBy).sendKeys("123456");
		driver.findElement(phoneTextboxBy).sendKeys("0987654321");
		driver.findElement(registerButtonBy).click();
				
		// Result comparison
		Assert.assertEquals(driver.findElement(confirmEmailErrorMsgBy).getText(), "Email nhập lại không đúng");
		Thread.sleep(1000);
	}
	
	@Test
	public void TC_04_Register_pass_less_than_6_char() throws InterruptedException {
		System.out.println("TC04 - Register with password < 6 characters");
		
		// Input info
		driver.findElement(nameTextboxBy).sendKeys("nmthi");
		driver.findElement(emailTextboxBy).sendKeys("nmthi@gmail.com");
		driver.findElement(confirmEmailTextboxBy).sendKeys("nmthi@gmail.com");
		driver.findElement(passwordTextboxBy).sendKeys("123");
		driver.findElement(confirmPasswordTextboxBy).sendKeys("1234");
		driver.findElement(phoneTextboxBy).sendKeys("0987654321");
		driver.findElement(registerButtonBy).click();
		
		// Result comparison
		Assert.assertEquals(driver.findElement(passwordErrorMsgBy).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		Assert.assertEquals(driver.findElement(confirmPasswordErrorMsgBy).getText(), "Mật khẩu phải có ít nhất 6 ký tự");
		Thread.sleep(1000);
	}
	
	@Test
	public void TC_05_Register_incorrect_confirm_pass() throws InterruptedException {
		System.out.println("TC05 - Register with incorrect confirm password");
		
		// Input info
		driver.findElement(nameTextboxBy).sendKeys("nmthi");
		driver.findElement(emailTextboxBy).sendKeys("nmthi@gmail.com");
		driver.findElement(confirmEmailTextboxBy).sendKeys("nmthi@gmail.com");
		driver.findElement(passwordTextboxBy).sendKeys("123456");
		driver.findElement(confirmPasswordTextboxBy).sendKeys("123465");
		driver.findElement(phoneTextboxBy).sendKeys("0987654321");
		driver.findElement(registerButtonBy).click();
		
		// Result comparison
		Assert.assertEquals(driver.findElement(confirmPasswordErrorMsgBy).getText(), "Mật khẩu bạn nhập không khớp");
		Thread.sleep(1000);
	}
	
	@Test
	public void TC_06_Register_invalid_phone_number() throws InterruptedException {
		System.out.println("TC06 - Register with invalid phone number");
		
		// Input info
		driver.findElement(nameTextboxBy).sendKeys("nmthi");
		driver.findElement(emailTextboxBy).sendKeys("nmthi@gmail.com");
		driver.findElement(confirmEmailTextboxBy).sendKeys("nmthi@gmail.com");
		driver.findElement(passwordTextboxBy).sendKeys("123456");
		driver.findElement(confirmPasswordTextboxBy).sendKeys("123456");
		
		// Result comparison
		driver.findElement(phoneTextboxBy).sendKeys("abc");
		driver.findElement(registerButtonBy).click();
		Assert.assertEquals(driver.findElement(phoneErrorMsgBy).getText(), "Vui lòng nhập con số");
		
		driver.findElement(phoneTextboxBy).clear();
		driver.findElement(phoneTextboxBy).sendKeys("0987654");
		driver.findElement(registerButtonBy).click();
		Assert.assertEquals(driver.findElement(phoneErrorMsgBy).getText(), "Số điện thoại phải từ 10-11 số.");
		
		driver.findElement(phoneTextboxBy).clear();
		driver.findElement(phoneTextboxBy).sendKeys("123456");
		driver.findElement(registerButtonBy).click();
		Assert.assertEquals(driver.findElement(phoneErrorMsgBy).getText(), "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019");
		Thread.sleep(1000);
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
