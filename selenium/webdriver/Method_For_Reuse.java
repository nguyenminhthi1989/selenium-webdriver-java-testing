package webdriver;

public class Method_For_Reuse {
/*
	====== CREATE THE RANDOM NUMBER ========================================
	 
	Random rand = new Random();
	rand.nextInt(99999);
	System.out.println(rand);
	
	======== CHECK CURRENT OS AND THEN CLICK CTRL OR COMMAND BUTTON ===========
	 
	WebDriver driver;
	driver = new ChromeDriver();
	Keys key;
	Actions action;
	action = new Actions(driver);
	String osName = System.getProperty("os.name");
	System.out.println(osName);
	if (osName.contains("Windows")) {
		key = Keys.CONTROL;
	} else {
		key = Keys.COMMAND;
	}
	action.keyDown(key).perform();
	
	======= SPLIT URL ========================================================
	 
	String link = "http://the-internet.herokuapp.com/basic_auth";
	String[] links = link.split("//");
	System.out.println(links[0]);
	System.out.println(links[1]);
	
	======= TRIM TEXT ========================================================
	
	String text = "\r\n" + "                                First Option\r\n" + "                       ";
	System.out.println("-------" + text + "----------");
	System.out.println("-------" + text.trim() + "---------");
	Assert.assertEquals(text.trim(), "First Option");
	
	====== GET BACKGROUND COLOR THEN CONVERT FROM RGBA TO HEX ===============
	 
	String targetCircle_RGBAColor = targetCircle.getCssValue("background-color");
	System.out.println("The RGBAColor is:" + targetCircle_RGBAColor);
	String targetCircle_HEXColor = Color.fromString(targetCircle_RGBAColor).asHex().toUpperCase();
	System.out.println("The HEX color is:" + targetCircle_HEXColor);
	Assert.assertEquals("#03A9F4", targetCircle_HEXColor);
		
	======= CHECK THE PAGE LOADED SUCCESSFULLY ================================	
	 
	public boolean isPageLoadedSuccess(WebDriver driver) {
		JavascriptExecutor jsExecutor;
		WebDriverWait explicitWait;

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

	======= DEFINE LIST OF ELEMENTS =======================================	
	 
	List<WebElement> postTitles = driver.findElements(By.cssSelector("h3.post-title a"));
		for (WebElement postTitle : postTitles) {
		System.out.println("Selenium Title:" + postTitle.getText());
		Assert.assertTrue(postTitle.getText().contains("Selenium"));
	}

	======== GET SHADOW-ROOT FOR CHROMEDRIVER <=94 ============================	
	 
	public WebElement expandRootElement(WebElement element) {
		WebElement ele = (WebElement) ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot", element);
		return ele;
	}

	======== GET SHADOW-ROOT FOR CHROMEDRIVER 95 OR CHROMEDRIVER 96+ ===========
 
	public WebElement expandRootElement(WebElement shadowHost) {
		WebElement returnObj = null;
		Object shadowRoot = ((JavascriptExecutor) driver).executeScript("return arguments[0].shadowRoot", shadowHost);
		if (shadowRoot instanceof WebElement) {
			// ChromeDriver 95
			returnObj = (WebElement) shadowRoot;
		} else if (shadowRoot instanceof Map) {
			// ChromeDriver 96+
			// Based on - https://github.com/SeleniumHQ/selenium/issues/10050#issuecomment-974231601
			Map<String, Object> shadowRootMap = (Map<String, Object>) shadowRoot;
			String shadowRootKey = (String) shadowRootMap.keySet().toArray()[0];
			String id = (String) shadowRootMap.get(shadowRootKey);
			RemoteWebElement remoteWebElement = new RemoteWebElement();
			remoteWebElement.setParent((RemoteWebDriver) driver);
			remoteWebElement.setId(id);
			returnObj = remoteWebElement;
		} else {
			Assert.fail("Unexpected return type for shadowRoot in expandRootElement()");
		}
		return returnObj;
	}

	========== GET SHADOW-ROOT(by using expandRootElement method) AND ELEMENT UNDER SHADOW-ROOT =====================================
	
	====This example has 2 roots: root 1, root 2, and element belongs to root 2
	
	WebElement root1 = driver.findElement(By.tagName("root 1 value"));
	WebElement shadowRoot1 = expandRootElement(root1);
	WebElement root2 = shadowRoot1.findElement(By.tagName("root 2 value"));
	WebElement shadowRoot2 = expandRootElement(root2);
	WebElement randomPopup = shadowRoot2.findElement(By.cssSelector("element value"));

*/
}
