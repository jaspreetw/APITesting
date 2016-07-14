package com.rjil.cloud.tej.Utility;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadPropertyFile {

	/**
	 * This method returns value for the key to be passed as input.
	 * @param key - name of the parameter, whose value need to be retrieve 
	 * @return - returns value of the parameter
	 * @throws IOException
	 */
	public String getProperty(String key) throws IOException {
		File config = new File("config.properties");
		String baseconfig = config.getAbsolutePath();
		
		String filePath = "src/main/resources/config.properties";
		filePath.replace("/", File.separator);
		FileInputStream input = new FileInputStream(filePath);
	
		Properties prop = new Properties();
		prop.load(input);
		String value = prop.getProperty(key);
		return value;
	}


}
