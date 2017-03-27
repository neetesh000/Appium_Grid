package libs;

import java.io.File;
import java.io.IOException;

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
	File logFile;
	
	private AppiumDriverLocalService appiumService;

	/**
	 * start appium with auto generated ports : appium port, chrome port, and
	 * bootstap port
	 * @throws IOException 
	 */
	public String startAppium() throws IOException {
		// start appium server
		port = ap.getPort();
		chromePort = ap.getPort();
		bootstrapPort = ap.getPort();
		
		logFile = new File("appium_port_" + port + ".log");
		
		logFile.createNewFile();

		File logFile = new File("appium_port_" + port + ".log");
		
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
		System.out.println();
		if(appiumService.isRunning()){
			appiumService.stop();
		}
		
	}

}
