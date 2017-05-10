package test.android.utility;
import java.io.BufferedReader;
/**
 * Command Prompt - this class contains method to run windows and mac commands  
 */
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandPrompt {
	
	
	
	/**
	 * This method run command on windows and mac
	 * @param command to run  
	 */
	public Process runCommand(String command) throws InterruptedException, IOException {
		Process p;
		ProcessBuilder builder;
		
		String os = System.getProperty("os.name");
		//System.out.println(os);
		
		// build cmd proccess according to os
		if(os.contains("Windows")) // if windows
		{
			builder = new ProcessBuilder("cmd.exe","/c", command);
			builder.redirectErrorStream(true);
			Thread.sleep(1000);
			p = builder.start();
		}
		else { // If Mac
			p = Runtime.getRuntime().exec(command);
			
		}

		return p;

	}
	
	public String getProcessOutput(Process p) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line="";
		String allLine="";
		while((line=r.readLine()) != null){
			allLine=allLine+""+line+"\n";
			if(line.contains("Console LogLevel: debug"))
				break;
		}
		
		return allLine;
	}
	
	public String runCommandAndGetProcessOutput(String command) throws IOException, InterruptedException {
		return getProcessOutput(runCommand(command));
	}
	
}
