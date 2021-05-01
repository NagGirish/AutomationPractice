package api.commonAPI;

import pages.ButtonLabel;
import pages.PropertiesLabel;


public class Login extends CommonUtils implements ButtonLabel{

	public void login_App() {
		getDriverInstance().get(CommonUtils.getDataFromProperties(PropertiesLabel.URL));
		waitForElementVisible_xpath(".//a[contains(text(),'Sign in')]").click();
		System.out.println("Url Opened-->" + CommonUtils.getDataFromProperties(PropertiesLabel.URL));
		System.out.println("Login " + getDriverInstance().getWindowHandle());
		
		waitForElementVisible_xpath("//input[@name='email']").sendKeys(CommonUtils.getDataFromProperties(PropertiesLabel.USERNAME));
		waitForElementVisible_xpath("//input[@name='passwd']").sendKeys(CommonUtils.getDataFromProperties(PropertiesLabel.PASSWORD));
		waitForElementVisible_xpath(".//button[@id='SubmitLogin']").click();
	}
	

	public void logoutApp() {
		waitForElementVisible_xpath("//a[normalize-space(.)='Sign out']").click();
		getDriverInstance().manage().deleteAllCookies();
//		getDriverInstance().quit();
//		cmdExecutor("taskkill /f /im " + CommonUtils.getDataFromProperties(PropertiesLabel.KILL_BROWSER) + "* /T");
	}

}
