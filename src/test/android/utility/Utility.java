package test.android.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class Utility {

	public static String readFileAsString(String filePath) throws IOException {

		return FileUtils.readFileToString(new File(filePath), "UTF-8");
	}

	public static Map<String, Object> readJsonFileAsMap(String filePath) throws JSONException, IOException {
		JSONObject jsonObject = new JSONObject(readFileAsString(filePath));
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();

		Iterator<String> iter = jsonObject.keys();
		while (iter.hasNext()) {
			String key = iter.next();
			jsonMap.put(key, jsonObject.get(key));
		}
		return jsonMap;
	}

	public static JSONObject readJsonFile(String filePath) throws JSONException, IOException {
		return new JSONObject(readFileAsString(filePath));
	}
	
	public static Properties getConfig() throws FileNotFoundException, IOException{
		Properties prop = new Properties();
		prop.load(new FileInputStream("resources/config.properties"));
		return prop;
	}

/*	public static String getPort() throws IOException {
		ServerSocket socket = new ServerSocket(0);
		socket.setReuseAddress(true);
		String port = Integer.toString(socket.getLocalPort());
		socket.close();
		return port;
	}*/

}
