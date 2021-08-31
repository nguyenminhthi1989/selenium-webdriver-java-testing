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

public class Video14_WebElements_Commands {
	
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
	
	@Test
	public void TC01_Verify_isDisplayed() throws InterruptedException {
		
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		WebElement emailTextbox = driver.findElement(emailTextboxBy);
		if (emailTextbox.isDisplayed()) {
			emailTextbox.sendKeys("Automation Testing");
			System.out.println("Email textbox is displayed");
		} else {
			System.out.println("Email textbox is not displayed");
		}
		
		WebElement ageUnder18Radio = driver.findElement(ageUnder18RadioBy);
		if (ageUnder18Radio.isDisplayed()) {
			ageUnder18Radio.click();
			System.out.println("Age under 18 is displayed");
		} else {
			System.out.println("Age under 18 is not displayed");
		}
		
		WebElement educationTextarea = driver.findElement(educationTextareaBy);
		if (educationTextarea.isDisplayed()) {
			educationTextarea.sendKeys("Automation Testing");
			System.out.println("Education textarea is displayed");
		} else {
			System.out.println("Education textarea is not displayed");
		}
		
		WebElement user5Text = driver.findElement(user5TextBy);
		if (user5Text.isDisplayed()) {
			System.out.println("User5 text is displayed");
		} else {
			System.out.println("User5 text is not displayed");
		}
		
		Thread.sleep(500);
	}
	
	@Test
	public void TC02_Verify_isEnabled() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		WebElement emailTextbox = driver.findElement(emailTextboxBy);
		WebElement ageUnder18Radio = driver.findElement(ageUnder18RadioBy);
		WebElement educationTextarea = driver.findElement(educationTextareaBy);
		WebElement job1Dropdown = driver.findElement(job1DropdownBy);
		WebElement developmentCheckbox = driver.findElement(developmentCheckboxBy);
		WebElement slider1 = driver.findElement(slider1By);
		
		Assert.assertTrue(emailTextbox.isEnabled());
		if (emailTextbox.isEnabled()) {
			System.out.println("Email textbox is enabled");
		} else {
			System.out.println("Email textbox is not enabled");
		}
		
		Assert.assertTrue(ageUnder18Radio.isEnabled());
		if (ageUnder18Radio.isEnabled()) {
			System.out.println("Age under 18 is enabled");
		} else {
			System.out.println("Age under 18 is not enabled");
		}
		
		Assert.assertTrue(educationTextarea.isEnabled());
		if (educationTextarea.isEnabled()) {
			System.out.println("Education textarea is enabled");
		} else {
			System.out.println("Education textarea is not enabled");
		}
		
		Assert.assertTrue(job1Dropdown.isEnabled());
		if (job1Dropdown.isEnabled()) {
			System.out.println("Job Role 01 is enabled");
		} else {
			System.out.println("Job Role 01 is not enabled");
		}
		
		Assert.assertTrue(developmentCheckbox.isEnabled());
		if (developmentCheckbox.isEnabled()) {
			System.out.println("Interests development checkbox is enabled");
		} else {
			System.out.println("Interests development checkbox is not enabled");
		}
		
		Assert.assertTrue(slider1.isEnabled());
		if (slider1.isEnabled()) {
			System.out.println("Slider 01 is enabled");
		} else {
			System.out.println("Slider 01 is not enabled");
		}
		
		WebElement passwordTextbox = driver.findElement(passwordTextboxBy);
		WebElement ageDisabledRadio = driver.findElement(ageDisabledRadioBy);
		WebElement bioTextarea = driver.findElement(bioTextareaBy);
		WebElement job3Dropdown = driver.findElement(job3DropdownBy);
		WebElement disabledCheckbox = driver.findElement(disabledCheckboxBy);
		WebElement slider2 = driver.findElement(slider2By);
		
		Assert.assertFalse(passwordTextbox.isEnabled());
		if (passwordTextbox.isEnabled()) {
			System.out.println("Password textbox is enabled");
		} else {
			System.out.println("Password textbox is not enabled");
		}
		
		Assert.assertFalse(ageDisabledRadio.isEnabled());
		if (ageDisabledRadio.isEnabled()) {
			System.out.println("Age disabled radio is enabled");
		} else {
			System.out.println("Age disabled radio is not enabled");
		}
		
		Assert.assertFalse(bioTextarea.isEnabled());
		if (bioTextarea.isEnabled()) {
			System.out.println("Biography textarea is enabled");
		} else {
			System.out.println("Biography textarea is not enabled");
		}
		
		Assert.assertFalse(job3Dropdown.isEnabled());
		if (job3Dropdown.isEnabled()) {
			System.out.println("Job Role 03 is enabled");
		} else {
			System.out.println("Job Role 03 is not enabled");
		}
		
		Assert.assertFalse(disabledCheckbox.isEnabled());
		if (disabledCheckbox.isEnabled()) {
			System.out.println("Interests disabled checkbox is enabled");
		} else {
			System.out.println("Interests disabled checkbox is not enabled");
		}
		
		Assert.assertFalse(slider2.isEnabled());
		if (slider2.isEnabled()) {
			System.out.println("Slider 02 is enabled");
		} else {
			System.out.println("Slider 02 is not enabled");
		}

		Thread.sleep(500);
	}
	
	@Test
	public void TC03_Verify_isSelected() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		WebElement ageUnder18Radio = driver.findElement(ageUnder18RadioBy);
		WebElement javaLanguage = driver.findElement(javaLangugeBy);
		
		Assert.assertFalse(ageUnder18Radio.isSelected());
		Assert.assertFalse(javaLanguage.isSelected());
		
		if (ageUnder18Radio.isSelected()) {
			System.out.println("Age under 18 radio is selected");
		} else {
			System.out.println("Age under 18 radio is not selected");
		}
		
		if (javaLanguage.isSelected()) {
			System.out.println("Language java checkbox is selected");
		} else {
			System.out.println("Language java checkbox is not selected");
		}
		
		ageUnder18Radio.click();
		javaLanguage.click();
		
		Assert.assertTrue(ageUnder18Radio.isSelected());
		Assert.assertTrue(javaLanguage.isSelected());
		
		if (ageUnder18Radio.isSelected()) {
			System.out.println("Age under 18 radio is selected");
		} else {
			System.out.println("Age under 18 radio is not selected");
		}
		
		if (javaLanguage.isSelected()) {
			System.out.println("Language java checkbox is selected");
		} else {
			System.out.println("Language java checkbox is not selected");
		}
		
		ageUnder18Radio.click();
		javaLanguage.click();
		
		Assert.assertTrue(ageUnder18Radio.isSelected());
		Assert.assertFalse(javaLanguage.isSelected());
		
		if (ageUnder18Radio.isSelected()) {
			System.out.println("Age under 18 radio is selected");
		} else {
			System.out.println("Age under 18 radio is not selected");
		}
		
		if (javaLanguage.isSelected()) {
			System.out.println("Language java checkbox is selected");
		} else {
			System.out.println("Language java checkbox is not selected");
		}
		
		Thread.sleep(500);
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
