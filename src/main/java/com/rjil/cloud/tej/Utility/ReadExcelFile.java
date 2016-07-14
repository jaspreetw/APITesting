package com.rjil.cloud.tej.Utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadExcelFile  {
	//int i, j;

	ReadPropertyFile prop = new ReadPropertyFile();
	String[][] arrayOfExcelData;
	List <Integer> parameterList = new ArrayList();
	
	/**
	 * This method reads the data from excel sheet and returns it as an array of objects
	 * @param sheetName - Name of the sheet from excel file where test data of API is saved.
	 * @return - returns an array of objects which hold all data from sheet
	 * @throws BiffException
	 * @throws IOException
	 */
	
	public String[][] getExcelContent(String excelFileName, String sheetName) throws BiffException, IOException {
		String excelFile = prop.getProperty(excelFileName);
		String fileName = "src/main/resources/" + excelFileName;
		fileName.replace("/", File.separator);
		FileInputStream filestream = new FileInputStream(fileName);
		Workbook workbook = Workbook.getWorkbook(filestream);
		Sheet sheet = workbook.getSheet(sheetName);
		int numberOfRowsInSheet = sheet.getRows();
		int numberOfColumnsInSheet = sheet.getColumns();
		String arrayOfExcelData[][] = new String[numberOfRowsInSheet][numberOfColumnsInSheet];
		for (int i = 0; i < numberOfRowsInSheet; i++) {
			for (int j = 0; j < numberOfColumnsInSheet; j++) {
				arrayOfExcelData[i][j] = sheet.getCell(j, i).getContents();

			}

		}

		return arrayOfExcelData;
	}

	/**
	 * This method reads location of testCase from the array and return the location
	 * @param arrayOfExcelData - array having all test data related to API under testing
	 * @param testCaseName - Name of the test case to be searched in the test sheet
	 * @return - returns row no. where testCaseName found
	 */
	public Integer getTestCaseNameLocation(String arrayOfExcelData[][], String testCaseName) {
		System.out.println(arrayOfExcelData.length);
		for (int i = 0; i < arrayOfExcelData.length; i++) {
			for (int j = 0; j < arrayOfExcelData[0].length; j++) {
				if (arrayOfExcelData[i][j].equalsIgnoreCase(testCaseName)) {
					return i;
				}
			}

		}
		return null;
	}

	/**
	 * This method reads location of input parameter and returns the location 
	 * @param arrayOfExcelData - array having all test data related to API under testing
	 * @param ParameterName - Name of the parameter whose value to be searched in the test sheet
	 * @return - returns column no. where parameter value has found
	 */
	public Integer getParameterNameLocation(String arrayOfExcelData[][], String ParameterName) {

		for (int i = 0; i < arrayOfExcelData.length; i++) {
			for (int j = 0; j < arrayOfExcelData[0].length; j++) {
				if (arrayOfExcelData[i][j].equalsIgnoreCase(ParameterName)) {
					return j;
				}
			}

		}
		return null;
	}
	
	
	public String[][] getExcelData() throws BiffException, IOException {
		arrayOfExcelData = getExcelContent("TejSecurityAPI_TestData.xls","TejUserLoginAPI");
		return arrayOfExcelData;
	}
	
	
	public int getRow(String[][]arrayOfExcelData, String testCaseName)
	{
		int row_num = getTestCaseNameLocation(arrayOfExcelData, testCaseName);
		System.out.println("row no.="+row_num);
		return row_num;
	}
	
	
	public List getColumns(String[][]arrayOfExcelData,String testCaseName, List parameterList) 
	{
	List <Integer> col_num = new ArrayList();
	
	System.out.println("size of parameterList="+parameterList.size());
	for(int i =0;i<parameterList.size();i++)
	{
		System.out.println("value of each parameter="+parameterList.get(i).toString());
	int j=getParameterNameLocation(arrayOfExcelData, parameterList.get(i).toString());
	col_num.add(j);
	System.out.println("all column values of j="+j);
	}
	
	return col_num;
	}
	
	
	
	
	
}
