package libs;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public abstract class BaseTest implements Runnable{
	protected AndroidDriver<AndroidElement> driver;
	protected BaseTest[] deviceThreads;
	protected int numOfDevices;
	protected String deviceId;
	protected String deviceName;
	protected String osVersion;
	protected String port;
	protected Thread t;
	protected int deviceCount;
	
	protected AppiumManager appiumMan = new AppiumManager();
	static List<Device> devices = new ArrayList<>();
	static DeviceConfiguration deviceConf = new DeviceConfiguration();

	public BaseTest(){
		try {
			devices = deviceConf.getDivces();
			deviceCount = devices.size();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public BaseTest(int i){
		this.deviceId = devices.get(i).deviceID();
		this.deviceName = devices.get(i).name();
		this.osVersion = devices.get(i).osVersion();
	}
	
	public void createDriver(){
		try	{
			port = appiumMan.startAppium(); 			// Start appium server			  
			  
			// create appium driver instance
			DesiredCapabilities capabilities = DesiredCapabilities.android();
			capabilities.setCapability("deviceName", deviceName);
			capabilities.setCapability("platformName", "android");
			capabilities.setCapability(CapabilityType.VERSION, osVersion);
			capabilities.setCapability(CapabilityType.BROWSER_NAME, "chrome");
			capabilities.setCapability("udid", deviceId);
				
			this.driver = new AndroidDriver<>(new URL("http://127.0.0.1:"+port+"/wd/hub"),capabilities);
		}
		catch(Exception e){
	    	e.printStackTrace();
	    }
	}
	
	public AppiumManager createDriver(String appPath){
		try	{
			
			AppiumManager appiumMan = new AppiumManager();
			port = appiumMan.startAppium(); 			// Start appium server			  
			  
			// create appium driver instance
			DesiredCapabilities capabilities = DesiredCapabilities.android();
			capabilities.setCapability("deviceName", deviceName);
			capabilities.setCapability("platformName", "android");
			capabilities.setCapability(CapabilityType.VERSION, osVersion);
			capabilities.setCapability("app", appPath);
			capabilities.setCapability("udid", deviceId);
				
			this.driver = new AndroidDriver<>(new URL("http://127.0.0.1:"+port+"/wd/hub"),capabilities);
		}
		catch(Exception e){
	    	e.printStackTrace();
	    }
		
		return appiumMan;
	}
	
	public void destroyDriver()
	{
		driver.quit();
	}
	public void start(){
		if (t == null){
		  t = new Thread(this);
		  t.start ();
		}
	}

	public abstract void run();
	
	public void execute()
	{
		Class<?> c;
		try {
			int startMethod = 0;
			String className = this.getClass().toString();
			System.out.println("class : "+className);
			className = className.replace("class ", "");
			System.out.println("class : "+className);
			// Get extended class name
			c = Class.forName(className);
			System.out.println("class : "+c);
			
			// Get start method
			Method[] m = c.getMethods();
			System.out.println("methods: "+m.length);
			for(int i=0;i<m.length;i++)	{
				//System.out.println("methods: "+m[i]);
				if(m[i].toString().contains("start")){
					startMethod=i;
					break;
				}
			}
			System.out.println("methods: "+m[startMethod]);
			// get constructor
			Constructor<?> cons = c.getConstructor(Integer.TYPE);
			System.out.println("cons: "+cons);
			
			System.out.println("deviceCount: "+deviceCount);
			// Create array of objects
			Object obj =  Array.newInstance(c, deviceCount);
			for (int i = 0; i < deviceCount; i++) {
                Object val = cons.newInstance(i);
                Array.set(obj, i, val);
                System.out.println("Objkect: " + obj.getClass());
            }

			for (int i = 0; i < deviceCount; i++) {
                Object val = Array.get(obj, i);
                m[startMethod].invoke(val);
            }
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
