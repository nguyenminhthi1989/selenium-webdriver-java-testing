package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Video21_Topic09_Button_Radio_Checkbox {
	
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	
	@BeforeClass
	public void beforeClass() {
		//System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		//driver = new ChromeDriver();
		System.setProperty("firefox.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	//@Test
	public void TC01_Custom_RadioButton() {
		driver.get("https://material.angular.io/components/radio/examples");
		
		// 1st option
		By summerRadioButtonSpan = By.xpath("//input[@value='Summer']/preceding-sibling::span[contains(@class,'outer')]");
		By summerRadioButtonInput = By.xpath("//input[@value='Summer']");
		driver.findElement(summerRadioButtonSpan).click();
		Assert.assertTrue(driver.findElement(summerRadioButtonInput).isSelected());
		
		// 2nd option
		By winterRadioButtonInput = By.xpath("//input[@value='Winter']");
		jsExecutor.executeScript("arguments[0].click()", driver.findElement(winterRadioButtonInput));
		Assert.assertTrue(driver.findElement(winterRadioButtonInput).isSelected());
		Assert.assertFalse(driver.findElement(summerRadioButtonInput).isSelected());
	}
	
	//@Test
	public void TC02_Custom_Checkbox() {
		driver.get("https://material.angular.io/components/checkbox/examples");
		By checkedCheckboxSpan = By.xpath("//span[text()='Checked']/preceding-sibling::span[contains(@class,'inner-container')]");
		By indeterminateCheckboxSpan = By.xpath("//span[text()='Indeterminate']/preceding-sibling::span[contains(@class,'inner-container')]");
		By checkedCheckboxInput = By.xpath("//span[text()='Checked']/preceding-sibling::span[contains(@class,'inner-container')]/input");
		By indeterminateCheckboxInput = By.xpath("//span[text()='Indeterminate']/preceding-sibling::span[contains(@class,'inner-container')]/input");
		
		// 1st option
		checkToCheckbox(checkedCheckboxInput,checkedCheckboxSpan);
		Assert.assertEquals(driver.findElement(checkedCheckboxInput).getAttribute("aria-checked"), "true");
		checkToCheckbox(indeterminateCheckboxInput,indeterminateCheckboxSpan);
		Assert.assertEquals(driver.findElement(indeterminateCheckboxInput).getAttribute("aria-checked"), "true");
		
		uncheckToCheckbox(checkedCheckboxInput,checkedCheckboxSpan);
		Assert.assertEquals(driver.findElement(checkedCheckboxInput).getAttribute("aria-checked"), "false");
		uncheckToCheckbox(indeterminateCheckboxInput,indeterminateCheckboxSpan);
		Assert.assertEquals(driver.findElement(indeterminateCheckboxInput).getAttribute("aria-checked"), "false");
		
		sleepInSecond(2);
		
		// 2nd option
		checkToCheckboxByJS(checkedCheckboxInput);
		Assert.assertEquals(driver.findElement(checkedCheckboxInput).getAttribute("aria-checked"), "true");
		checkToCheckboxByJS(indeterminateCheckboxInput);
		Assert.assertEquals(driver.findElement(indeterminateCheckboxInput).getAttribute("aria-checked"), "true");
		
		uncheckToCheckboxByJS(checkedCheckboxInput);
		Assert.assertEquals(driver.findElement(checkedCheckboxInput).getAttribute("aria-checked"), "false");
		uncheckToCheckboxByJS(indeterminateCheckboxInput);
		Assert.assertEquals(driver.findElement(indeterminateCheckboxInput).getAttribute("aria-checked"), "false");
		
	}
	
	@Test
	public void TC03_Custom_Radio_Checkbox_GoogleDocs() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		
		// exercise to practise when there's no input tag in HTML
		
		// Radio button
		By danangRadioButton = By.xpath("//div[@aria-label='Đà Nẵng']");
		
		Assert.assertEquals(driver.findElement(danangRadioButton).getAttribute("aria-checked"), "false");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Đà Nẵng' and @aria-checked='false']")).isDisplayed());
		
		driver.findElement(danangRadioButton).click();
		
		Assert.assertEquals(driver.findElement(danangRadioButton).getAttribute("aria-checked"), "true");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@aria-label='Đà Nẵng' and @aria-checked='true']")).isDisplayed());
		
		// Checkbox
		By quangninhCheckbox = By.xpath("//div[@role='checkbox' and @aria-label='Quảng Ninh']");
		
		Assert.assertEquals(driver.findElement(quangninhCheckbox).getAttribute("aria-checked"),"false");
				
		driver.findElement(quangninhCheckbox).click();
		Assert.assertEquals(driver.findElement(quangninhCheckbox).getAttribute("aria-checked"),"true");
				
		sleepInSecond(2);
		
		driver.findElement(quangninhCheckbox).click();
		Assert.assertEquals(driver.findElement(quangninhCheckbox).getAttribute("aria-checked"),"false");
		
		// Click all checboxes
		List <WebElement> checboxes = driver.findElements(By.xpath("//div[@role='checkbox']"));
		for (WebElement checkbox : checboxes) {
			checkbox.click();
			sleepInSecond(1);
			Assert.assertEquals(checkbox.getAttribute("aria-checked"), "true");
		}
	}
	
	public void checkToCheckboxByJS(By by) {
		if(!driver.findElement(by).isSelected()) {
			jsExecutor.executeScript("arguments[0].click()", driver.findElement(by));
		}
	}
	
	public void uncheckToCheckboxByJS(By by) {
		if(driver.findElement(by).isSelected()) {
			jsExecutor.executeScript("arguments[0].click()", driver.findElement(by));
		}
	}
	
	public void checkToCheckbox(By byInput, By bySpan) {
		if (!driver.findElement(byInput).isSelected()) {
			driver.findElement(bySpan).click();
		}
	}
	
	public void uncheckToCheckbox(By byInput, By bySpan) {
		if (driver.findElement(byInput).isSelected()) {
			driver.findElement(bySpan).click();
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
