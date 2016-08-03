package com.rjil.cloud.tej.apihelpers;

import com.jayway.restassured.response.ValidatableResponse;
import com.rjil.cloud.tej.common.Utils;

import static com.jayway.restassured.RestAssured.given;

public class LoginBaseScript extends BaseTestScript {

    /**
     * Method to get Login Response
     *
     * @return Login validatable response
     */
    public static ValidatableResponse getLoginResponse() {
        ValidatableResponse response = given().body(loginJSONBody).header("Content-Type", "application/json").log().all()
                .when()
                .post(loginURL)
                .then();
        response.log().all();
        Utils.addRequestResponseToLogger();
        return response;
    }

}
