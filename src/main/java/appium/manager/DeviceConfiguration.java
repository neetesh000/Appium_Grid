package appium.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeviceConfiguration {

	CommandPrompt cmd = new CommandPrompt();
	List<Device> devices = new ArrayList<>();
	
	public void startADB() throws InterruptedException, IOException {
		Process p = cmd.runCommand("adb start-server");
		String output = cmd.getProcessOutput(p);
		String[] lines = output.split("\n");
		if(lines.length==1)
			System.out.println("adb service already started");
		else if(lines[1].equalsIgnoreCase("* daemon started successfully *"))
			System.out.println("adb service started");
		else if(lines[0].contains("internal or external command")){
			System.out.println("adb path not set in system varibale");
			throw new RuntimeException("adb path not set in system varibale");
		}
	}
	
	public void stopADB() throws InterruptedException, IOException {
		cmd.runCommand("adb kill-server");
	}
	
	public List<Device> getDivces() throws IOException, InterruptedException{
		
		startADB(); // start adb service
		String output = cmd.getProcessOutput(cmd.runCommand("adb devices"));
		String[] lines = output.split("\n");

		if(lines.length<=1){
			System.out.println("No Device Connected");
			stopADB();
			throw new RuntimeException("No device is connected");
		}
		
		for(int i=1;i<lines.length;i++){
			lines[i]=lines[i].replaceAll("\\s+", "");
			
			if(lines[i].contains("device")){
				lines[i]=lines[i].replaceAll("device", "");
				String deviceID = lines[i];
				String model =cmd.runCommandAndGetProcessOutput("adb -s "+deviceID+" shell getprop ro.product.model").replaceAll("\\s+", "");
				String brand = cmd.runCommandAndGetProcessOutput("adb -s "+deviceID+" shell getprop ro.product.brand").replaceAll("\\s+", "");
				String osVersion = cmd.runCommandAndGetProcessOutput("adb -s "+deviceID+" shell getprop ro.build.version.release").replaceAll("\\s+", "");
				
				Device device = new Device(model, brand, osVersion, deviceID);
				
				devices.add(device);
				
				System.out.println("Following device is connected");
				System.out.println(deviceID+" "+device.name()+" "+osVersion+"\n");
			}else if(lines[i].contains("unauthorized")){
				lines[i]=lines[i].replaceAll("unauthorized", "");
				String deviceID = lines[i];
				
				System.out.println("Following device is unauthorized");
				System.out.println(deviceID+"\n");
			}else if(lines[i].contains("offline")){
				lines[i]=lines[i].replaceAll("offline", "");
				String deviceID = lines[i];
				
				System.out.println("Following device is offline");
				System.out.println(deviceID+"\n");
			}
		}
		return devices;
	}
	
}
