package android.calculator.tests;

import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import java.io.IOException;

import org.testng.annotations.BeforeMethod;

import android.calculator.pages.SimpleCalculatorPage;
import appium.manager.AppiumDriverManager;
import io.appium.java_client.android.Activity;


public class CalcExample extends BaseTest {

	private SimpleCalculatorPage simpleCalculatorPage;

	@BeforeClass
	public void beforeClass() throws IOException, InterruptedException {

		AppiumDriverManager setup = new AppiumDriverManager(this.getClass().getSimpleName());

		this.driver = setup.createDriver(device, port);

		simpleCalculatorPage = new SimpleCalculatorPage(driver);

		simpleCalculatorPage.dismissIntro();

	}

	@BeforeMethod
	public void beforeMethod() throws IOException, InterruptedException {

		logger.info("Restarting the app");
		driver.startActivity(new Activity(config.getProperty("appPackage"), config.getProperty("appActivity")));

	}
	
	@AfterMethod
	public void afterMethod() {
		
		driver.closeApp();

	}

	@Test
	public void testEdition() {

		simpleCalculatorPage = new SimpleCalculatorPage(driver);

		logger.info("perorming operation 3+9=12");
		simpleCalculatorPage.clickDigit3().clickPlusButton().clickDigit9().clickEqual();

		Assert.assertEquals(simpleCalculatorPage.getOperationResult(), 12 + "");

	}

	@Test
	public void testMultiplication1() {

		simpleCalculatorPage = new SimpleCalculatorPage(driver);

		logger.info("perorming operation 4*6=24");
		simpleCalculatorPage.clickDigit4().clickMultiplicationButton().clickDigit6().clickEqual();

		// Failing Assertion because of deliberately written wrong result
		Assert.assertEquals(simpleCalculatorPage.getOperationResult(), 25 + "");

	}

	@Test
	public void testMultiplication2() {

		SimpleCalculatorPage simpleCalculatorPage = new SimpleCalculatorPage(driver);

		logger.info("perorming operation 3*9=27");
		simpleCalculatorPage.clickDigit3().clickMultiplicationButton().clickDigit9().clickEqual();

		Assert.assertEquals(simpleCalculatorPage.getOperationResult(), 27 + "");

	}

}
