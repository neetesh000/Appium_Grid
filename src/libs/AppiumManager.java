package libs;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.AndroidServerFlag;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

/**
 * Appium Manager - this class contains method to start and stops appium server
 */
public class AppiumManager {

	CommandPrompt cp = new CommandPrompt();
	AvailabelPorts ap = new AvailabelPorts();

	String port;
	String chromePort;
	String bootstrapPort;
	
	AppiumDriverLocalService appiumService;

	/**
	 * start appium with auto generated ports : appium port, chrome port, and
	 * bootstap port
	 * @throws IOException 
	 */
	
	static {
		File appiumLogsDirectrory = new File("appium_logs");
		
		if(appiumLogsDirectrory.exists()){
			try {
				FileUtils.forceDelete(appiumLogsDirectrory);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		appiumLogsDirectrory.mkdir();
	}
	
	public String startAppium() throws IOException {
		// start appium server
		port = ap.getPort();
		chromePort = ap.getPort();
		bootstrapPort = ap.getPort();

		File logFile = new File("appium_logs/appium_port_" + port + ".log");
		
		logFile.createNewFile();

		AppiumServiceBuilder appiumBuilder = new AppiumServiceBuilder();
		

		appiumBuilder.withLogFile(logFile);
		appiumBuilder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
		
		System.out.println("port:" +Integer.parseInt(port) );
		
		appiumBuilder.usingPort(Integer.parseInt(port));
		
		appiumBuilder.withArgument(AndroidServerFlag.BOOTSTRAP_PORT_NUMBER, bootstrapPort);
		appiumBuilder.withArgument(AndroidServerFlag.CHROME_DRIVER_PORT, chromePort);

		appiumService = appiumBuilder.build();
		appiumService.start();
		return port;
	}

	public void killServer() {
		if(appiumService!=null && appiumService.isRunning()){
			appiumService.stop();
		}
		
	}

}
