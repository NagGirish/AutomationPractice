package test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import api.commonAPI.CommonUtils;
import api.operationalAPI.GenericMethods;
import api.operationalAPI.RegisterAPI;
import pages.ButtonLabel;
import pages.Home;
import pages.PropertiesLabel;
import pages.Registration;

public class RegisterTest extends GenericMethods {

	RegisterAPI register = new RegisterAPI();
	
	@Test(priority = 1, dataProvider = "email")
	public void registerUser(String email) {
		getDriverInstance().get(CommonUtils.getDataFromProperties(PropertiesLabel.URL));
		clickLink(Home.SIGN_IN);
		clickLink(Home.SIGN_IN);
		enterValueToTextbox(Registration.EMAIL_ADDRESS, email);
		clickButton(ButtonLabel.CREATE_AN_ACCOUNT);
		register.registerUser();
	}

	
	@DataProvider
	public Object[][] email() {
		return new Object[][] { {"email111@gmail.com"}};
	}
}
