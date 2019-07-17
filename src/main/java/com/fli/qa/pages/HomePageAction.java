package com.fli.qa.pages;

import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fli.qa.base.TestBase;

public class HomePageAction extends TestBase {

	@FindBy(how = How.XPATH, using = "//a[@class='logo-qantas']")
	public WebElement flightCompanyLogo;

	@FindBy(how = How.ID, using = "typeahead-input-from")
	public WebElement fromDropDown;

	@FindBy(how = How.XPATH, using = "//li[@id='typeahead-list-item-from-0']")
	public WebElement fromDropDownValue;

	@FindBy(how = How.ID, using = "typeahead-input-to")
	public WebElement toDropDown;

	@FindBy(how = How.XPATH, using = "//li[@id='typeahead-list-item-to-0']")
	public WebElement toDropDownValue;

	@FindBy(how = How.XPATH, using = "//button[@class='qfa1-submit-button__button'][text()='SEARCH FLIGHTS']")
	public WebElement searchButton;

	@FindBy(how = How.XPATH, using = "//div[@class='widget-form__row-container widget-form__row-container--no-top-padding-medium-up widget-form__row-container--animate-on-expand']//input[@id='oneway']//following::label[1]")
	public WebElement oneWayRadioButton;

	private Calendar today;

	// Initializing the Page Objects:
	public HomePageAction() {
		PageFactory.initElements(driver, this);
	}

	public HomePageAction verifyPageLoad() {

		try {

			WebDriverWait wait = new WebDriverWait(driver, 60);
			wait.until(ExpectedConditions.visibilityOf(flightCompanyLogo));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	public HomePageAction selectOneWay() {
		try {
			fluentWaitClickable(driver, oneWayRadioButton);
			Thread.sleep(2000);
			oneWayRadioButton.click();

		} catch (Exception e) {

		}
		return this;
	}

	public HomePageAction clickOnFrom() {

		try {
			fluentWaitClickable(driver, fromDropDown);
			fromDropDown.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	public HomePageAction selectFrom(String fromValue) {

		try {
			fluentWaitClickable(driver, fromDropDown);
			fromDropDown.clear();
			fluentWaitClickable(driver, fromDropDown);
			fromDropDown.sendKeys(fromValue, Keys.TAB);
			fromDropDown.click();
			fromDropDownValue.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	public HomePageAction clickOnTo() {

		try {
			fluentWaitClickable(driver, toDropDown);
			toDropDown.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	public HomePageAction selectTo(String toValue) {

		try {
			fluentWaitClickable(driver, toDropDown);
			toDropDown.clear();
			fluentWaitClickable(driver, toDropDown);
			toDropDown.sendKeys(toValue, Keys.TAB);
			Thread.sleep(2000);
			toDropDown.click();
			toDropDownValue.click();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	public HomePageAction clickOnSearch() {

		try {
			fluentWaitClickable(driver, searchButton);
			searchButton.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	public HomePageAction chooseDate() {
		try {
			//today = getCurrentDay();
			
			today = Calendar.getInstance();
			today.add(Calendar.DATE, +15);


			String FromDate = today.get(Calendar.DAY_OF_WEEK)  + ", the " + today.get(Calendar.DAY_OF_MONTH) + " of "
					+ today.get(Calendar.MONTH)+ " " + today.get(Calendar.YEAR);

		
			WebElement dateWidgetFrom = driver.findElement(By.xpath("//input[@id='datepicker-input-departureDate']"));
			dateWidgetFrom.click();

			WebElement widget = driver.findElement(By.xpath("//div[@class='date-picker__calendar-container']"));
			List<WebElement> columns = widget.findElements(By.tagName("td"));

			for (WebElement cell : columns) {

				if (cell.getText().equals(FromDate)) {
					cell.click();
					break;
				}
			}
		}

		catch (Exception e) {

		}
		return this;
	}

//	public HomePageAction chooseDate() {
//		try {
//			today = Calendar.getInstance();
//			today.add(Calendar.DATE, +15);
//			String FromDate =(today.get(Calendar.MONTH) + 1) + "/" + today.get(Calendar.DATE) + "/" + today.get(Calendar.YEAR);
//
//			((JavascriptExecutor)driver).executeScript("arguments[0].value=arguments[1]", driver.findElement(By.xpath("//input[@id='datepicker-input-departureDate']")), FromDate);
//
//		}
//		catch(Exception e) {
//			
//		}
//		return this;
//	}

	private String getCurrentDay() {
		// Create a Calendar Object
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

		// Get Current Day as a number
		int todayInt = calendar.get(Calendar.DAY_OF_MONTH);
		System.out.println("Today Int: " + todayInt + "\n");

		// Integer to String Conversion
		String todayStr = Integer.toString(todayInt);
		System.out.println("Today Str: " + todayStr + "\n");

		return todayStr;
	}
	
	
	
}
