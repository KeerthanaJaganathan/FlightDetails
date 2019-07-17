package com.fli.qa.testcases;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fli.qa.base.TestBase;
import com.fli.qa.pages.FlightsPageAction;
import com.fli.qa.pages.HomePageAction;
import com.fli.qa.pages.OptionsPageAction;
import com.fli.qa.util.TestUtil;

public class HomePageTest extends TestBase{

	HomePageAction homePage;
	TestUtil testUtil;
	FlightsPageAction flightPage;
	OptionsPageAction optionsPage;
	String sheetName = "contacts";
	
	   
	public HomePageTest(){
			super();
	}
	
	
	@BeforeMethod
	public void setUp() throws InterruptedException {
		
		initialization();
		testUtil = new TestUtil();
		homePage = new HomePageAction();
		flightPage = new FlightsPageAction();
		optionsPage = new OptionsPageAction();
		
		//TestUtil.runTimeInfo("error", "login successful");
	}
	
	@DataProvider
	public Object[][] getFlightDetails(){
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}
	
	@Test(priority=1, dataProvider="getFlightDetails")
	public void verifyContactsPageLabel(String from, String to, String newBaggage) throws InterruptedException{

		homePage.verifyPageLoad()
		.clickOnFrom()
		.selectOneWay()
		.selectFrom(from)
		.clickOnTo()
		.selectTo(to)
		.clickOnSearch();
		flightPage.verifyPageLoad()
		.assertSubtotal()
		.clickRedDeal()
		.clickContinue()
		.clickContinue_2()
		.clickAccept();
		optionsPage.verifyPageLoad()
		.optionsPageCompleted()
		.checkBaggageSize(newBaggage)
		.checkAdditionalBaggage(newBaggage)
		.clickBack(newBaggage)
		.clickBack(newBaggage)
		.clickChangeFlight(newBaggage)
		.clickBack(newBaggage);
		driver.close();
				
	}
	

	@AfterTest
	public void tearDown(){
		driver.quit();
	}
	
	
	
	
}
