package android.calculator.tests;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.FileAppender;
import org.apache.logging.log4j.core.appender.FileAppender.Builder;
import org.apache.logging.log4j.core.config.AppenderRef;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import android.calculator.utility.Util;
import appium.manager.AppiumServerManager;
import appium.manager.Device;
import net.sourceforge.tess4j.*;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class BaseTest {


	protected AppiumServerManager appiumManager;

	public Logger logger;

	protected String port;

	protected Device device;

	protected AndroidDriver<AndroidElement> driver;
	
	public static final Map<String,Logger> loggers = new HashMap<>();
	
	protected static Properties config;


	@BeforeSuite
	public void beforeSuit() throws IOException {
		
		config = Util.getConfig();
		
		File logDirectory = new File(config.getProperty("logsDirectory"));
		File screenshotsDirec = new File(config.getProperty("screenshotsDirectory")); // "target/screenshots"

		if (screenshotsDirec.exists())
			FileUtils.forceDelete(screenshotsDirec);

		if (logDirectory.exists())
			FileUtils.forceDelete(logDirectory);

		FileUtils.forceMkdir(logDirectory);
		FileUtils.forceMkdir(screenshotsDirec);

		System.out.println("Initializing TessEract library");
/*
		try{
			if (tessBaseApi.Init("/opt/local/share", "eng") != 0) {
				System.err.println("Could not initialize tesseract.");
			}
		}catch(Exception e){
			System.out.println("Setup TessEract in the Test Machine before initializing TessEract Library for OCR");
		}*/

	}

	@Parameters({ "deviceId", "port", "bootStrapPort" })
	@BeforeTest
	public void beforeTest(String deviceId, String port, String bootStrapPort)
			throws IOException, InterruptedException, URISyntaxException {

		appiumManager = new AppiumServerManager(port, bootStrapPort);

		this.device = new Device(deviceId);
		this.port = port;	
		
		System.out.println("Starting appium Server for device: " + device + " on port " + port);
	
		appiumManager.startAppium();	
		
		configureLogger().info("Started appium Server for device: " + device + " on port " + port);
		

	}
	
	@AfterTest
	public void afterTest(){

		System.out.println("Stopping Appium Server at port: " + port);
		appiumManager.killServer();

	}

	@Parameters({ "deviceId", "port", "bootStrapPort" })
	@BeforeClass
	public void setUpDeviceConfiguration(String deviceId, String port, String bootStrapPort)
			throws IOException, InterruptedException {
		this.device = new Device(deviceId);
		this.port = port;

		this.logger = loggers.get(deviceId);
		logger.info("Test cases in the class " + this.getClass().getName() + " started");
		
	}
	
	
	@BeforeMethod
	public void printTestName(Method method) throws IOException {
		
		logger.info("-----------------------------------------------------");
		logger.info("-------------------"+method.getName() +" Start-------------------");
		logger.info("-----------------------------------------------------\n\n");
		

	}
	
	
	@AfterMethod
	public void takeScreenshotOnFailure(ITestResult test) throws IOException {
		switch (test.getStatus()) {

		case ITestResult.SUCCESS:
			logger.info(test.getName() + " Passed");
			logger.info("-----------------------------------------------------");
			logger.info("-------------------Test Case Passed----------------------");
			logger.info("-----------------------------------------------------\n\n");
			break;
		case ITestResult.FAILURE:
			logger.info(test.getName() + " Failed");
			logger.info("Taking screenshot");
			takeScreenshot(test.getName());
			logger.info("-----------------------------------------------------");
			logger.info("-------------------Test Case Failed----------------------");
			logger.info("-----------------------------------------------------\n\n");
		}

	}

	public File takeScreenshot(String testName) throws IOException {
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot, new File(config.getProperty("screenshotsDirectory") + testName + device.deviceID() + ".png"));
		return screenshot;
	}

	public synchronized boolean verifyToastMessage(String msg)
			throws IOException {
		TakesScreenshot takeScreenshot = ((TakesScreenshot) driver);

		File[] screenshots = new File[3];

		for (int i = 0; i < screenshots.length; i++) {
			screenshots[i] = takeScreenshot.getScreenshotAs(OutputType.FILE);
		}

		String outText;

		for (int i = 0; i < screenshots.length; i++) {
			/*PIX image = pixRead(screenshots[i].getAbsolutePath());
			tessBaseApi.SetImage(image);
			outText = tessBaseApi.GetUTF8Text().getString().replaceAll("\\s", "");
			logger.info(outText);
			isMsgContains = outText.contains(msg);
			pixDestroy(image);*/
			
			outText = getImgText(screenshots[i].getAbsolutePath());
			if (outText.contains(msg)) {
				return true;
			}
		}

		return false;

	}
	
	public String getImgText(String imageLocation) {
	      ITesseract instance = new Tesseract();
	      try 
	      {
	         String imgText = instance.doOCR(new File(imageLocation));
	         return imgText;
	      } 
	      catch (TesseractException e) 
	      {
	         e.getMessage();
	         return "Error while reading image";
	      }
	   }

//	@AfterSuite()
//	public void afterSuite() {
//
//		try {
//			tessBaseApi.close();
//		} catch (Exception e) {
//			tessBaseApi.End();
//			e.printStackTrace();
//		}
//		
//	}

	private Logger configureLogger() {
		LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
		final Configuration ctxConfig = ctx.getConfiguration();

		Layout<String> layout = PatternLayout.newBuilder().withPattern("%d [%t] %-5level: %msg%n")
				.withConfiguration(ctxConfig).withAlwaysWriteExceptions(true).withNoConsoleNoAnsi(false).build();

		Appender appender = ((Builder<?>) new FileAppender.Builder<>()
				.withAppend(false).withBufferedIo(true).withBufferSize(4000).setConfiguration(ctxConfig)
				.withFileName(config.getProperty("testLogsDirectory") + device.name() + "_" +device.deviceID()+ "_"  + port + ".log")
				.withIgnoreExceptions(true).withImmediateFlush(true).withLayout(layout).withLocking(false)
				.withName(device.deviceID())).build();

		appender.start();
		ctxConfig.addAppender(appender);
		AppenderRef ref = AppenderRef.createAppenderRef(device.deviceID(), null, null);
		AppenderRef[] refs = new AppenderRef[] { ref };

		LoggerConfig loggerConfig = LoggerConfig.createLogger(false, Level.ALL, device.deviceID(), null, refs, null,
				ctxConfig, null);

		loggerConfig.addAppender(appender, null, null);
		ctxConfig.addLogger(device.deviceID(), loggerConfig);
		ctx.updateLoggers(ctxConfig);
		Logger logger = ctx.getLogger(device.deviceID());
		loggers.put(device.deviceID(), logger);
		return logger;
	}
}
