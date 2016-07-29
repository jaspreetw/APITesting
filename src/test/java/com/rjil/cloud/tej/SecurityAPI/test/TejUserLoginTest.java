package com.rjil.cloud.tej.SecurityAPI.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.*;

import com.rjil.cloud.tej.APIModels.LoginModel;
import com.rjil.cloud.tej.Utility.ReadExcelFile;
import com.rjil.cloud.tej.Utility.ReadPropertyFile;
import jxl.read.biff.BiffException;

public class TejUserLoginTest {

	int row_num;
	ReadExcelFile fromExcel = new ReadExcelFile();
	ReadPropertyFile prop = new ReadPropertyFile();
	String[][] arrayOfExcelData;
	List <Integer> col_num = new ArrayList();
	LoginModel login = new LoginModel();
	List <Integer> parameterList = new ArrayList();

	@BeforeClass
	public String[][] getExcelData() throws BiffException, IOException
	{
	arrayOfExcelData =	fromExcel.getExcelContent("TejSecurityAPI_TestData.xls","TejUserLoginAPI");
	return arrayOfExcelData;
	}


	@Test
	public void CheckValidTejUserLogin() throws IOException, BiffException {

	/*	//String loginBody = getJsonBody("CheckValidTejUserLogin");

		given().body(loginBody).header("Content-Type", "application/json")
	    .when()
	    .post(prop.getProperty("Tej_SIT_url") + prop.getProperty("TejUserLogin_url"))
		.then().statusCode(200).extract().response().print();
		*/

//		Response response = given().content(io.restassured.http.ContentType.JSON).body(loginBody).when()
//				.post(prop.getProperty("Tej_SIT_url") + prop.getProperty("TejUserLogin_url")).then().extract().response();
	//	System.out.println(response.asString());
	//	JsonParser parser = new JsonParser();
	//	JsonObject obj = (JsonObject) parser.parse(response.asString());
	//	System.out.println(obj.get("userId"));
	//	System.out.println(obj.get("authToken").getAsJsonObject().get("accessToken"));

	}

		private List setParameterList()
	{
	List <String> parameterList  = new ArrayList<String>();
	parameterList.add("authProviderId");
	parameterList.add("emailId");
	parameterList.add("password");
	parameterList.add("deviceType");
	parameterList.add("deviceName");
	parameterList.add("deviceKey");
	parameterList.add("registrationId");
	parameterList.add("imei");
	parameterList.add("serial");
	parameterList.add("platformType");
	parameterList.add("platformVersion");
	parameterList.add("apiLevel");
	parameterList.add("clientVersion");
	parameterList.add("brand");
	parameterList.add("model");
	return parameterList;
	}



/*	private String getJsonBody(String testCaseName) throws BiffException, IOException
	{
		int j= 0;
		row_num = fromExcel.getRow(arrayOfExcelData, testCaseName);
		col_num = fromExcel.getColumns(arrayOfExcelData, testCaseName, setParameterList());


		login.setAuthProviderId(arrayOfExcelData[row_num][col_num.get(j++)]);
		login.setEmailId(arrayOfExcelData[row_num][col_num.get(j++)]);
		login.setPassword(arrayOfExcelData[row_num][col_num.get(j++)]);
		login.setDeviceType(arrayOfExcelData[row_num][col_num.get(j++)]);

		HashMap<String, String> map = new HashMap<>();
		map.put("deviceName", arrayOfExcelData[row_num][col_num.get(j++)] );
		map.put("deviceKey", arrayOfExcelData[row_num][col_num.get(j++)]);
		map.put("registrationId",arrayOfExcelData[row_num][col_num.get(j++)]);
		map.put("imei", arrayOfExcelData[row_num][col_num.get(j++)]);
		map.put("serial", arrayOfExcelData[row_num][col_num.get(j++)]);
		map.put("platformType", arrayOfExcelData[row_num][col_num.get(j++)]);
		map.put("platformVersion", arrayOfExcelData[row_num][col_num.get(j++)] );
		map.put("apiLevel", arrayOfExcelData[row_num][col_num.get(j++)]);
		map.put("clientVersion", arrayOfExcelData[row_num][col_num.get(j++)]);
		map.put("brand", arrayOfExcelData[row_num][col_num.get(j++)]);
		map.put("model", arrayOfExcelData[row_num][col_num.get(j++)]);
		map.put("manufacturer", "manufacturer");
		map.put("product", "product");
		map.put("screenSize", "screenSize");
		map.put("screenResolution", "screenResolution");
		map.put("screenDensity", "screenDensity");

		login.setDeviceInfo(map);

		String jsonbodyforlogin = LoginModel.getGsonFromObject(login);
		System.out.println("jsonBody for login="+jsonbodyforlogin);
		return jsonbodyforlogin;
}*/


	/**
	 * This method returns expected result to be used for assertion
	 * @param testCaseName- Name of the test case to be tested from excel sheet
	// * @param testParameter3 - Name of the parameter which stores result,in the excel sheet
	 * @return - returns expected results with respect to values passed as input
	 * @throws IOException
	 * @throws BiffException
	 */
	private String getExpectedResult(String testCaseName, String testResultParameter) throws BiffException, IOException {
		Integer row_num = fromExcel.getTestCaseNameLocation(getExcelData(), testCaseName);
		Integer col_num = fromExcel.getParameterNameLocation(getExcelData(),  testResultParameter);
		if(col_num != null && row_num != null)
		{
			return arrayOfExcelData[row_num.intValue()][col_num.intValue()];
		}
		return "";
	}






















}
