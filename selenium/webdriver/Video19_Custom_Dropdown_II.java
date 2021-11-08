package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Video19_Custom_Dropdown_II {

	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	
	@BeforeClass
	public void beforeClass() {
		//System.setProperty("firefox.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		
		//driver = new FirefoxDriver();
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 15);
		jsExecutor = (JavascriptExecutor) driver;
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	//@Test
	public void TC01_Custom_Dropdown_Angular() {
		driver.get("https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");
		
		selectItemInDropDown("//span[@aria-owns='games_options']","//ul[@id='games_options']/li","Tennis");
		sleepInSecond(1);
		Assert.assertEquals(driver.findElement(By.xpath("//span[@aria-owns='games_options']/input")).getAttribute("aria-label"), "Tennis");
		System.out.println("\n=================================================================================\n");
		
		selectItemInDropDown("//span[@aria-owns='games_options']","//ul[@id='games_options']/li","Cricket");
		sleepInSecond(1);
		Assert.assertEquals(driver.findElement(By.xpath("//span[@aria-owns='games_options']/input")).getAttribute("aria-label"), "Cricket");
		System.out.println("\n=================================================================================\n");
		
		selectItemInDropDown("//span[@aria-owns='games_options']","//ul[@id='games_options']/li","Rugby");
		sleepInSecond(1);
		Assert.assertEquals(driver.findElement(By.xpath("//span[@aria-owns='games_options']/input")).getAttribute("aria-label"), "Rugby");
		System.out.println("\n=================================================================================\n");
		
	}
	
	//@Test
	public void TC02_Custom_Dropdown_Editable_1() {
		driver.get("http://indrimuska.github.io/jquery-editable-select/");
		
		selectItemInEditableDropDown("//div[@id='default-place']/input", "//ul[@class='es-list' and @style]/li", "f", "Ford");
		sleepInSecond(2);
		//WebElement selectedItem = driver.findElement(By.xpath("//ul[@class='es-list' and @style]/li[text()='Ford']"));
		//Assert.assertTrue(selectedItem.isSelected());
		
		selectItemInEditableDropDown("//div[@id='default-place']/input", "//ul[@class='es-list' and @style]/li", "p", "Porsche");
		sleepInSecond(2);
		//Assert.assertEquals(driver.findElement(By.xpath("//ul[@class='es-list' and @style]/li[@class='es-visible selected']")).getText(), "Porsche");
		
		selectItemInEditableDropDown("//div[@id='default-place']/input", "//ul[@class='es-list' and @style]/li", "m", "Mercedes");
		sleepInSecond(2);
		//Assert.assertEquals(driver.findElement(By.xpath("//ul[@class='es-list' and @style]/li[@class='es-visible selected']")).getText(), "Mercedes");
	}
	
	//@Test
	public void TC03_Custom_Dropdown_Editable_2() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		
		//selectItemInEditableDropDown("//div[@role='combobox']/input", "//div[@role='listbox']/div", "a", "Angola");
		selectItemInEditableDropDown("//input[@class='search']", "//div[@role='option']/span", "a", "Angola");
		sleepInSecond(2);
		WebElement selectedItem1 = driver.findElement(By.xpath("//div[@class='divider text']"));
		Assert.assertEquals(selectedItem1.getText(), "Angola");
		System.out.println("Selected item is " + selectedItem1.getText());
		System.out.println("\n=========================================================\n");
		
		selectItemInEditableDropDown("//div[@role='combobox']/input", "//div[@role='listbox']/div", "a", "Belarus");
		sleepInSecond(2);
		WebElement selectedItem2 = driver.findElement(By.xpath("//div[@class='divider text']"));
		Assert.assertEquals(selectedItem2.getText(), "Belarus");
		System.out.println("Selected item is " + selectedItem2.getText());
		System.out.println("\n=========================================================\n");
		
		selectItemInEditableDropDown("//div[@role='combobox']/input", "//div[@role='listbox']/div", "f", "Benin");
		sleepInSecond(2);
		WebElement selectedItem3 = driver.findElement(By.xpath("//div[@role='combobox']/input[@type='text']"));
		Assert.assertEquals(selectedItem3.getAttribute("value"), "f");
		System.out.println("Searched word is " + selectedItem3.getAttribute("value"));
		System.out.println("\n=========================================================\n");
	}
	
	@Test
	public void TC04_Custom_Dropdown_Multiple_Select() {
			
		driver.get("https://multiple-select.wenzhixin.net.cn/templates/template.html?v=189&url=basic.html");
		
		//String[] inputDataList = {"August", "October", "April", "June"};
		String[] inputDataList = {"April", "June1"};
		//String[] inputDataList = {"[Select all]"};
		selectMultiItemInDropDown("(//button[@class='ms-choice'])[1]", "(//button[@class='ms-choice'])[1]/following-sibling::div//span", inputDataList);
		//Assert.assertTrue(verifyItemSelected(inputDataList));
		Assert.assertFalse(verifyItemSelected(inputDataList));
	}
	
	public boolean verifyItemSelected(String[] inputDataList) {
		
		// 1 - verify the selected items displaying 
		String selectedItemsDisplaying = driver.findElement(By.xpath("(//button[@class='ms-choice'])[1]/span")).getText();
		System.out.println("Selected items are: " + selectedItemsDisplaying);
		
		// 2 - count the items that are selected
		
		List<WebElement> allSelectedItems = driver.findElements(By.xpath("//li[@class='selected']//input"));
		int selectedItemsNumer = allSelectedItems.size();
		
		// 3 - conditions to verify selected items
		
		if (selectedItemsNumer > 0 && selectedItemsNumer <= 3) {
			boolean status = true;
			for (String item : inputDataList) {
				if (!selectedItemsDisplaying.contains(item)) {
					System.out.println("Selected Items are not enough!");
					status = false;
					return status;
				}
			}
			System.out.println("Selected Items are less than 4 items");
			return status;
		} else if (selectedItemsNumer >= 12) {
			System.out.println("All items are selected");
			return driver.findElement(By.xpath("(//button[@class='ms-choice'])[1]/span[text()='All selected']")).isDisplayed();
		} else if (selectedItemsNumer > 3 && selectedItemsNumer < 12) {
			System.out.println("Selected Items are more than than 3 items and less than 12 items");
			return driver.findElement(By.xpath("(//button[@class='ms-choice'])[1]/span[text()='" + selectedItemsNumer + " of 12 selected']")).isDisplayed();
		} else {
			return false;
		}
	}
	
	public void selectMultiItemInDropDown(String parentLocator, String childLocator, String[] inputDataList) {
		// 1 - cick to show dropdown list
		driver.findElement(By.xpath(parentLocator)).click();
		
		// 2 - wait until all elements in HTML/DOM loaded
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childLocator)));
		
		// 3 - get all items in dropdown list and assign to a list
		List<WebElement> allItems = driver.findElements(By.xpath(childLocator));
		
		// 4 - go through each item to check then click
		
		for (WebElement item : allItems) {
			System.out.println("Item is " + item.getText());
			for (String inputData : inputDataList) {
				System.out.println("inputData is " + inputData);
				if(item.getText().trim().equals(inputData)) {
					
					//jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
					//sleepInSecond(5);
					
					item.click();
					System.out.println(item.getText() + " is selected!");
					sleepInSecond(1);
					
					// 5 - check if already select enough items
					
					List<WebElement> selectedItems = driver.findElements(By.xpath("//li[@class='selected']//input"));
					System.out.println("Item selected = " + selectedItems.size());
					System.out.println("\n====================================\n");
					if (inputDataList.length == selectedItems.size()) {
						break;
					}
				}	
			}
			
		}
	}
	
	
	public void selectItemInEditableDropDown (String parentLocator, String childLocator, String input, String expectedItem) {
		//parentLocator must be a textbox --> to clear and sendkey
		driver.findElement(By.xpath(parentLocator)).clear();
		driver.findElement(By.xpath(parentLocator)).sendKeys(input);
		sleepInSecond(2);
		
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childLocator)));
		
		List<WebElement> allItems = driver.findElements(By.xpath(childLocator));
		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedItem)) {
				System.out.println("Item " + item.getText() + " is selected");
				item.click();
				break;
			}
			else {
				System.out.println("Item " + expectedItem + " is not in the list. Please choose another item!");
			}
		}
	}
	
	public void selectItemInDropDown(String parentLocator, String childLocator, String expectedItem) {
		
		// 1 - Wait cho đến khi defined element có thể click được (clickable)
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath(parentLocator)));
		// explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath(parentLocator))).click(); --> wait to be clickable then click
		
		// 2 - Click để xổ ra all items
		driver.findElement(By.xpath(parentLocator)).click();
		sleepInSecond(1);
		
		// 3 - Wait cho đến khi all items có trong HTML DOM
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(childLocator)));
		
		
		// 4 - Lấy tất cả các item con đưa vào 1 list element và in ra tổng số item
		List<WebElement> allItems = driver.findElements(By.xpath(childLocator));
		System.out.println("Tổng số item trong dropdown list = " + allItems.size());
		
		// 5 - Duyệt qua từng item để click chọn
		for (WebElement item : allItems) {
			if (item.getText().equals(expectedItem)) {
				if (item.isDisplayed()) {
					System.out.println("Item " + item.getText() + " is clicked by Selenium!");
					item.click();
				} else {
					jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
					System.out.println("Item " + item.getText() + " is clicked by javaScript!");
					item.click();
					break;
				}
			}
			else {
				System.out.println("Item " + expectedItem + " is not in the list. Please choose another item!");
			}
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
