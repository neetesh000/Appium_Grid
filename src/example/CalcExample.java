package example;

import java.io.File;

import libs.AppiumManager;
import libs.BaseTest;

import org.openqa.selenium.By;

import io.appium.java_client.android.Connection;

public class CalcExample extends BaseTest {

	public void performOperations() {

		try {
			driver.findElement(By.id("cling_dismiss")).click();
			driver.findElement(By.id("com.android2.calculator3:id/digit5")).click();
			driver.findElement(By.id("com.android2.calculator3:id/plus")).click();
			driver.findElement(By.id("com.android2.calculator3:id/digit9")).click();
			driver.findElement(By.id("com.android2.calculator3:id/equal")).click();
			driver.setConnection(Connection.DATA);
			driver.setConnection(Connection.ALL);
			String num = driver.findElement(By.xpath("//android.widget.EditText[@index=0]")).getText();
			System.out.println("Result : " + num);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			driver.quit();
		}
	}

	public void run() {
		File app = new File("src/example/AndroidCalculator.apk");
		String appPath = app.getAbsolutePath();
		AppiumManager man = createDriver(appPath); // create devices
		try {
			performOperations(); // user function
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			man.killServer();
		}

	}

	public static void main(String[] args) {

		CalcExample calc = new CalcExample();
		calc.execute();
	}
}
