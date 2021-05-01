package api.operationalAPI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import api.commonAPI.CommonUtils;
import pages.ButtonLabel;
import pages.Checkout;

public class GenericMethods extends CommonUtils {
	
	/**
	 * Clicks on any link based on an Label name provided.
	 *
	 * @param linkName
	 */
	public void clickLink(String linkName) {
		waitForElementVisible_xpath("//a[normalize-space(text())='" + linkName + "' or  normalize-space(.)='"+linkName+"']").click();
	}
	
	/**
	 * Navigates to the selected Menu.
	 *
	 * @param menu
	 */
	public void selectMenu(String menu) {
		String menus[] = menu.split(">");
		Actions actions = new Actions(getDriverInstance());
		System.out.println(menus[0]);
		WebElement element = waitForElementVisible_xpath("//a[text()='"+menus[0]+"']");
		actions.moveToElement(element).build().perform();;
		waitForElementVisible_xpath("//a[text()='"+menus[1]+"']").click();
	}

	/**
	 * Enters the value into a textbox based on an Label name provided.
	 *
	 * @param labelName 
	 * @param value
	 */
	public void enterValueToTextbox(String labelName, String value) {
			waitForElementVisible_xpath("//label[text()='" + labelName + "']/..//input").clear();
			waitForElementVisible_xpath("//label[text()='" + labelName + "']/..//input").sendKeys(value);
		}

	/**
	 * Enters the value into a textarea based on an Label name provided.
	 *
	 * @param labelName 
	 * @param value
	 */
	public void enterValueToTextArea(String labelName, String value) {
		waitForElementVisible_xpath("//label[text()='" + labelName + "']/..//textarea").clear();
		waitForElementVisible_xpath("//label[text()='" + labelName + "']/..//textarea").sendKeys(value);
	}
	
	/**
	 * Selects a value from the dropdown based on the visible text displayed on the screen.
	 *
	 * @param labelName 
	 * @param value
	 */
	public void selectValueInDropdown(String labelName, String value) {
		Select select = new Select(getDriverInstance().findElement(By.xpath("//label[text()='"+labelName+"']/..//select")));
		select.selectByVisibleText(value);
	}
	
	/**
	 * Selects one or more checkboxes based on the label passed.
	 *
	 * @param checkBoxesLabelsList 
	 */
	public void setCheckboxes(String... checkBoxesLabelsList) {
		for (String checkBoxLabel : checkBoxesLabelsList) {
			waitForElementVisible_xpath("//label[normalize-space(.)='" + checkBoxLabel + "']").click();
		}
	}

	/**
	 * Selects a radio button based on the label passed.
	 *
	 * @param labelName 
	 */
	public void selectRadioButton(String labelName) {
		waitForElementVisible_xpath("//label[normalize-space(.)='" + labelName + "']").click();
	}

	/**
	 * Clicks on the button based on the name of the button passed.
	 *
	 * @param buttonName 
	 */
	public void clickButton(String buttonName) {
		waitForElementVisible_xpath("//button[normalize-space(.)='" + buttonName + "']").click();
	}
	
	/**
	 * Clicks on the button based on the name of the button passed where the button is within span tag.
	 *
	 * @param buttonName 
	 */
	public void clickSpanButton(String buttonName) {
		waitForElementVisible_xpath("//span[normalize-space(.)='" + buttonName + "']").click();
	}
	
	/**
	 *  Used to scroll the page to the element where the operation needs to be performed.
	 *
	 * @param xpathOfElement 
	 */
	public void scrollToElement(String xpathOfElement) {
		System.out.println("Inside ScrollToElement");
		JavascriptExecutor js = (JavascriptExecutor) getDriverInstance();
		js.executeScript("arguments[0].scrollIntoView(true);", waitForElementVisible_xpath(xpathOfElement));
	}
	
	/**
	 *  Used to complete the checkout process.
	 *
	 */
	public void startCheckout() {
		clickSpanButton(ButtonLabel.PROCEED_TO_CHECKOUT);
		clickSpanButton(ButtonLabel.PROCEED_TO_CHECKOUT);
		clickSpanButton(ButtonLabel.PROCEED_TO_CHECKOUT);
		setCheckboxes(Checkout.TERMS_CONDITIONS);
		clickSpanButton(ButtonLabel.PROCEED_TO_CHECKOUT);
		clickLink(Checkout.PAY_BY_CHECK);
		clickButton(Checkout.I_CONFIRM_MY_ORDER);
	}

	public boolean verifyDataInTable(String dataValue) {
		return isElementPresent(By.xpath("//*[@class='dataTable']//td[text()='" + dataValue + "']"));
	}

	public String getDatafromExcel(int rowNumber, int columnNumber, String filename) {
		String data = "";
		try {
			FileInputStream excelFile = new FileInputStream(new File(filename));
			Workbook workbook = new HSSFWorkbook(excelFile);
			Sheet sheet = workbook.getSheetAt(0);
			Row row = sheet.getRow(rowNumber);
			Cell currentCell = row.getCell(columnNumber);
			if (currentCell.getCellTypeEnum() == CellType.STRING) {
				data = currentCell.getStringCellValue();
			} else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
				data = String.valueOf(currentCell.getNumericCellValue());
			}
			workbook.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	/**
	 * Waits till the loading icon is visible on the screen and then continues the execution
	 *
	 */
	public void waitForBrowserReady() {
		boolean flag = false;
		while (true) {
			if (isElementPresent(By.xpath("//div[@id='fancybox-loading']"))) {
				flag = true;
		}
			if (flag) {
				break;
			}
		}
	}
}
