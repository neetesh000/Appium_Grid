package appium.manager;

import java.io.File;
import java.io.IOException;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.AndroidServerFlag;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class AppiumServerManager implements Runnable {

	protected CommandPrompt cp = new CommandPrompt();
	protected String port;
	private String command;
	public static final String LOGS_DIRECTORY = "target/appiumLogs";

	Process appiumProcess;
	private AppiumDriverLocalService appiumService;
	private String bootStrapPort;

	
	public AppiumServerManager(String port, String bootStrapPort) {
		this.port = port;
		this.bootStrapPort = bootStrapPort;
	}

	public void startAppium() throws IOException {
		// start appium server

		File logFile = new File(LOGS_DIRECTORY + "/appium_port_" + port + ".log");
		
		logFile.createNewFile();
		
		AppiumServiceBuilder appiumBuilder = new AppiumServiceBuilder();
		appiumBuilder.usingPort(new Integer(port));
		
		appiumBuilder.withLogFile(logFile);
		appiumBuilder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
		appiumBuilder.withArgument(AndroidServerFlag.BOOTSTRAP_PORT_NUMBER, bootStrapPort+"");
		appiumService = appiumBuilder.build();
		appiumService.start();
	}

	@Override
	public void run() {
		try {
			appiumProcess = cp.runCommand(command);
	
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}

	}

	public void killServer() {
		if(appiumService.isRunning()){
			appiumService.stop();
		}

	}

}
