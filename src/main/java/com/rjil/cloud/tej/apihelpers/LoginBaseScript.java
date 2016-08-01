package com.rjil.cloud.tej.apihelpers;

import com.jayway.jsonpath.JsonPath;
import com.jayway.restassured.response.ValidatableResponse;
import com.rjil.cloud.tej.apiconstants.LoginConstants;
import com.rjil.cloud.tej.common.Base64;
import com.rjil.cloud.tej.common.Utils;

import java.io.File;
import java.io.IOException;

import static com.jayway.restassured.RestAssured.given;

public class LoginBaseScript extends BaseTestScript {
    protected static LoginConstants loginConstants;
    protected static String loginJOSNBody = "";
    protected static String loginURL = "";

    /**
     * Constructor to initialize Models and Login URLs
     */
    public LoginBaseScript() {
        initModels();

        String path = System.getProperty("user.dir") + "/resources/loginTestData/loginBody.js";
        File file = new File(path);
        try {
            loginJOSNBody = JsonPath.parse(file).jsonString();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        setLoginData(loginConstants.getEmailId(), serverConfig.get("Email"));
        setLoginData(loginConstants.getPassword(), serverConfig.get("Password"));
        setLoginData(loginConstants.getDeviceKey(), serverConfig.get("deviceKey"));

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
        loginConstants = new LoginConstants();
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
     */
    public void setLoginData(String path, Object value) {
        loginJOSNBody = JsonPath.parse(loginJOSNBody).set(path, value).jsonString();
    }

}
