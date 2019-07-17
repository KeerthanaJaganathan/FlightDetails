package com.fli.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.fli.qa.util.TestUtil;
import com.fli.qa.util.WebEventListener;
import com.google.common.base.Function;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;

	public TestBase() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					"/Users/sathya/Desktop/PageObjectModel1/src/main/java/com/fli/qa/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void initialization() {
		String browserName = prop.getProperty("browser");

		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
			driver = new ChromeDriver();
		}

		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with
		// EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);

		driver.get(prop.getProperty("url"));

	}

	public boolean fluentWaitClickable(WebDriver driver, WebElement element) {
		boolean bReturn = false;
		try {
			System.out.println("entered element wait");
			Wait<WebDriver> wait = new FluentWait<WebDriver>((WebDriver) driver)
					.withTimeout(30, TimeUnit.SECONDS).pollingEvery(3, TimeUnit.SECONDS)
					.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class);
			// wait.until(ExpectedConditions.visibilityOf(element));
			wait.until(ExpectedConditions.elementToBeClickable(element));

			// Reporter.reportStep("The element with locator:" + element + "is
			// identified successfully.", "INFO", driver);
			bReturn = true;
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			// Reporter.reportStep("There is no such element with locator: " +
			// element + " identified by driver", "FAIL",
			// driver);
			driver.close();
		} catch (StaleElementReferenceException e) {
			e.printStackTrace();
			// Reporter.reportStep("There is no such element with locator: " +
			// element + " in the DOM", "FAIL", driver);
			driver.close();
		}
		return bReturn;
	}

	public void fluentWaitSearchForPresenceOfElement(WebDriver driver, final WebElement element) throws InterruptedException {

		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
		wait.pollingEvery(500, TimeUnit.MILLISECONDS);
		wait.withTimeout(30, TimeUnit.SECONDS);

		Function<WebDriver, WebElement> function = new Function<WebDriver, WebElement>() {
			@Override
			public WebElement apply(WebDriver arg0) {
				// System.out.println("Checking for the element!!");
				// WebElement element = arg0.findElement(By.id("target"));
				if (element != null) {
					System.out.println("Target element found");

				}
				return element;
			}
		};

		wait.until(function);
	}

}
