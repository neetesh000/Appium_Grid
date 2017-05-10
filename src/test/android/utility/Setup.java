package test.android.utility;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class Setup {

	private JSONObject testData;
	private static DesiredCapabilities defaultCapabilities;
	private final String testClass;

	private Properties config;

	public Setup(String testClass) throws IOException {
		config = Utility.getConfig();
		defaultCapabilities = new DesiredCapabilities();

		defaultCapabilities.setCapability(MobileCapabilityType.APP, new File(config.getProperty("appRelativePath")));
		defaultCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, config.getProperty("appPackage"));

		this.testClass = testClass;

	}

	public JSONObject getTestData() throws JSONException, IOException {
		
		String filePath = config.getProperty("testDataRepository") + this.testClass + ".json";
		System.out.println("----- Test Data file path: " + filePath);
		
		File testDataFile = new File(filePath);
		testData = new JSONObject(FileUtils.readFileToString(testDataFile, "UTF-8"));
		return testData;
	}

	public DesiredCapabilities getDefaultCapabilities() {
		return new DesiredCapabilities(defaultCapabilities);
	}

	public AndroidDriver<AndroidElement> createDriver(DesiredCapabilities capabilities, Device device, String port)
			throws IOException, InterruptedException {

		defaultCapabilities.asMap().forEach((String key, Object value) -> capabilities.setCapability(key, value));
		
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, device.name());
		capabilities.setCapability(MobileCapabilityType.VERSION, device.osVersion());
		capabilities.setCapability(MobileCapabilityType.UDID, device.deviceID());
		
		return new AndroidDriver<>(new URL("http://127.0.0.1:" + port + "/wd/hub"), capabilities);

	}

	public AndroidDriver<AndroidElement> createDriver(Device device, String port)
			throws IOException, InterruptedException {

		return createDriver(new DesiredCapabilities(), device, port);

	}

}
