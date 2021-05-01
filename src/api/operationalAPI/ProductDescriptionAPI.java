package api.operationalAPI;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import pages.Calender;
import pages.MyAccounts;
import pages.Registration;

public class ProductDescriptionAPI extends GenericMethods implements MyAccounts {
	
	public void registerUser() {
		enterValueToTextbox(Registration.FIRST_NAME, "abc");
		enterValueToTextbox(Registration.LAST_NAME, "abc");
		selectValueInDropdown(Registration.STATE, "Alabama");
		selectDate(Calender.DAY, "2 ");
		selectDate(Calender.MONTH, "March ");
		selectDate(Calender.YEAR, "2021 ");
	}
	
	public void selectDate(Calender canlenderLabel, String value) {
		Select select = new Select(getDriverInstance().findElement(By.xpath("//label[text()='Date of Birth']/..//select[@id='"+canlenderLabel.getCanlenderLabel()+"']")));
		select.selectByVisibleText(value);
	}
}