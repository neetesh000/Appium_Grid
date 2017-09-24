package android.calculator.pages;

import org.openqa.selenium.support.PageFactory;

import android.calculator.pages.objects.SimpleCalculatorPageObjects;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SimpleCalculatorPage extends SimpleCalculatorPageObjects{

	public SimpleCalculatorPage(AndroidDriver<AndroidElement> driver) {
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}
	
	public SimpleCalculatorPage clickDigit1(){
		digit1.click();
		return this;
	}
	
	public SimpleCalculatorPage clickDigit2(){
		digit2.click();
		return this;
	}
	
	public SimpleCalculatorPage clickDigit3(){
		digit3.click();
		return this;
	}
	
	public SimpleCalculatorPage clickDigit4(){
		digit4.click();
		return this;
	}
	
	public SimpleCalculatorPage clickDigit5(){
		digit5.click();
		return this;
	}
	
	public SimpleCalculatorPage clickDigit6(){
		digit6.click();
		return this;
	}
	
	public SimpleCalculatorPage clickDigit8(){
		digit8.click();
		return this;
	}
	
	public SimpleCalculatorPage clickDigit7(){
		digit7.click();
		return this;
	}
	
	public SimpleCalculatorPage clickDigit9(){
		digit9.click();
		return this;
	}
	
	public SimpleCalculatorPage clickDot(){
		dot.click();
		return this;
	}
	
	public SimpleCalculatorPage clickDigit0(){
		digit0.click();
		return this;
	}
	
	public SimpleCalculatorPage clickPlusButton(){
		additionButton.click();
		return this;
	}
	
	public SimpleCalculatorPage clickMinusButton(){
		subtractionButton.click();
		return this;
	}
	
	public SimpleCalculatorPage clickMultiplicationButton(){
		multiplicationButton.click();
		return this;
	}
	
	public SimpleCalculatorPage clickDivisionButton(){
		divisionButton.click();
		return this;
	}
	
	public SimpleCalculatorPage clickEqual(){
		equalButton.click();
		return this;
	}
	
	public String getOperationResult(){
		return result.getText();
	}

	public SimpleCalculatorPage dismissIntro(){
		introduction.click();
		return this;
	}
}
