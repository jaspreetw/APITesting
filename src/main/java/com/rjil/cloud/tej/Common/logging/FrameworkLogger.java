/*
* Copyright Medtronic, Inc. 2013
*
* MEDTRONIC CONFIDENTIAL - This document is the property of Medtronic,
* Inc.,and must be accounted for. Information herein is confidential. Do
* not reproduce it, reveal it to unauthorized persons, or send it outside
* Medtronic without proper authorization.
*/

package com.rjil.cloud.tej.Common.logging;


import com.rjil.cloud.tej.APIHelpers.BaseTestScript;
import com.rjil.cloud.tej.Common.Utils;
import com.rjil.cloud.tej.Common.logging.level.FailLevel;
import com.rjil.cloud.tej.Common.logging.level.PassLevel;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Main class for performing logging in automation test framework
 * Allows configuration of logger based on given project properties
 * Provides ability to add log messages of different levels
 */
public class FrameworkLogger {

    private static final Logger LOGGER = Logger.getLogger(FrameworkLogger.class);

    public static final String LOG_FILE_EXTENSION = ".log";
    public static final String LOG_FILE_NAME = "file";
    public static final String PROJECT_CONFIG_PROPERTIES = "ProjectConfig.properties";
    public static String currentDate;
    private static String currentTestClassName;

    public static void logStep(final String message) {
        LOGGER.info(message);
    }

    //This method should be used when any unexpected  behaviour is taken a place
    public static void logError(final String errorMessage) {
        logError(errorMessage);
    }

    //This method should be used when any exception is taken a place
    public static void logError(final Exception exception) {
        if (exception != null) {
            String message = exception.getMessage();
            LOGGER.error(message != null ? message : "", exception);
        }
    }

    //This method is used when verification statement fails
    public static void logFail(final String failMessage) {
        LOGGER.log(FailLevel.FAIL, failMessage);
    }

    public static void logWarning(final String warningMessage) {
        LOGGER.warn(warningMessage);
    }

    public static void logDebug(final String debugMessage) {
        LOGGER.debug(debugMessage);
    }

    //This method is used when verification is pass
    public static void logPass(final String verifyMessage) {
        LOGGER.log(PassLevel.PASS, verifyMessage);
    }


    public static void config(final Class<? extends BaseTestScript> testClass) {
        currentTestClassName = testClass.getSimpleName();
        currentDate = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new java.util.Date());
        //Create map with al header data
        Map<String, String> headerDataMap = createHeaderData();
        //Create log file name

        //Configure file appender
        Logger.getRootLogger().addAppender(createFileAppender(headerDataMap,
                Utils.getProperty(PROJECT_CONFIG_PROPERTIES, "LogPath")
                        + File.separator + testClass.getSimpleName() + "_" + currentDate));

    }


    private static FileAppender createFileAppender(Map<String, String> headerDataMap, String logFileName) {
        FileAppender fileAppender = new FileAppender();
        fileAppender.setName(LOG_FILE_NAME);
        fileAppender.setFile(logFileName + LOG_FILE_EXTENSION);
        fileAppender.setLayout(new CustomPatternLayout("%d{yyyy-MM-dd HH:mm:ss} %-4p - %m%n", headerDataMap));
        fileAppender.setAppend(false);
        fileAppender.activateOptions();
        return fileAppender;
    }

    private static Map<String, String> createHeaderData() {

        Map<String, String> headerDataMap = new HashMap<>();

        headerDataMap.put("TestClassName", currentTestClassName);
        headerDataMap.put("Execution Server", BaseTestScript.executionServer);
        if (BaseTestScript.isIdam) {
            headerDataMap.put("LoginType", "IDAM");
        } else {
            headerDataMap.put("LoginType", "Non-IDAM");
        }
        headerDataMap.put("Execution Date", currentDate);

        return headerDataMap;
    }

}
