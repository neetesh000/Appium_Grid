package test.android.pages.objects;

import org.openqa.selenium.support.FindBy;

import io.appium.java_client.android.AndroidElement;

public class SimpleCalculatorPageObjects {
	
	@FindBy(id="digit1")
	protected AndroidElement digit1;
	
	@FindBy(id="digit2")
	protected AndroidElement digit2;
	
	@FindBy(id="digit3")
	protected AndroidElement digit3;
	
	@FindBy(id="digit4")
	protected AndroidElement digit4;
	
	@FindBy(id="digit5")
	protected AndroidElement digit5;
	
	@FindBy(id="digit6")
	protected AndroidElement digit6;
	
	@FindBy(id="digit7")
	protected AndroidElement digit7;
	
	@FindBy(id="digit8")
	protected AndroidElement digit8;
	
	@FindBy(id="digit9")
	protected AndroidElement digit9;
	
	@FindBy(id="digit0")
	protected AndroidElement digit0;
	
	@FindBy(id="dot")
	protected AndroidElement dot;
	
	@FindBy(id="div")
	protected AndroidElement divisionButton;
	
	@FindBy(id="mul")
	protected AndroidElement multiplicationButton;
	
	@FindBy(id="minus")
	protected AndroidElement subtractionButton;
	
	@FindBy(id="plus")
	protected AndroidElement additionButton;
	
	@FindBy(id="equal")
	protected AndroidElement equalButton;
	
	@FindBy(xpath="//android.widget.EditText[@index=0]")
	protected AndroidElement result;
	
	@FindBy(id="overflow_menu")
	protected AndroidElement overflowMenu;
	
	@FindBy(id="cling_dismiss")
	protected AndroidElement introduction;
	
	
	

}
