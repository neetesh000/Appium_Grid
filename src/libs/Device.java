package libs;

public class  Device{
	
	private String model;
	private String brand;
	private String osVersion;
	private String deviceID;
	private String name;
	
	public Device(String model, String brand, String osVersion, String deviceID) {
		super();
		this.model = model;
		this.brand = brand;
		this.osVersion = osVersion;
		this.deviceID = deviceID;
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
		return deviceID;
	}

	public String name() {
		return name;
	}
	

}
