package api.operationalAPI;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import pages.Calender;
import pages.MyAccounts;

public class RegisterAPI extends GenericMethods implements MyAccounts {
	
	public void registerUser() {
		
	}
	
	public void selectDate(Calender canlenderLabel, String value) {
		Select select = new Select(getDriverInstance().findElement(By.xpath("//label[text()='Date of Birth']/..//select[@id='"+canlenderLabel.getCanlenderLabel()+"']")));
		select.selectByVisibleText(value);
	}
}