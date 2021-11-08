package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Video18_Custom_Dropdown {
	
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("firefox.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		// wait để apply cho các trạng thái của element (visible/invisible/presence/clickable/...)
		explicitWait = new WebDriverWait(driver, 15);
		
		// ép kiểu tường minh (Reference casting)
		jsExecutor = (JavascriptExecutor) driver;
		
		
		// wait để tìm element (findElement/findElements)
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@Test
	public void TC01_Custom_Dropdown_JQuery() {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		
		By parentItem = By.id("number-button");
		By childItems = By.xpath("//ul[@id='number-menu']//div");
		
		selectItemInDropDown(parentItem, childItems, "5");
		sleepInSecond(1);
		Assert.assertTrue(isElementDisplayed(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='5']"))));
		
		selectItemInDropDown(parentItem, childItems, "19");
		sleepInSecond(1);
		Assert.assertTrue(isElementDisplayed(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='19']"))));
		
		selectItemInDropDown(parentItem, childItems, "1");
		sleepInSecond(1);
		Assert.assertTrue(isElementDisplayed(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='1']"))));
		
		selectItemInDropDown(parentItem, childItems, "15");
		sleepInSecond(1);
		Assert.assertTrue(isElementDisplayed(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text' and text()='15']"))));
	}
	
	@Test
	public void TC02_Custom_Dropdown_ReactJS() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		
		By parentItem = By.xpath("//div[@role='listbox']");
		By childItems = By.xpath("//div[@role='option']//span");
		
		selectItemInDropDown(parentItem, childItems, "Jenny Hess");
		sleepInSecond(1);
		Assert.assertTrue(isElementDisplayed(driver.findElement(By.xpath("//div[@role='alert' and text()='Jenny Hess']"))));
		
		selectItemInDropDown(parentItem, childItems, "Elliot Fu");
		sleepInSecond(1);
		Assert.assertTrue(isElementDisplayed(driver.findElement(By.xpath("//div[@role='alert' and text()='Elliot Fu']"))));
		
		selectItemInDropDown(parentItem, childItems, "Stevie Feliciano");
		sleepInSecond(1);
		Assert.assertTrue(isElementDisplayed(driver.findElement(By.xpath("//div[@role='alert' and text()='Stevie Feliciano']"))));
		
		selectItemInDropDown(parentItem, childItems, "Christian");
		sleepInSecond(1);
		Assert.assertTrue(isElementDisplayed(driver.findElement(By.xpath("//div[@role='alert' and text()='Christian']"))));
		
		selectItemInDropDown(parentItem, childItems, "Matt");
		sleepInSecond(1);
		Assert.assertTrue(isElementDisplayed(driver.findElement(By.xpath("//div[@role='alert' and text()='Matt']"))));
		
		selectItemInDropDown(parentItem, childItems, "Justen Kitsune");
		sleepInSecond(1);
		Assert.assertTrue(isElementDisplayed(driver.findElement(By.xpath("//div[@role='alert' and text()='Justen Kitsune']"))));
	}
	
	@Test
	public void TC03_Custom_Dropdown_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		
		By parentItem = By.xpath("//li[@class='dropdown-toggle']");
		By childItems = By.xpath("//ul[@class='dropdown-menu']//a");
		
		selectItemInDropDown(parentItem, childItems, "First Option");
		sleepInSecond(1);
		Assert.assertTrue(isElementDisplayed(driver.findElement(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'First Option')]"))));
		
		selectItemInDropDown(parentItem, childItems, "Second Option");
		sleepInSecond(1);
		Assert.assertTrue(isElementDisplayed(driver.findElement(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'Second Option')]"))));
		
		selectItemInDropDown(parentItem, childItems, "Third Option");
		sleepInSecond(1);
		Assert.assertTrue(isElementDisplayed(driver.findElement(By.xpath("//li[@class='dropdown-toggle' and contains(text(),'Third Option')]"))));
	}
	
	public void selectItemInDropDown(By parentBy, By childBy, String expectedItem) {
		// 1 - Click vào element để xổ ra all items
		driver.findElement(parentBy).click();
		
		// 2 - Wait cho tất cả element được load ra (có trong HTML/DOM)
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(childBy));
		
		// store lại tất cả các element (all items của dropdown list)
		List<WebElement> allItems = driver.findElements(childBy);
		
		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedItem)){
				if (item.isDisplayed()) { // 3 - Nếu item mình cần chọn nó nằm trong view (nhìn thấy được) thì click vào
					System.out.println("Item is displayed in dropdown list");
					item.click();	
				} else { // 4 - Nếu item mình cần chọn ko nhìn thấy được (bị che bên dưới) thì scroll xuống và click vào
					System.out.println("Item is NOT displayed in dropdown list");
					jsExecutor.executeScript("agruments[0].scrollIntoView(true);", item);
					item.click();
				}
			}	
		}
	}
	
	public boolean isElementDisplayed(WebElement element) {
		if (element.isDisplayed()) {
			System.out.println("Item [" + element.getText() + "] is selected and displayed correctly");
			return true;
		} else {
			System.out.println("Item [" + element.getText() + "] is NOT displayed");
			return false;
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
		driver.quit();
	}
}
