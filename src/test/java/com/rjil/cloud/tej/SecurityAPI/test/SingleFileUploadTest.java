package com.rjil.cloud.tej.SecurityAPI.test;

import static com.jayway.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.rjil.cloud.tej.SecurityAPI.pojo.SingleFileUploadPojo;
import com.rjil.cloud.tej.SecurityAPI.pojo.TejUserLoginPojo;
import com.rjil.cloud.tej.Utility.ReadExcelFile;
import com.rjil.cloud.tej.Utility.ReadPropertyFile;

import jxl.read.biff.BiffException;

public class SingleFileUploadTest {

	int row_num;
	ReadExcelFile fromExcel = new ReadExcelFile();
	ReadPropertyFile prop = new ReadPropertyFile();
	String[][] arrayOfExcelData;
	List <Integer> col_num = new ArrayList();
	SingleFileUploadPojo singleUpload = new SingleFileUploadPojo();
	List <Integer> parameterList = new ArrayList();
	
	@BeforeClass
	public String[][] getExcelData() throws BiffException, IOException
	{
	arrayOfExcelData =	fromExcel.getExcelContent("TejSecurityAPI_TestData.xls","SingleFileUplaodAPI");
	return arrayOfExcelData;
	}
	
	
	@Test
	public void CheckValidSingleFileUpload() throws IOException, BiffException {

		String singleFileUplaodBody = getJsonBody("CheckValidSingleFileUpload");
		given().body(singleFileUplaodBody).header("Content-Type", "application/json")
	    .when()
	    .post(prop.getProperty("Tej_SIT_url") + prop.getProperty("TejUserLogin_url")).then().statusCode(200).extract().response().print();

	}
		
		private List setParameterList()
		{
		List <String> parameterList  = new ArrayList<String>();
		parameterList.add("name");
		parameterList.add("description");
		parameterList.add("isProfilePic");
		parameterList.add("isAutoUpload");
		parameterList.add("isContactPhoto");
		parameterList.add("sourceFolder");
		parameterList.add("hidden");
		return parameterList;
		}
		
		

		private String getJsonBody(String testCaseName) throws BiffException, IOException
		{
			String [] keywords= {"kapil","lonawala","trip"};
			int j= 0;
			row_num = fromExcel.getRow(arrayOfExcelData, testCaseName);
			col_num = fromExcel.getColumns(arrayOfExcelData, testCaseName, setParameterList());
			
			singleUpload.setName(arrayOfExcelData[row_num][col_num.get(j++)]);
			singleUpload.setSize((double) 123366);
			singleUpload.setHash("hash");
			singleUpload.setDescription(arrayOfExcelData[row_num][col_num.get(j++)]);
			singleUpload.setFolderKey("");
			singleUpload.setBoardKey("");
			singleUpload.setIsProfilePic(Boolean.parseBoolean(arrayOfExcelData[row_num][col_num.get(j++)]));
			singleUpload.setIsAutoUpload(Boolean.parseBoolean(arrayOfExcelData[row_num][col_num.get(j++)]));
			singleUpload.setIsContactPhoto(Boolean.parseBoolean(arrayOfExcelData[row_num][col_num.get(j++)]));
			singleUpload.setSourceFolder("");
			HashMap<String, String> location = new HashMap<String, String> ();
			location.put("latitude","34.89 degrees" );
			location.put("longitude", "65.34");
			location.put("altitude", "5890m");
			singleUpload.setLocation(location);
			singleUpload.setKeywords(keywords);
			singleUpload.setHidden(Boolean.parseBoolean(arrayOfExcelData[row_num][col_num.get(j++)]));
			
			String jsonbodyforSingleUpload= SingleFileUploadPojo.getGsonFromObject(singleUpload);
			System.out.println("jsonBody for login="+jsonbodyforSingleUpload);
			return jsonbodyforSingleUpload;
	}
		

		/**
		 * This method returns expected result to be used for assertion
		 * @param testCaseName- Name of the test case to be tested from excel sheet
		 * @param testParameter3 - Name of the parameter which stores result,in the excel sheet
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
