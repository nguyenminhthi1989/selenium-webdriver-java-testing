package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Video24_Topic10_User_Interaction {
	
	WebDriver driver;
	Actions action;
	Alert alert;
		
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		action = new Actions(driver);
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	//@Test
	public void TC01_Right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		
		// Quit icon
		By quitIconBy = By.xpath("//li[contains(@class,'context-menu-icon-quit')]");
		// right click on the button
		action.contextClick(driver.findElement(By.xpath("//span[@class='context-menu-one btn btn-neutral']"))).perform();
		// verify Quit icon displaying
		Assert.assertTrue(driver.findElement(quitIconBy).isDisplayed());
		// move mouse to Quit icon
		action.moveToElement(driver.findElement(quitIconBy)).perform();
		// verify mouse is over Quit icon
		Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit') and contains(@class,'context-menu-hover')]")).isDisplayed());
		// click Quit icon
		action.click(driver.findElement(quitIconBy)).perform();
		// Verify text in the alert
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "clicked: quit");
		// click OK to close the Alert
		alert.accept();
		// verify Quit icon NOT displaying
		Assert.assertFalse(driver.findElement(quitIconBy).isDisplayed());
		
		// Copy icon
		By copyIconBy = By.xpath("//li[contains(@class,'context-menu-icon-copy')]");
		// right click on the button
		action.contextClick(driver.findElement(By.xpath("//span[@class='context-menu-one btn btn-neutral']"))).perform();
		// verify Copy icon displaying
		Assert.assertTrue(driver.findElement(copyIconBy).isDisplayed());
		// move mouse to Copy icon
		action.moveToElement(driver.findElement(copyIconBy)).perform();
		// verify mouse is over Copy icon
		Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-copy') and contains(@class,'context-menu-hover')]")).isDisplayed());
		// click Copy icon
		action.click(driver.findElement(copyIconBy)).perform();
		// Verify text in the alert
		alert = driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "clicked: copy");
		// click OK to close the Alert
		alert.accept();
		// verify Copy icon NOT displaying
		Assert.assertFalse(driver.findElement(copyIconBy).isDisplayed());
	}
	
	@Test
	public void TC02_drag_drop_HTML4() {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		
		WebElement sourceItem = driver.findElement(By.id("draggable"));
		WebElement targetItem = driver.findElement(By.id("droptarget"));
		
		// Verify text before dragged
		Assert.assertEquals(targetItem.getText(), "Drag the small circle here.");
		
		// 1st option - Drag source item to target item
		//action.dragAndDrop(sourceItem, targetItem).perform();
		// 2nd option - Click and hold > move to element > release
		action.clickAndHold(sourceItem).moveToElement(targetItem).release().perform();
		
		// Verify text after dragged
		Assert.assertEquals(targetItem.getText(), "You did great!");
		
		// Verify background color after dragged
		String backgroundTargetRGBAColor = targetItem.getCssValue("background-color");
		System.out.println("RGBA background color is: " + backgroundTargetRGBAColor);
		String backgroundTargetHexColor = Color.fromString(backgroundTargetRGBAColor).asHex().toLowerCase();
		System.out.println("Hexa background color is: " + backgroundTargetHexColor);
		Assert.assertEquals(backgroundTargetHexColor, "#03a9f4");
	}
	
	public void sleepInSecond(int sleep){
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
