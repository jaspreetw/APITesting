package com.rjil.cloud.tej.apihelpers;


import com.jayway.jsonpath.JsonPath;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.LogConfig;
import com.jayway.restassured.filter.log.LogDetail;
import com.jayway.restassured.response.ValidatableResponse;
import com.rjil.cloud.tej.apiconstants.LoginConstants;
import com.rjil.cloud.tej.common.Base64;
import com.rjil.cloud.tej.common.Utils;
import com.rjil.cloud.tej.common.datadriven.model.DataContainer;
import com.rjil.cloud.tej.common.datadriven.model.TestDataRecord;
import com.rjil.cloud.tej.common.datadriven.reader.*;
import com.rjil.cloud.tej.common.logging.FrameworkLogger;
import com.rjil.cloud.tej.enums.DataType;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.config.RestAssuredConfig.config;

public class BaseTestScript {
    public static String executionServer;
    public static boolean isIdam;
    protected DataContainer dataContainer;
    public static Map<String, String> apiUrls;
    public static Map<String, String> serverConfig;
    public static String accessToken;
    public static String userId;
    public static StringBuffer authorizationType;
    protected static String loginJOSNBody = "";
    protected static String loginURL = "";
    protected static LoginConstants loginConstants;

    /**
     * Base Test script Constructor
     */
    public BaseTestScript() {
        serverConfig = Utils.getServerConfigMAP();
        executionServer = serverConfig != null ? serverConfig.get("environment") : null;
        isIdam = Boolean.parseBoolean(serverConfig != null ? serverConfig.get("isIDAM") : null);
        apiUrls = Utils.getPropertyMAP(executionServer);
        initializeLogger();
        loginConstants = new LoginConstants();
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
    public void preConditionManager() throws IOException {
        if (!(this instanceof LoginBaseScript)) {
            defaultLogin();
        }
    }

    /**
     * Method to default login
     */
    public void defaultLogin() throws IOException {
        setIdamJsonBody();
        ValidatableResponse response = given().body(loginJOSNBody).header("Content-Type", "application/json").log().all()
                .when()
                .post(loginURL)
                .then();
        response.log().all();
        Utils.addRequestResponseToLogger();
        accessToken = getAccessToken(response);
        userId = getUserId(response);
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
     * Method to get test data for data driven test
     *
     * @param path:     Test data ile path
     * @param dataType: data type
     * @return Test data record list in Array
     * @throws ParseException
     */
    public Object[][] getTesData(String path, DataType dataType) throws ParseException {
        String userDir = System.getProperty("user.dir");
        path = userDir + "/resources/" + path;
        TestDataReader dataReader = getTestDataReaderByDataType(dataType);
        dataContainer = dataReader != null ? dataReader.readTestData(path) : null;
        List<TestDataRecord> testDataList = dataContainer != null ? dataContainer.getTestDataList() : null;
        Object[][] data = new Object[0][];
        if (testDataList != null) {
            data = new Object[testDataList.size()][1];
            for (int i = 0; i < testDataList.size(); i++) {
                TestDataRecord testDataRecord = testDataList.get(i);
                data[i] = new Object[1];
                data[i][0] = testDataRecord.getValueByIndex(0).getValue();
            }
        }
        return data;
    }

    /**
     * update Json for username, password and device key. also update json for IDAM and non IDAM accounts.
     */
    public static void setIdamJsonBody() throws IOException {
        setUserData();
        updateJsonIDAM();
        getLoginUrl();

    }

    /**
     * Method to get login URL IDAM/NON IDAM
     */
    public static void getLoginUrl() {
        //set idam url
        if (!isIdam) {
            loginURL = apiUrls.get("loginURL");
        } else {
            loginURL = apiUrls.get("IDAMLoginURL");
        }
    }

    /**
     * Update login json body for IDAM login
     */
    public static void updateJsonIDAM() {
        //change Email Id field to Login ID and remove Auto provider ID for Idam account
        if (isIdam) {
            loginJOSNBody = JsonPath.parse(loginJOSNBody).renameKey("@", "emailId", "loginId").jsonString();
            loginJOSNBody = JsonPath.parse(loginJOSNBody).delete(loginConstants.getAuthProviderId()).jsonString();
        }
    }

    /**
     * Set user specific data in login json body
     * @throws IOException
     */
    public static void setUserData() throws IOException {
        //load json file
        String path = System.getProperty("user.dir") + "/resources/loginTestData/loginBody.js";
        File file = new File(path);

        loginJOSNBody = JsonPath.parse(file).jsonString();
        //Set Email Address, Password and Device Key form Property file
        loginJOSNBody = setLoginData(loginConstants.getEmailId(), serverConfig.get("Email"), loginJOSNBody);
        loginJOSNBody = setLoginData(loginConstants.getPassword(), serverConfig.get("Password"), loginJOSNBody);
        loginJOSNBody = setLoginData(loginConstants.getDeviceKey(), serverConfig.get("deviceKey"), loginJOSNBody);
    }

    /**
     * Method to update Login JSON Body
     */
    public static String setLoginData(String path, Object value, String jsonString) {
        return JsonPath.parse(jsonString).set(path, value).jsonString();
    }

    /**
     * Method to get Access Token
     *
     * @return Access Token
     */
    public static String getAccessToken(ValidatableResponse response) {
        String accessToken = response.extract().path("authToken.accessToken");
        authorizationType= new StringBuffer("Baisc");
        accessToken =authorizationType.append(authorizationType. Base64.b64encode(accessToken));
        return accessToken;
    }

    /**
     * Method to get User Id
     *
     * @return UserId
     */
    public static String getUserId(ValidatableResponse response) {
        return response.extract().path("userId");
    }

}
