package com.fli.qa.pages;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fli.qa.base.TestBase;

public class OptionsPageAction extends TestBase {

	String newBaggage;

	@FindBy(how = How.XPATH, using = "//li[@class='done ng-star-inserted'][2]//span[text()='Completed step']//following::span[text()='Options']")
	public WebElement optionsPageCompleted;

	@FindBy(how = How.XPATH, using = "//div[@class='allowanceContainer ng-star-inserted']//following::div[text()='1 piece']")
	public WebElement baggageSizeOne;

	@FindBy(how = How.XPATH, using = "//button[@class='btn btn-link btn-inline edit-link ng-star-inserted']")
	public WebElement addBaggage;

	@FindBy(how = How.XPATH, using = "//a[@class='qantas-logo-big']")
	public WebElement flightCompanyLogo;

	@FindBy(how = How.XPATH, using = "//h1[text()='Add Additional Baggage']")
	public WebElement verifyBaggagePage;

	@FindBy(how = How.XPATH, using = "//button[@id='btn-continue']")
	public WebElement continueButton;
	
	@FindBy(how = How.XPATH, using = "//button[@id='bags-btn-continue']")
	public WebElement continueButtonAddNew;

	@FindBy(how = How.XPATH, using = "//button[@id='btn-accept']")
	public WebElement acceptButton;
	
	@FindBy(how = How.XPATH, using = "//button//span[text()='Accept']")
	public WebElement acceptButtonAddNew;
	

	@FindBy(how = How.XPATH, using = "//div[@class='allowanceContainer ng-star-inserted']//following::span[text()='Additional Baggage']")
	public WebElement additionalBaggage;

	@FindBy(how = How.XPATH, using = "//div[@class='allowanceContainer ng-star-inserted']//following::span[@class='e2e-purchased-baggage-piece ng-star-inserted']")
	public WebElement additionalBaggageText;

	@FindBy(how = How.XPATH, using = "//button[@id='btn-back']")
	public WebElement backButton;

	@FindBy(how = How.XPATH, using = "//button[text()='YES, change my flight']")
	public WebElement changeFlight;

	@FindBy(how = How.XPATH, using = "//button[@class='btn right']")
	public WebElement plusButton;

	// Initializing the Page Objects:
	public OptionsPageAction() {
		PageFactory.initElements(driver, this);
	}

	public OptionsPageAction verifyPageLoad() {

		try {

			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.visibilityOf(flightCompanyLogo));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	public OptionsPageAction optionsPageCompleted() {
		try {
			fluentWaitSearchForPresenceOfElement(driver, optionsPageCompleted);

		} catch (Exception e) {

		}
		return this;
	}

	public OptionsPageAction checkBaggageSize(String newBaggage) {

		try {
			if (newBaggage.equalsIgnoreCase("0")) {
				fluentWaitSearchForPresenceOfElement(driver, baggageSizeOne);
			} else if (newBaggage.equalsIgnoreCase("1")) {
				Thread.sleep(5000);
				
				JavascriptExecutor jse = (JavascriptExecutor)driver;
				jse.executeScript("window.scrollBy(0,1000)", "");
				addBaggage.click();
				fluentWaitSearchForPresenceOfElement(driver, verifyBaggagePage);
				continueButtonAddNew.click();
				fluentWaitSearchForPresenceOfElement(driver, acceptButton);
				acceptButtonAddNew.click();
			} else if (newBaggage.equalsIgnoreCase("2")) {
				Thread.sleep(5000);
				JavascriptExecutor jse = (JavascriptExecutor)driver;
				jse.executeScript("window.scrollBy(0,1000)", "");
				addBaggage.click();
				
				fluentWaitSearchForPresenceOfElement(driver, verifyBaggagePage);
				plusButton.click();
				continueButtonAddNew.click();
				fluentWaitSearchForPresenceOfElement(driver, acceptButton);
				acceptButtonAddNew.click();
			} else if (newBaggage.equalsIgnoreCase("3")) {
				
				Thread.sleep(5000);
				JavascriptExecutor jse = (JavascriptExecutor)driver;
				jse.executeScript("window.scrollBy(0,1000)", "");
				addBaggage.click();
				fluentWaitSearchForPresenceOfElement(driver, verifyBaggagePage);
				for (int i = 1; i <= 2; i++) {
					plusButton.click();
				}
				continueButtonAddNew.click();
				fluentWaitSearchForPresenceOfElement(driver, acceptButton);
				acceptButtonAddNew.click();
			} else if (newBaggage.equalsIgnoreCase("4")) {
				Thread.sleep(5000);
				JavascriptExecutor jse = (JavascriptExecutor)driver;
				jse.executeScript("window.scrollBy(0,1000)", "");
				addBaggage.click();
				fluentWaitSearchForPresenceOfElement(driver, verifyBaggagePage);
				for (int i = 1; i <= 3; i++) {
					plusButton.click();
				}
				continueButtonAddNew.click();
				fluentWaitSearchForPresenceOfElement(driver, acceptButton);
				acceptButtonAddNew.click();
			} else if (newBaggage.equalsIgnoreCase("5")) {
				Thread.sleep(5000);
				JavascriptExecutor jse = (JavascriptExecutor)driver;
				jse.executeScript("window.scrollBy(0,1000)", "");
				addBaggage.click();
				fluentWaitSearchForPresenceOfElement(driver, verifyBaggagePage);
				for (int i = 1; i <= 4; i++) {
					plusButton.click();
				}
				continueButtonAddNew.click();
				fluentWaitSearchForPresenceOfElement(driver, acceptButton);
				acceptButtonAddNew.click();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	public OptionsPageAction checkAdditionalBaggage(String newBaggage) {

		try {
			if (newBaggage.equalsIgnoreCase("0")) {
				fluentWaitSearchForPresenceOfElement(driver, baggageSizeOne);
			} else {
				fluentWaitSearchForPresenceOfElement(driver, additionalBaggage);
				String baggageDetails = additionalBaggageText.getText();
				assertEquals(newBaggage, baggageDetails);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	public OptionsPageAction clickBack(String newBaggage) {

		try {
			Thread.sleep(5000);
			fluentWaitClickable(driver, backButton);
			backButton.click();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	public OptionsPageAction clickChangeFlight(String newBaggage) {

		try {
			if (newBaggage.equalsIgnoreCase("0")) {
				System.out.println("There is no need to change the flight");
			} else {
				fluentWaitClickable(driver, changeFlight);
				changeFlight.click();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

}
