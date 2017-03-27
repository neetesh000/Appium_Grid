package example;

import java.io.File;

import libs.AppiumManager;
import libs.BaseTest;

import org.openqa.selenium.By;

import io.appium.java_client.android.Connection;

public class CalcExample extends BaseTest {
	
	public CalcExample() {
		super();
	}
	
	public CalcExample(int num){
		super(num);
	}

	public void performOperations() {

		try {
			driver.findElement(By.id("cling_dismiss")).click();
			driver.findElement(By.id("com.android2.calculator3:id/digit5")).click();
			driver.findElement(By.id("com.android2.calculator3:id/plus")).click();
			driver.findElement(By.id("com.android2.calculator3:id/digit9")).click();
			driver.findElement(By.id("com.android2.calculator3:id/equal")).click();
			driver.setConnection(Connection.AIRPLANE);
			
			String num = driver.findElement(By.xpath("//android.widget.EditText[@index=0]")).getText();
			System.out.println("Result : " + num);
			
			driver.findElement(By.id("com.android2.calculator3:id/digit8")).click();
			driver.findElement(By.id("com.android2.calculator3:id/plus")).click();
			driver.findElement(By.id("com.android2.calculator3:id/digit7")).click();
			driver.findElement(By.id("com.android2.calculator3:id/equal")).click();
			driver.setConnection(Connection.ALL);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			driver.quit();
		}
	}

	public void run() {
		File app = new File("resources/AndroidCalculator.apk");
		String appPath = app.getAbsolutePath();
		createDriver(appPath); // create devices
		try {
			performOperations(); // user function
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			appiumMan.killServer();
		}

	}

	public static void main(String[] args) {

		CalcExample calc = new CalcExample();
		calc.execute();
	}
}
