package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Video25_Topic11_Handle_Popup {
	
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	Actions action;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	//@Test
	public void TC01_Fixed_Popup() {
		driver.get("https://ngoaingu24h.vn/");
		By loginPopupBy = By.id("modal-login-v1");
		// Verify popup not displayed
		Assert.assertFalse(driver.findElement(loginPopupBy).isDisplayed());
		// Click to show popup
		driver.findElement(By.xpath("//button[@class='login_ icon-before']")).click();
		// Should have wait timer for pop-up displaying
		sleepInSecond(2);
		// Verify popup displayed
		Assert.assertTrue(driver.findElement(loginPopupBy).isDisplayed());
		
		driver.findElement(By.id("account-input")).sendKeys("automationfc");
		driver.findElement(By.id("password-input")).sendKeys("automationfc");
		driver.findElement(By.xpath("//button[@class='btn-v1 btn-login-v1 buttonLoading']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='row error-login-panel' and contains(.,'Tài khoản không tồn tại!')]")).isDisplayed());
		
		// Click to close popup
		driver.findElement(By.xpath("//div[@id='modal-login-v1']//button[@class='close']")).click();
		// Should have wait timer for pop-up disapppear
		sleepInSecond(2);
		// Verify popup not displayed
		Assert.assertFalse(driver.findElement(loginPopupBy).isDisplayed());
	}
	
	//@Test
	public void TC02_Random_Popup_InDom() {
		// step1
		driver.get("https://blog.testproject.io/");
		action.moveToElement(driver.findElement(By.cssSelector("#search-2 input.search-field"))).perform();
		
		// wait until page loaded successfully
		Assert.assertTrue(isPageLoadedSuccess(driver));
		//sleepInSecond(15);
		
		// step2: if pop-up displayed --> close it, if not displayed --> go to step3
		if(driver.findElement(By.className("mailch-wrap")).isDisplayed()) {
			//verify that popup displaying
			Assert.assertTrue(driver.findElement(By.className("mailch-wrap")).isDisplayed());
			System.out.println("------------------------- The pop-up is displaying! -------------------------");
			sleepInSecond(2);
			driver.findElement(By.id("close-mailch")).click();
			sleepInSecond(2);
			Assert.assertFalse(driver.findElement(By.className("mailch-wrap")).isDisplayed());
			System.out.println("------------------------- The pop-up is closed! -------------------------");
		} else {
			//verify that popup NOT displaying
			Assert.assertFalse(driver.findElement(By.className("mailch-wrap")).isDisplayed());
			System.out.println("------------------------- The pop-up is NOT displaying! -------------------------");
		}
		
		// step3
		driver.findElement(By.cssSelector("#search-2 input.search-field")).sendKeys("Selenium");
		sleepInSecond(2);
		driver.findElement(By.cssSelector("#search-2 span.glass")).click();
		
		// wait until page loaded successfully
		Assert.assertTrue(isPageLoadedSuccess(driver));
		
		List<WebElement> articleHeaders = driver.findElements(By.cssSelector("div.posts-wrap h3>a"));
		System.out.println("All article headers = " + articleHeaders.size());
		for (WebElement articleHeader : articleHeaders) {
			System.out.println(articleHeader.getText());
			Assert.assertTrue(articleHeader.getText().contains("Selenium"));
		}		
	}
	
	//@Test
	public void TC03_Random_Popup_InDom() {
		driver.get("https://vnk.edu.vn/");
		
		isPageLoadedSuccess(driver);
		sleepInSecond(30);
		
		By popupAd = By.cssSelector(".tve_flt");
		if(driver.findElement(popupAd).isDisplayed()) {
			System.out.println("------------------------- The pop-up is displaying! -------------------------");
			sleepInSecond(2);
			driver.findElement(By.cssSelector("svg.tcb-icon")).click();
			sleepInSecond(2);
			Assert.assertFalse(driver.findElement(popupAd).isDisplayed());
			System.out.println("------------------------- The pop-up is closed! -------------------------");
		} else {
			Assert.assertFalse(driver.findElement(popupAd).isDisplayed());
			System.out.println("------------------------- The pop-up is NOT displayed! -------------------------");
		}
	}
	
	@Test
	public void TC04_Radom_Popup_NotInDom() {
		driver.get("https://shopee.vn/");
		sleepInSecond(2);
		
		WebElement root1 = driver.findElement(By.tagName("shopee-banner-popup-stateful"));
	
		//Get shadow root element
		WebElement shadowRoot1 = expandRootElement(root1);
		
		WebElement root2 = shadowRoot1.findElement(By.tagName("shopee-banner-simple"));
		WebElement shadowRoot2 = expandRootElement(root2);
		
		WebElement popupAdLink = shadowRoot2.findElement(By.cssSelector("div[class='simple-banner with-placeholder']>a"));
		System.out.println("The ad link is: " + popupAdLink.getAttribute("href").toString());
		
		WebElement popupAd = shadowRoot2.findElement(By.cssSelector("div[class='simple-banner with-placeholder']"));
		if (popupAd.isDisplayed()) {
			System.out.println("------------------------- The pop-up is displaying! -------------------------");
			sleepInSecond(1);
			WebElement closePopupBtn = shadowRoot1.findElement(By.cssSelector("div[class='shopee-popup__close-btn']"));
			closePopupBtn.click();
			sleepInSecond(1);
			System.out.println("------------------------- The pop-up is closed! -------------------------");
		}
		
		// input data to search
		driver.findElement(By.className("shopee-searchbar-input__input")).sendKeys("Macbook Pro");
		sleepInSecond(1);
		driver.findElement(By.xpath("//button[contains(@class,'shopee-searchbar__search-button')]")).click();
		
		//verify searched data
		sleepInSecond(3);
		System.out.println("Data releated to searched keywords is displaying!");
		
		// move to last element of the page
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("(//div[@class='row shopee-search-item-result__items']/div/a)[15]")));
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("(//div[@class='row shopee-search-item-result__items']/div/a)[30]")));
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("(//div[@class='row shopee-search-item-result__items']/div/a)[45]")));
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("(//div[@class='row shopee-search-item-result__items']/div/a)[60]")));
		sleepInSecond(1);
		
		List<WebElement> dataList = driver.findElements(By.xpath("//div[@class='row shopee-search-item-result__items']/div/a"));
		System.out.println("Total data in a page = " + dataList.size() + "\n");
		sleepInSecond(2);
		for (WebElement dataCol : dataList) {
			//System.out.println("Link of each column is " + dataCol.getAttribute("href").toString() + "\n");
			Assert.assertTrue(dataCol.getAttribute("href").toString().toLowerCase().contains("-pro"));
		}
	}
	
	//@Test
	public void TC05_Radom_Popup_NotInDom() {
		driver.get("https://dehieu.vn/");
		//sleepInSecond(8);
		isPageLoadedSuccess(driver);
		
		List<WebElement> popupAd = driver.findElements(By.className("popup-content"));
		if(popupAd.size() > 0) {
			System.out.println("------------------------- The pop-up is displaying! -------------------------");
			sleepInSecond(1);
			driver.findElement(By.id("close-popup")).click();
			sleepInSecond(1);
			System.out.println("------------------------- The pop-up is closed! -------------------------");
		} else {
			System.out.println("------------------------- The pop-up is NOT displaying! -------------------------");
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
	
	public WebElement expandRootElement(WebElement element) {
		WebElement ele = (WebElement) ((JavascriptExecutor)driver)
	.executeScript("return arguments[0].shadowRoot", element);
		return ele;
	}
	
	public boolean isPageLoadedSuccess(WebDriver driver) {
		explicitWait = new WebDriverWait(driver, 120);
		jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}
		};
		return explicitWait.until(jQueryLoad);
	}
	
	@AfterClass
	public void afterClass() {
		//driver.quit();
	}
}
