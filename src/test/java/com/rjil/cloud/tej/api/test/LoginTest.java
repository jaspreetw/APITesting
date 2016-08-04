package com.rjil.cloud.tej.api.test;

import com.jayway.jsonpath.JsonPath;
import com.jayway.restassured.module.jsv.JsonSchemaValidator;
import com.jayway.restassured.response.ValidatableResponse;
import com.rjil.cloud.tej.apihelpers.LoginBaseScript;
import com.rjil.cloud.tej.common.VerificationFailException;
import com.rjil.cloud.tej.common.Verify;
import com.rjil.cloud.tej.common.datadriven.model.TestDataRecord;
import com.rjil.cloud.tej.common.logging.FrameworkLogger;
import com.rjil.cloud.tej.enums.DataType;
import com.rjil.cloud.tej.enums.LoginParameters;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

/**
 * Login script
 */

public class LoginTest extends LoginBaseScript {


    @DataProvider
    public Object[][] LoginCredentials() throws ParseException {
        return getTesData("LoginTestData/loginBody_DataDriven.js", DataType.JSON);
    }

    @Test(dataProvider = "LoginCredentials")

    public void checkStatus(Object record) throws VerificationFailException, IOException {
        loginJSONBody= JsonPath.parse(record).jsonString();
        setIdamJsonBody();
        int status = getLoginResponse().extract().statusCode();
        Verify.verifyEquals(status,200,"Verify Success Status");
    }

    @Test
    public void checkLogin() throws VerificationFailException, IOException {
        FrameworkLogger.logStep("Set" + LoginParameters.AUTHPROVIDERID.getValue());

        loginJSONBody=setJsonData(LoginParameters.AUTHPROVIDERID.getValue(), 1,loginJSONBody);
        ValidatableResponse response = getLoginResponse();
        int status = response.extract().statusCode();
        Verify.verifyEquals(status,200,"Verify Success Status");
    }

    @Test
    public void checkLoginSchema() throws IOException {
        getLoginResponse().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("loginTestData/test-schema.json"));
    }

}
