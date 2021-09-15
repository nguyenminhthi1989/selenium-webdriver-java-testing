package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Video16_Textbox_Textarea {

	WebDriver driver;
	JavascriptExecutor jsExecutor;

	String randomEmail, randomUserID, randomPassword, loginPageURL, customerID;
	String customerName, gender, dateOfBirthInput, dateOfBirthOutput, addressInput, addressOutput, city, state, pin,
			mobileNumber, email, password;
	String editAddressInput, editAddressOutput, editCity, editState, editPin, editMobileNumber, editEmail;

	By nameTextboxBy = By.name("name");
	By genderRadioBy = By.xpath("//input[@name='rad1' and @value='m']");
	By dayOfBirthTextboxBy = By.name("dob");
	By addressTextareaBy = By.name("addr");
	By cityTextboxBy = By.name("city");
	By stateTextboxBy = By.name("state");
	By pinTextboxBy = By.name("pinno");
	By mobileNumberTextboxBy = By.name("telephoneno");
	By emailTextboxBy = By.name("emailid");
	By passwordTextboxBy = By.name("password");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;

		randomEmail = "test" + getRandomNumber() + "@gmail.net";
		loginPageURL = "http://demo.guru99.com/v4";

		// data to input when add new customer
		customerName = "Automation Test";
		gender = "male";
		dateOfBirthInput = "29-06-1989";
		dateOfBirthOutput = "1989-06-29";
		addressInput = "2906 Thai Sanh Hanh\nWard9";
		addressOutput = "2906 Thai Sanh Hanh Ward9";
		city = "My Tho";
		state = "Tien Giang";
		pin = "654321";
		mobileNumber = "0987654321";
		email = "customer" + getRandomNumber() + "@gmail.net";
		password = "123456";

		// data to input when edit customer info
		editAddressInput = "16 Lo Ma,\nWard9";
		editAddressOutput = "16 Lo Ma, Ward9";
		editCity = "MT";
		editState = "TG";
		editPin = "445566";
		editMobileNumber = "0985588990";
		editEmail = "customer" + getRandomNumber() + "@hotmail.com";

		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC01_1_Textbox_Textarea_Login() {
		// login by random email to automatically generate userID and password
		driver.get(loginPageURL);
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.name("emailid")).sendKeys(randomEmail);
		driver.findElement(By.name("btnLogin")).click();

		randomUserID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		randomPassword = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();

		// login by provided userID and password
		driver.get(loginPageURL);
		driver.findElement(By.name("uid")).sendKeys(randomUserID);
		driver.findElement(By.name("password")).sendKeys(randomPassword);
		driver.findElement(By.name("btnLogin")).click();

		// verify homepage displaying
		sleepInSecond(2);
		Assert.assertTrue(driver
				.findElement(By
						.xpath("//marquee[@class='heading3' and text()=\"Welcome To Manager's Page of Guru99 Bank\"]"))
				.isDisplayed());
	}

	@Test
	public void TC01_2_Textbox_Textarea_NewCustomer() {
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();

		// input data for new customer
		driver.findElement(nameTextboxBy).sendKeys(customerName);
		driver.findElement(genderRadioBy).click();

		// change datetime picker type to textbox to input data for dateOfBirth
		jsExecutor.executeScript("arguments[0].removeAttribute('type')", driver.findElement(dayOfBirthTextboxBy));
		driver.findElement(dayOfBirthTextboxBy).sendKeys(dateOfBirthInput);
		sleepInSecond(2);

		driver.findElement(addressTextareaBy).sendKeys(addressInput);
		driver.findElement(cityTextboxBy).sendKeys(city);
		driver.findElement(stateTextboxBy).sendKeys(state);
		driver.findElement(pinTextboxBy).sendKeys(pin);
		driver.findElement(mobileNumberTextboxBy).sendKeys(mobileNumber);
		driver.findElement(emailTextboxBy).sendKeys(email);
		driver.findElement(passwordTextboxBy).sendKeys(password);
		sleepInSecond(2);

		driver.findElement(By.name("sub")).click();

		// verify data of created new customer
		driver.findElement(By.xpath("//p[@class='heading3' and text()='Customer Registered Successfully!!!']"))
				.isDisplayed();
		Assert.assertEquals(
				driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(),
				customerName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(),
				gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),
				dateOfBirthOutput);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),
				addressOutput);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),
				state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),
				mobileNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),
				email);
		sleepInSecond(2);

		// get customerID to edit in next testcase
		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
	}

	@Test
	public void TC01_3_Textbox_Textarea_EditCustomer() {
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.name("cusid")).sendKeys(customerID);
		driver.findElement(By.name("AccSubmit")).click();

		// input data to edit customer info
		driver.findElement(addressTextareaBy).clear();
		driver.findElement(addressTextareaBy).sendKeys(editAddressInput);
		driver.findElement(cityTextboxBy).clear();
		driver.findElement(cityTextboxBy).sendKeys(editCity);
		driver.findElement(stateTextboxBy).clear();
		driver.findElement(stateTextboxBy).sendKeys(editState);
		driver.findElement(pinTextboxBy).clear();
		driver.findElement(pinTextboxBy).sendKeys(editPin);
		driver.findElement(mobileNumberTextboxBy).clear();
		driver.findElement(mobileNumberTextboxBy).sendKeys(editMobileNumber);
		driver.findElement(emailTextboxBy).clear();
		driver.findElement(emailTextboxBy).sendKeys(editEmail);
		sleepInSecond(2);

		driver.findElement(By.name("sub")).click();

		// verify data of edited customer
		driver.findElement(By.xpath("//p[@class='heading3' and text()='Customer details updated Successfully!!!']"))
				.isDisplayed();
		Assert.assertEquals(
				driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(),
				customerName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(),
				gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),
				dateOfBirthOutput);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),
				editAddressOutput);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(),
				editCity);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),
				editState);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(),
				editPin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),
				editMobileNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),
				editEmail);
		sleepInSecond(2);
	}

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(99999);
	}

	public void sleepInSecond(long timeoutInSecond) {
		try {
			Thread.sleep(timeoutInSecond * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
