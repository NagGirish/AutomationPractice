package api.commonAPI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import pages.PropertiesLabel;

public class CommonUtils extends DriverInstance {

	/**
	 * Used to fetch the data from properties file.
	 * 
	 * @param propertiesLabel
	 */
	public static String getDataFromProperties(PropertiesLabel propertiesLabel) {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("./resources/config.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return (prop.getProperty(propertiesLabel.getPropertiesLabel()));
	}

	/**
	 * Executes command in the command prompt.
	 * 
	 * @param sCommand
	 */
	public void cmdExecutor(String sCommand) {
		Process p;
		try {
			p = Runtime.getRuntime().exec("cmd /c " + sCommand);
			p.waitFor();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Returns all the visible elements
	 * 
	 * @param By
	 */
	public static boolean isElementPresent(By by) {
		List<WebElement> elements;
		try {
			elements = getDriverInstance().findElements(by);
			if (elements.size() > 0) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Used to fetch the data from excel file based on the sheet and row number
	 * 
	 * @param sheetName
	 * @param file
	 * @param rowNum
	 */
	public static ArrayList<String> getData(String sheetName,File file, int rowNum)  {

		ArrayList<String> rowdata=new ArrayList<String>();
		try {
			FileInputStream fileinputStream = new FileInputStream(file);
			XSSFWorkbook workbook = new XSSFWorkbook(fileinputStream);
			DataFormatter dataFormatter = new DataFormatter();
			FormulaEvaluator formulaEvalutor=new XSSFFormulaEvaluator(workbook);
			Sheet sheet = workbook.getSheet(sheetName);
			short lastCellNum = sheet.getRow(0).getLastCellNum();
			Row row = sheet.getRow(rowNum);
			for (int j = 0; j < lastCellNum ; j++) {
				Cell cell = row.getCell(j);
				formulaEvalutor.evaluate(cell);
				if(cell==null)
				{
					rowdata.add(null);
				}
				else
				{
					String formatCellValue = dataFormatter.formatCellValue(cell, formulaEvalutor);
					rowdata.add(formatCellValue);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return rowdata;
	}

}
