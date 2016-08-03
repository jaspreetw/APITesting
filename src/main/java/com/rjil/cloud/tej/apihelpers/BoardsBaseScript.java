package com.rjil.cloud.tej.apihelpers;

import com.jayway.jsonpath.JsonPath;
import com.jayway.restassured.response.ValidatableResponse;
import com.rjil.cloud.tej.common.Utils;
import com.rjil.cloud.tej.enums.BoardParameters;

import java.io.File;
import java.io.IOException;

import static com.jayway.restassured.RestAssured.given;

public class BoardsBaseScript extends BaseTestScript {
    protected static String boardJSONBody;
    protected static String boardName = "QA_";
    public static int max = 9999;
    public static int min = 100;

    public static String getBoardURL() {
        return apiUrls.get("baseURL") + apiUrls.get("boardsURL");
    }

    protected static void setBoardJsonBody() throws IOException {
        //load json file
    	boardName=boardName+Utils.setRandomValue(max, min);
        String path = System.getProperty("user.dir") + "/resources/boardTestData/boardBody.js";
        File file = new File(path);
        boardJSONBody = JsonPath.parse(file).jsonString();
        boardJSONBody = setJsonData(boardConstants.getBoardName(), boardName, boardJSONBody);
      
    }


    /**
     * Method to get Board Response
     *
     * @return board validatable response
     */
    public static ValidatableResponse getCreateBoardAPIResponse() throws IOException {
        setBoardJsonBody();
        ValidatableResponse response = given().body(boardJSONBody)
                .header("Content-Type", "application/json")
                .header(BoardParameters.XUSERID.getValue(), userId)
                .header(BoardParameters.XDEVICEKEY.getValue(), serverConfig.get("deviceKey"))
                .header(BoardParameters.AUTHORIZATION.getValue(), accessToken)
                .header(BoardParameters.ACCEPTLANGUAGE.getValue(), "en")
                .log().all()
                .when()
                .post(getBoardURL())
                .then();
        response.log().all();
        Utils.addRequestResponseToLogger();
        return response;
    }
}
