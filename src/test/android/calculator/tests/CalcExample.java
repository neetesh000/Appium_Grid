package test.android.calculator.tests;

import java.io.IOException;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

import test.android.pages.SimpleCalculatorPage;
import test.android.utility.BaseTest;
import test.android.utility.Setup;

public class CalcExample extends BaseTest {
	
	private SimpleCalculatorPage simpleCalculatorPage;

	@BeforeClass
	public void beforeClass() throws IOException, InterruptedException{
		
		Setup setup = new Setup(this.getClass().getSimpleName());
		
		this.driver = setup.createDriver(device, port);
		
		simpleCalculatorPage = new SimpleCalculatorPage(driver);
		
		simpleCalculatorPage.dismissIntro();
		
	}
	
	@BeforeMethod
	public void beforeMethod() throws IOException, InterruptedException{
		
		logger.info("Restarting the app");
		driver.startActivity(config.getProperty("appPackage"), config.getProperty("appActivity"));
		
	}

	@Test
	public void testEdition() {
		
		simpleCalculatorPage = new SimpleCalculatorPage(driver);
		
		logger.info("perorming operation 3+9=12");
		simpleCalculatorPage.clickDigit3().clickPlusButton().clickDigit9().clickEqual();
		
		assertEquals(simpleCalculatorPage.getOperationResult(), 12+"");

	}
	
	@Test
	public void testMultiplication1() {
		
		simpleCalculatorPage = new SimpleCalculatorPage(driver);
		
		logger.info("perorming operation 4*6=24");
		simpleCalculatorPage.clickDigit4().clickMultiplicationButton().clickDigit6().clickEqual();
		
		//Failing Assertion because of deliberately written wrong result
		assertEquals(simpleCalculatorPage.getOperationResult(), 25+""); 
		

	}
	
	@Test
	public void testMultiplication2() {
		
		SimpleCalculatorPage simpleCalculatorPage = new SimpleCalculatorPage(driver);
		
		logger.info("perorming operation 3*9=27");
		simpleCalculatorPage.clickDigit3().clickMultiplicationButton().clickDigit9().clickEqual();
		
		assertEquals(simpleCalculatorPage.getOperationResult(), 27+""); 
		

	}
	
}
