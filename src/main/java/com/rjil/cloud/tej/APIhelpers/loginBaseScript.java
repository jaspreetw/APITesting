package com.rjil.cloud.tej.APIhelpers;

import com.jayway.restassured.response.ValidatableResponse;
import com.rjil.cloud.tej.APIModels.BaseAPIModel;
import com.rjil.cloud.tej.APIModels.DeviceInfoModel;
import com.rjil.cloud.tej.APIModels.LoginModel;
import com.rjil.cloud.tej.Common.Base64;
import com.rjil.cloud.tej.Common.Utils;
import com.rjil.cloud.tej.Common.datadriven.model.TestDataRecord;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.text.ParseException;

import static com.jayway.restassured.RestAssured.given;

public class loginBaseScript extends BaseTestScript {
    protected static LoginModel loginModel;
    protected static DeviceInfoModel deviceInfoModel;
    protected BaseAPIModel baseAPIModel;
    protected static String loginJOSNBody = "";
    protected static String loginURL = "";

    /**
     * Constructor to initialize Models and Login URLs
     */
    public loginBaseScript() {
        initModels();
        setDefaultLoginBody();
        loginJOSNBody = loginModel.getGsonFromObject();
        if (!isIdam) {
            loginURL = apiUrls.get("loginURL");
        } else {
            loginURL = apiUrls.get("IDAMLoginURL");
        }
    }

    /**
     * Method to initialize Models
     */
    private void initModels() {
        loginModel = new LoginModel();
        deviceInfoModel = new DeviceInfoModel();
        baseAPIModel = new BaseAPIModel();
    }


    /**
     * Method to set Login data from file
     *
     * @param record data record
     */
    public void setLoginBody(TestDataRecord record) {
        loginModel.setAuthProviderId(record.getStringValueByName(loginParameters.AUTHPROVIDERID.getValue()));
        if (isIdam) {
            loginModel.setEmailId(record.getStringValueByName(loginParameters.LOGINID.getValue()));
        } else {
            loginModel.setEmailId(record.getStringValueByName(loginParameters.EMAILID.getValue()));
        }

        loginModel.setPassword(record.getStringValueByName(loginParameters.PASSWORD.getValue()));
        loginModel.setDeviceType(record.getStringValueByName(loginParameters.DEVICETYPE.getValue()));
        deviceInfoModel.setDeviceName(record.getStringValueByName(loginParameters.DEVICENAME.getValue()));
        deviceInfoModel.setDeviceKey(record.getStringValueByName(loginParameters.DEVICEKEY.getValue()));
        deviceInfoModel.setRegistrationId(record.getStringValueByName(loginParameters.REGISTRATIONID.getValue()));
        deviceInfoModel.setImei(record.getStringValueByName(loginParameters.IMEI.getValue()));
        deviceInfoModel.setSerial(record.getStringValueByName(loginParameters.SERIAL.getValue()));
        deviceInfoModel.setPlatformType(record.getStringValueByName(loginParameters.PLATFORMTYPE.getValue()));
        deviceInfoModel.setPlatformVersion(record.getStringValueByName(loginParameters.PLATFORMVERSION.getValue()));
        deviceInfoModel.setApiLevel(record.getStringValueByName(loginParameters.APILEVEL.getValue()));
        deviceInfoModel.setClientVersion(record.getStringValueByName(loginParameters.CLIENTVERSION.getValue()));
        deviceInfoModel.setBrand(record.getStringValueByName(loginParameters.BRAND.getValue()));
        deviceInfoModel.setType(record.getStringValueByName(loginParameters.TYPE.getValue()));
        loginModel.setDeviceInfo(deviceInfoModel);
    }

    /**
     * Method to set default Login data from json
     */
    public void setDefaultLoginBody() {
        TestDataRecord record = null;
        try {
            record = getDefaultLoginData("defaultLoginData.js");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        loginModel.setAuthProviderId(record.getStringValueByName(loginParameters.AUTHPROVIDERID.getValue()));
        if (isIdam) {
            loginModel.setEmailId(record.getStringValueByName(loginParameters.LOGINID.getValue()));
        } else {
            loginModel.setEmailId(record.getStringValueByName(loginParameters.EMAILID.getValue()));
        }
        loginModel.setDeviceType(record.getStringValueByName(loginParameters.DEVICETYPE.getValue()));

        DeviceInfoModel deviceInfoModel = new DeviceInfoModel();
        deviceInfoModel.setDeviceName(record.getStringValueByName(loginParameters.DEVICENAME.getValue()));
        deviceInfoModel.setDeviceKey(record.getStringValueByName(loginParameters.DEVICEKEY.getValue()));
        deviceInfoModel.setRegistrationId(record.getStringValueByName(loginParameters.REGISTRATIONID.getValue()));
        deviceInfoModel.setImei(record.getStringValueByName(loginParameters.IMEI.getValue()));
        deviceInfoModel.setSerial(record.getStringValueByName(loginParameters.SERIAL.getValue()));
        deviceInfoModel.setPlatformType(record.getStringValueByName(loginParameters.PLATFORMTYPE.getValue()));
        deviceInfoModel.setPlatformVersion(record.getStringValueByName(loginParameters.PLATFORMVERSION.getValue()));
        deviceInfoModel.setApiLevel(record.getStringValueByName(loginParameters.APILEVEL.getValue()));
        deviceInfoModel.setClientVersion(record.getStringValueByName(loginParameters.CLIENTVERSION.getValue()));
        deviceInfoModel.setBrand(record.getStringValueByName(loginParameters.BRAND.getValue()));
        deviceInfoModel.setType(record.getStringValueByName(loginParameters.TYPE.getValue()));
        loginModel.setDeviceInfo(deviceInfoModel);
    }

    /**
     * Login Parameter Enum
     */
    public enum loginParameters {
        AUTHPROVIDERID("authProviderId"),
        EMAILID("emailId"),
        LOGINID("loginId"),
        PASSWORD("password"),
        DEVICETYPE("deviceType"),
        DEVICENAME("deviceName"),
        DEVICEKEY("deviceKey"),
        REGISTRATIONID("registrationId"),
        IMEI("imei"),
        SERIAL("serial"),
        PLATFORMTYPE("platformType"),
        PLATFORMVERSION("platformVersion"),
        APILEVEL("apiLevel"),
        CLIENTVERSION("clientVersion"),
        BRAND("brand"),
        TYPE("type"),
        MODEL("model");

        public String value;

        loginParameters(String value) {
            this.value = value;

        }

        public String getValue() {
            return value;
        }
    }

    /**
     * Method to get Login Response
     *
     * @return Login validatable response
     */
    public static ValidatableResponse getLoginResponse() {
        ValidatableResponse response = given().body(loginJOSNBody).header("Content-Type", "application/json").log().all()
                .when()
                .post(loginURL)
                .then();
        response.log().all();
        Utils.addRequestResponseToLogger();
        return response;
    }

    /**
     * Method to get Access Token
     *
     * @return Access Token
     */
    public static String getAccessToken(ValidatableResponse response) {
        String accessToken = response.extract().path("accessToken");
        accessToken = Base64.b64encode(accessToken);
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

    /**
     * Method to update Login JSON Body
     *
     * @param key   JSON body key
     * @param value JSON body parameter  value
     * @throws org.json.simple.parser.ParseException
     */
    public void setLoginData(String key, Object value) throws org.json.simple.parser.ParseException {
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = (JSONObject) parser.parse(loginJOSNBody);
        jsonObject.put(key, value);
        loginJOSNBody = jsonObject.toJSONString();
    }
}
