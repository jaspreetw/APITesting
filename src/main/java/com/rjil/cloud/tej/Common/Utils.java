package com.rjil.cloud.tej.Common;

import com.rjil.cloud.tej.Common.logging.FrameworkLogger;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Utils Class for util methods
 */
public class Utils {
    /**
     * Method to get Property from file
     *
     * @param file Property file
     * @param key  Property value
     * @return: Property value
     */
    public static String getProperty(String file, String key) {

        try {
            file = System.getProperty("user.dir") + "/resources/" + file;
            File config = new File(file);
            FileInputStream input;
            input = new FileInputStream(config.getAbsolutePath());
            Properties prop = new Properties();
            prop.load(input);
            String value = prop.getProperty(key);
            input.close();
            return value;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method to get Properties Map
     *
     * @param server Execution Server
     * @return Property Map
     */
    public static Map<String, String> getPropertyMAP(String server) {

        try {
            String path = System.getProperty("user.dir") + "/resources/serverURL/" + server + "/config.properties";
            File config = new File(path);
            FileInputStream input;
            input = new FileInputStream(config.getAbsolutePath());
            Properties prop = new Properties();
            prop.load(input);
            Map<String, String> properties = new HashMap<>();
            for (Object key : prop.keySet()) {
                properties.put(key.toString(), prop.getProperty(key.toString()));
            }
            input.close();
            return properties;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method to get Properties Map from property file
     *
     * @return : Property Map
     */
    public static Map<String, String> getServerConfigMAP() {

        try {
            String path = System.getProperty("user.dir") + "/resources/ProjectConfig.properties";
            File config = new File(path);
            FileInputStream input;
            input = new FileInputStream(config.getAbsolutePath());
            Properties prop = new Properties();
            prop.load(input);
            Map<String, String> properties = new HashMap<>();
            for (Object key : prop.keySet()) {
                properties.put(key.toString(), prop.getProperty(key.toString()));
            }
            input.close();
            return properties;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Method to add request Response to Log file
     */
    public static void addRequestResponseToLogger() {
        FrameworkLogger.logStep("Request: \n" + getRestAssuredRequestResponse().toString());
        cleanFile();
    }

    /**
     * Method to get Restassured Request Response from file
     *
     * @return file data in string
     */
    public static StringBuilder getRestAssuredRequestResponse() {

        BufferedReader br = null;
        StringBuilder allString = new StringBuilder();
        try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader("./tmp.txt"));

            while ((sCurrentLine = br.readLine()) != null) {
                allString.append(sCurrentLine);
                allString.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return allString;

    }

    /**
     * Method to clear temporary log file
     */
    public static void cleanFile() {
        try {
            PrintWriter writer = new PrintWriter("./tmp.txt");
            writer.print("");
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

