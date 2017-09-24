package appium.manager;

import java.io.IOException;

public class  Device{
	
	final private String model;
	final private String brand;
	final private String osVersion;
	final private String deviceId;
	final private String name;
	
	public Device(String model, String brand, String osVersion, String deviceID) {
		super();
		this.model = model;
		this.brand = brand;
		this.osVersion = osVersion;
		this.deviceId = deviceID;
		this.name = brand+" "+model;
	}
	
	public Device(String deviceId) throws IOException, InterruptedException {
		CommandPrompt cmt = new CommandPrompt();
		this.model = cmt.runCommandAndGetProcessOutput("adb -s "+deviceId+" shell getprop ro.product.model").replaceAll("\\s+", "");
		this.brand = cmt.runCommandAndGetProcessOutput("adb -s "+deviceId+" shell getprop ro.product.brand").replaceAll("\\s+", "");
		this.osVersion = cmt.runCommandAndGetProcessOutput("adb -s "+deviceId+" shell getprop ro.build.version.release").replaceAll("\\s+", "");
		this.deviceId = deviceId;
		this.name = brand+" "+model;
	}

	public String model() {
		return model;
	}

	public String brand() {
		return brand;
	}

	public String osVersion() {
		return osVersion;
	}

	public String deviceID() {
		return deviceId;
	}

	public String name() {
		return name;
	}

	@Override
	public String toString() {
		return "Device [model=" + model + ", brand=" + brand + ", osVersion=" + osVersion + ", deviceId=" + deviceId
				+ ", name=" + name + "]";
	}
	
	
	

}
