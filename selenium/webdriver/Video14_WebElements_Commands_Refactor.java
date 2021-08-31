package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Video14_WebElements_Commands_Refactor {
	
	WebDriver driver;
	
	//enabled elements
	By emailTextboxBy = By.id("mail");
	By ageUnder18RadioBy = By.id("under_18");
	By educationTextareaBy = By.id("edu");
	By user5TextBy = By.xpath("//h5[text()='Name: User5']");
	By job1DropdownBy = By.id("job1");
	By developmentCheckboxBy = By.id("development");
	By slider1By = By.id("slider-1");
	By javaLangugeBy = By.id("java");	
	
	//disabled elements
	By passwordTextboxBy = By.xpath("//input[@name='user_pass']");
	By ageDisabledRadioBy = By.id("radio-disabled");
	By bioTextareaBy = By.id("bio");
	By job3DropdownBy = By.id("job3");
	By disabledCheckboxBy = By.id("check-disbaled");
	By slider2By = By.id("slider-2");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	public boolean isElementDisplayed(By by) {
		WebElement element = driver.findElement(by);
		if (element.isDisplayed()) {
			System.out.println("Element [" + by + "] is displayed");
			return true;
		} else {
			System.out.println("Element [" + by + "] is NOT displayed");
			return false;
		}
	}
	
	public boolean isElementEnabled(By by) {
		WebElement elemnent = driver.findElement(by);
		if (elemnent.isEnabled()) {
			System.out.println("Elemnent [" + by + "] is enabled");
			return true;
		} else {
			System.out.println("Elemnent [" + by + "] is disabled");
			return false;
		}
	}
	
	public boolean isElementSelected(By by) {
		WebElement element = driver.findElement(by);
		if(element.isSelected()) {
			System.out.println("Element [" + by + "] is selected");
			return true;
		} else {
			System.out.println("Element [" + by + "] is NOT selected");
			return false;
		}
	}
	
	public void sendKeyToElement(By by, String value) {
		WebElement element = driver.findElement(by);
		element.clear();
		element.sendKeys(value);
	}
	
	public void clickToElement(By by) {
		WebElement element = driver.findElement(by);
		element.click();
	}
	
	@Test
	public void TC01_Verify_isDisplayed(){
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		if (isElementDisplayed(emailTextboxBy)) {
			sendKeyToElement(emailTextboxBy, "Automation Testing");
		}
		
		if (isElementDisplayed(ageUnder18RadioBy)) {
			clickToElement(ageUnder18RadioBy);
		}
		
		if (isElementDisplayed(educationTextareaBy)) {
			sendKeyToElement(educationTextareaBy, "Automation Testing");
		}
		
		Assert.assertFalse(isElementDisplayed(user5TextBy));
	}
	
	@Test
	public void TC02_Verify_isEnabled(){
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		Assert.assertTrue(isElementEnabled(emailTextboxBy));
		Assert.assertTrue(isElementEnabled(ageUnder18RadioBy));
		Assert.assertTrue(isElementEnabled(educationTextareaBy));
		Assert.assertTrue(isElementEnabled(job1DropdownBy));
		Assert.assertTrue(isElementEnabled(developmentCheckboxBy));
		Assert.assertTrue(isElementEnabled(slider1By));
		
		Assert.assertFalse(isElementEnabled(passwordTextboxBy));
		Assert.assertFalse(isElementEnabled(ageDisabledRadioBy));
		Assert.assertFalse(isElementEnabled(bioTextareaBy));
		Assert.assertFalse(isElementEnabled(job3DropdownBy));
		Assert.assertFalse(isElementEnabled(disabledCheckboxBy));
		Assert.assertFalse(isElementEnabled(slider2By));
	}
	
	@Test
	public void TC03_Verify_isSelected(){
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		Assert.assertFalse(isElementSelected(ageUnder18RadioBy));
		Assert.assertFalse(isElementSelected(javaLangugeBy));
		
		clickToElement(ageUnder18RadioBy);
		clickToElement(javaLangugeBy);
				
		Assert.assertTrue(isElementSelected(ageUnder18RadioBy));
		Assert.assertTrue(isElementSelected(javaLangugeBy));
		
		clickToElement(ageUnder18RadioBy);
		clickToElement(javaLangugeBy);
		
		Assert.assertTrue(isElementSelected(ageUnder18RadioBy));
		Assert.assertFalse(isElementSelected(javaLangugeBy));
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
