package com.rjil.cloud.tej.api.test;

import com.jayway.jsonpath.JsonPath;
import com.jayway.restassured.module.jsv.JsonSchemaValidator;
import com.jayway.restassured.response.ValidatableResponse;
import com.rjil.cloud.tej.apihelpers.BoardsBaseScript;
import com.rjil.cloud.tej.apihelpers.LoginBaseScript;
import com.rjil.cloud.tej.common.VerificationFailException;
import com.rjil.cloud.tej.common.Verify;
import com.rjil.cloud.tej.common.logging.FrameworkLogger;
import com.rjil.cloud.tej.enums.DataType;
import com.rjil.cloud.tej.enums.LoginParameters;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.text.ParseException;

/**
 * Board script
 */

public class BoardTest extends BoardsBaseScript {

    @Test
    public void checkLogin() throws org.json.simple.parser.ParseException, VerificationFailException, ParseException, IOException {

        ValidatableResponse response = getCreateBoardAPIResponse();
        int status = response.extract().statusCode();
        Verify.verifyEquals(status,200,"Verify Success Status");
    }
}
