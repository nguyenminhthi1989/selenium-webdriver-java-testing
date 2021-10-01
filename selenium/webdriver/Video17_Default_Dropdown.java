package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Video17_Default_Dropdown {
	
	WebDriver driver;
	Select select;
	String firstName, lastName, email, password;
	String dateOfBirthDay, dateOfBirthMonth, dateOfBirthYear;
	
	By genderRadioBy = By.id("gender-male");
	By firstNameTextboxBy = By.id("FirstName");
	By lastNameTextboxBy = By.id("LastName");
	By emailTextboxBy = By.id("Email");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("firefox.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		firstName = "test";
		lastName = "automation";
		email = firstName + lastName + getRandomNumber() + "@gmail.net";
		password = "123456";
		
		dateOfBirthDay = "29";
		dateOfBirthMonth = "June";
		dateOfBirthYear = "1989";
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@Test
	public void TC02_HTML_Dropdown_list() {
		driver.get("https://www.rode.com/wheretobuy");
		
		//Verify dropdown is not multiple select
		select = new Select(driver.findElement(By.id("where_country")));
		Assert.assertFalse(select.isMultiple());
		
		//select Vietnam and verify
		select.selectByVisibleText("Vietnam");
		Assert.assertEquals("Vietnam", select.getFirstSelectedOption().getText());
		
		//click Search and verify
		driver.findElement(By.id("search_loc_submit")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='result_count']//span[text()='29']")).isDisplayed());
		
		//verify store name
		System.out.println("Display name of 29 stores:");
		List<WebElement> storeName = driver.findElements(By.xpath("//div[@id='search_results']//div[@class='store_name']"));
		Assert.assertEquals(storeName.size(), 29);
		for(WebElement store : storeName) {
			System.out.println("- " + store.getText());
		}
		System.out.println("TC02_Handle_HTML_Dropdown/list_on_RODE_page");
	}
	
	@Test
	public void TC03_HTML_Dropdown_list() {
		driver.get("https://demo.nopcommerce.com/");
		driver.findElement(By.className("ico-register")).click();
		
		//input info to register
		driver.findElement(genderRadioBy).click();
		driver.findElement(firstNameTextboxBy).sendKeys(firstName);
		driver.findElement(lastNameTextboxBy).sendKeys(lastName);
		
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		select.selectByVisibleText(dateOfBirthDay);
		Assert.assertEquals(select.getOptions().size(), 32);
		
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		select.selectByVisibleText(dateOfBirthMonth);
		Assert.assertEquals(select.getOptions().size(), 13);
		
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		select.selectByVisibleText(dateOfBirthYear);
		Assert.assertEquals(select.getOptions().size(), 112);
		
		driver.findElement(emailTextboxBy).sendKeys(email);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
		
		driver.findElement(By.id("register-button")).click();
		
		//verify register successfully
		Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Your registration completed']")).isDisplayed());
		
		//verify customer info
		driver.findElement(By.className("ico-account")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='My account - Customer info']")).isDisplayed());
		driver.findElement(genderRadioBy).isSelected();
		Assert.assertEquals(driver.findElement(firstNameTextboxBy).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(lastNameTextboxBy).getAttribute("value"), lastName);
		
		select = new Select(driver.findElement(By.name("DateOfBirthDay")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), dateOfBirthDay);
		
		select = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), dateOfBirthMonth);
		
		select = new Select(driver.findElement(By.name("DateOfBirthYear")));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), dateOfBirthYear);
		
		Assert.assertEquals(driver.findElement(emailTextboxBy).getAttribute("value"), email);
		
		//finish testcase
		System.out.println("TC03_Handle_HTML_Dropdown/list_on_NopCommerce_page");
	}
	
	public int getRandomNumber() {
		Random random = new Random();
		return random.nextInt(99999);
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
