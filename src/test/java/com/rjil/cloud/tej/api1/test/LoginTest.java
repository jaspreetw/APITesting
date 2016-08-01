package com.rjil.cloud.tej.api1.test;

import com.jayway.restassured.module.jsv.JsonSchemaValidator;
import com.jayway.restassured.response.ValidatableResponse;
import com.rjil.cloud.tej.apihelpers1.LoginBaseScript;
import com.rjil.cloud.tej.common1.VerificationFailException;
import com.rjil.cloud.tej.common1.Verify;
import com.rjil.cloud.tej.common1.datadriven.model.TestDataRecord;
import com.rjil.cloud.tej.common1.logging.FrameworkLogger;
import com.rjil.cloud.tej.enums1.DataType;
import com.rjil.cloud.tej.enums1.LoginParameters;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.text.ParseException;

/**
 * Login script
 */

public class LoginTest extends LoginBaseScript {


    @DataProvider
    public Object[][] LoginCredentials() throws ParseException {
        return getTesData("LoginTestdata.xlsx", DataType.EXCEL);
    }

    @Test(dataProvider = "LoginCredentials")

    public void checkStatus(TestDataRecord record) throws org.json.simple.parser.ParseException, VerificationFailException {
        loginJOSNBody=record.toString();
        int status = getLoginResponse().extract().statusCode();
        Verify.verifyEquals(status,200,"Verify Success Status");
    }

    @Test
    public void checkLogin() throws org.json.simple.parser.ParseException, VerificationFailException, ParseException {
        FrameworkLogger.logStep("Set" + LoginParameters.AUTHPROVIDERID.getValue());

        setLoginData(LoginParameters.AUTHPROVIDERID.getValue(), 1);
        ValidatableResponse response = getLoginResponse();
        int status = response.extract().statusCode();

        Verify.verifyEquals(status,200,"Verify Success Status");
    }

    @Test
    public void checkLoginSchema() {
        getLoginResponse().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("loginTestData/test-schema.json"));
    }

}
