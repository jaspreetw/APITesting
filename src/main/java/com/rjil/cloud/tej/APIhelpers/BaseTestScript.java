package com.rjil.cloud.tej.APIhelpers;


import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.LogConfig;
import com.jayway.restassured.filter.log.LogDetail;
import com.jayway.restassured.response.ValidatableResponse;
import com.rjil.cloud.tej.Common.CustomListener;
import com.rjil.cloud.tej.Common.Utils;
import com.rjil.cloud.tej.Common.datadriven.model.DataContainer;
import com.rjil.cloud.tej.Common.datadriven.model.TestDataRecord;
import com.rjil.cloud.tej.Common.datadriven.reader.*;
import com.rjil.cloud.tej.Common.logging.FrameworkLogger;
import groovy.util.logging.Log4j;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import static com.jayway.restassured.config.RestAssuredConfig.config;

public class BaseTestScript {
    public static String executionServer;
    public static boolean isIdam;
    protected DataContainer dataContainer;
    public static Map<String, String> apiUrls;
    public static Map<String, String> serverConfig;
    public static String testName;
    public static String accessToken;
    public static String userId;

    /**
     * Base Test script Constructor
     */
    public BaseTestScript() {
        serverConfig = Utils.getServerConfigMAP();
        executionServer = serverConfig != null ? serverConfig.get("environment") : null;
        isIdam = Boolean.parseBoolean(serverConfig != null ? serverConfig.get("isIDAM") : null);
        apiUrls = Utils.getPropertyMAP(executionServer);
        initializeLogger();
    }

    /**
     * Method to initialize Restassured logger
     */
    public void initializeLogger() {
        try {
            LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL);
            FileOutputStream fo = new FileOutputStream(new File("./tmp.txt"));
            PrintStream defaultPrintStream = new PrintStream(fo);
            RestAssured.config = config().logConfig(new LogConfig(defaultPrintStream, true));
            FrameworkLogger.config(getClass());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @BeforeSuite
    /**
     * Precondition manager for any precondition. this method can be override in Feature Base Script
     */
    public void preConditionManager() {
        if (!(this instanceof loginBaseScript)) {
            defaultLogin();
        }
    }

    /**
     * Method to default login
     */
    public void defaultLogin() {
        ValidatableResponse response = loginBaseScript.getLoginResponse();
        accessToken = loginBaseScript.getAccessToken(response);
        userId = loginBaseScript.getUserId(response);
    }
    /**
     * Method to select dataType
     *
     * @param dataType : Type of data (Excel, Json, XML, CSV)
     * @return : Test Data
     */
    private TestDataReader getTestDataReaderByDataType(DataType dataType) {
        System.out.println("******Start**********getTestDataReaderByDataType***********");

        switch (dataType) {
            case JSON:
                return new JSONDataReader();
            case XML:
                return new XMLDataReader();
            case EXCEL:
                return new ExcelDataReader();
            case CSV:
                return new CSVDataReader();
        }
        return null;
    }

    /**
     * Data type enum
     */
    protected enum DataType {
        EXCEL,
        JSON,
        XML,
        CSV,
        EMPTY
    }

    /**
     * Method to get test data for data driven test
     * @param path Test data ile path
     * @param dataType data type
     * @return Test data record list in Array
     * @throws ParseException
     */
    public Object[][] getTesData(String path, DataType dataType) throws ParseException {
        String userDir = System.getProperty("user.dir");
        path = userDir + "/src/main/resources/" + path;
        TestDataReader dataReader = getTestDataReaderByDataType(dataType);
        dataContainer = dataReader != null ? dataReader.readTestData(path) : null;
        List<TestDataRecord> testDataList = dataContainer != null ? dataContainer.getTestDataList() : null;
        Object[][] data = new Object[testDataList.size()][1];
        if (testDataList != null) {
            for (int i = 0; i < testDataList.size(); i++) {
                TestDataRecord testDataRecord = testDataList.get(i);
                data[i] = new Object[1];
                data[i][0] = testDataRecord;
            }
        }
        return data;
    }

    /**
     * Get default test data form JSON
     * @param path JSON file Path
     * @return default test data
     * @throws ParseException
     */
    public TestDataRecord getDefaultLoginData(String path) throws ParseException {
        String userDir = System.getProperty("user.dir");
        path = userDir + "/src/main/resources/" + path;
        TestDataReader dataReader = getTestDataReaderByDataType(DataType.JSON);
        dataContainer = dataReader != null ? dataReader.readTestData(path) : null;
        List<TestDataRecord> testDataList = dataContainer != null ? dataContainer.getTestDataList() : null;
        return testDataList.get(0);
    }


}
