package com.fli.qa.pages;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fli.qa.base.TestBase;

public class FlightsPageAction extends TestBase {

	String redDealValue;
	
	@FindBy(how = How.XPATH, using = "//div[@class='subtotal-label']/span//following::div[@class='price-table']//span[text()='$0']")
	public WebElement subtotalZero;
	
	@FindBy(how = How.XPATH, using = "//div[@class='subtotal-label']/span//following::div[@class='price-table']//span")
	public WebElement subtotal;

	@FindBy(how = How.XPATH, using = "//a[@class='qantas-logo-big']")
	public WebElement flightCompanyLogo;
	
	
	@FindBy(how = How.XPATH, using = "//div[@class='fare-cell fare_AUAURED1JQ idx_0 not-selected with-sep']")
	public WebElement firstRedDeal;
	
	@FindBy(how = How.XPATH, using = "//div[@class='price-recap hidden-sm-down ng-tns-c64-8 ng-star-inserted']//following::div[@class='ng-tns-c64-8 ng-star-inserted'][2]")
	public WebElement redDealValueText;
	
	@FindBy(how = How.XPATH, using = "//button[@name='add-to-cart']")
	public WebElement addButton;
	
	@FindBy(how = How.XPATH, using = "//button[@id='btn-continue']")
	public WebElement continueButton;
	
	@FindBy(how = How.XPATH, using = "//button[@id='btn-accept']")
	public WebElement acceptButton;
	
	
	
	// Initializing the Page Objects:
	public FlightsPageAction() {
		PageFactory.initElements(driver, this);
	}

	public FlightsPageAction verifyPageLoad() {

		try {

			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.visibilityOf(flightCompanyLogo));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	public FlightsPageAction assertSubtotal() {
		try {
			fluentWaitSearchForPresenceOfElement(driver, subtotalZero);
			assertEquals(subtotalZero.getText(), "$0");
			

		} catch (Exception e) {

		}
		return this;
	}

	public FlightsPageAction clickRedDeal() {

		try {
			fluentWaitSearchForPresenceOfElement(driver,firstRedDeal);
			firstRedDeal.click();
			redDealValue = redDealValueText.getText();
			fluentWaitSearchForPresenceOfElement(driver,addButton);
			addButton.click();
			fluentWaitSearchForPresenceOfElement(driver, subtotal);
			String newte = subtotal.getText();
			String newte1 =newte.substring(0, newte.length() - 1);
			assertEquals(newte1, redDealValue);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	public FlightsPageAction clickContinue() {

		try {
			continueButton.click();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	public FlightsPageAction clickContinue_2() {

		try {
			
			WebDriverWait wait = new WebDriverWait(driver, 90);
			wait.until(ExpectedConditions.visibilityOf(flightCompanyLogo));
			Thread.sleep(10000);
			fluentWaitClickable(driver, continueButton);
			Thread.sleep(2000);
			continueButton.click();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}
	

	public FlightsPageAction clickAccept() {

		try {
			fluentWaitClickable(driver, acceptButton);
			acceptButton.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	
	
}
