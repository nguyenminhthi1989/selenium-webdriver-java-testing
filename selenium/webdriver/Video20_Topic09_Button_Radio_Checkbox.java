package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Video20_Topic09_Button_Radio_Checkbox {
	
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		jsExecutor = (JavascriptExecutor) driver;
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	//@Test
	public void TC01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();
		
		// 1 - Verify that Login button is disabled
		By loginButtonBy = By.xpath("//button[@class='fhs-btn-login']");
		Assert.assertFalse(driver.findElement(loginButtonBy).isEnabled());
		
		// 2 - Input data to Email and Password fields
		driver.findElement(By.id("login_username")).sendKeys("abc@yahoo.com");
		driver.findElement(By.id("login_password")).sendKeys("123456");
		
		// 3 - Verify that Login button is enabled
		Assert.assertTrue(driver.findElement(loginButtonBy).isEnabled());
		
		// 4 - Verify that Login button's background color is red
		String loginBtnRGBAColor = driver.findElement(loginButtonBy).getCssValue("background-color");
		System.out.println("RGBA color is " + loginBtnRGBAColor);
		
		String loginBtnHexaColor = Color.fromString(loginBtnRGBAColor).asHex().toUpperCase();
		System.out.println("Hexa color is " + loginBtnHexaColor);
		
		Assert.assertEquals("#C92127", loginBtnHexaColor);
		
		// 5 - Refresh page and navigate to login page
		driver.navigate().refresh();
		driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();
		
		// 6 - Remove "disabled" attribute of Login button by using JavascriptExecutor
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled')", driver.findElement(loginButtonBy));
		Assert.assertTrue(driver.findElement(loginButtonBy).isEnabled());
		
		// 7 - Click Login button and verify error messages in Email and Password fields
		driver.findElement(loginButtonBy).click();
		
		String inputEmailErr = driver.findElement(By.xpath("//div[@class='fhs-input-box checked-error']//div[@class='fhs-input-alert']")).getText();
		Assert.assertEquals(inputEmailErr, "Thông tin này không thể để trống");
		
		String inputPassErr = driver.findElement(By.xpath("//div[@class='fhs-input-box fhs-input-display checked-error']//div[@class='fhs-input-alert']")).getText();
		Assert.assertEquals(inputPassErr, "Thông tin này không thể để trống");
	}
	
	//@Test
	public void TC02_Default_Checkbox() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		By dualzoneCheckboxBy = By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input");
		By rearsideCheckboxBy = By.xpath("//label[text()='Rear side airbags']/preceding-sibling::input");
		
		//driver.findElement(dualzoneCheckboxBy).click();
		
		// Verify Dual-zone air conditioning is not selected then clicked
		checkToCheckbox(dualzoneCheckboxBy);
		Assert.assertTrue(driver.findElement(dualzoneCheckboxBy).isSelected());
		sleepInSecond(2);
		
		// Verify Dual-zone air conditioning is selected then clicked to become not selected
		uncheckToCheckbox(dualzoneCheckboxBy);
		Assert.assertFalse(driver.findElement(dualzoneCheckboxBy).isSelected());
		
		//Verify Rear side airbags is selected then not clicked
		checkToCheckbox(rearsideCheckboxBy);
		Assert.assertTrue(driver.findElement(rearsideCheckboxBy).isSelected());
		sleepInSecond(2);
		
		//Verify Rear side airbags is selected then clicked to become not selected
		uncheckToCheckbox(rearsideCheckboxBy);
		Assert.assertFalse(driver.findElement(rearsideCheckboxBy).isSelected());
	}
	
	@Test
	public void TC03_Default_Radio_Button() {
		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
		By petrolTwo = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input");
		By petrolThree = By.xpath("//label[text()='3.6 Petrol, 191kW']/preceding-sibling::input");
		
		// Verify 2.0 Petrol
		Assert.assertFalse(driver.findElement(petrolTwo).isSelected());
		driver.findElement(petrolTwo).click();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(petrolTwo).isSelected());
		driver.findElement(petrolTwo).click();
		Assert.assertTrue(driver.findElement(petrolTwo).isSelected());
		
		// Verify 3.6 Petrol
		Assert.assertFalse(driver.findElement(petrolThree).isEnabled());
		Assert.assertFalse(driver.findElement(petrolThree).isSelected());
	}
	
	public void checkToCheckbox(By by) {
		if (!driver.findElement(by).isSelected()) {
			System.out.println("The item is not selected, so it should be clicked!\n");
			driver.findElement(by).click();
		} else {
			System.out.println("The item is selected, so it should not be clicked again!\n");
		}
	}
	
	public void uncheckToCheckbox(By by) {
		if (driver.findElement(by).isSelected()) {
			driver.findElement(by).click();
		}
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
