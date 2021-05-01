package api.operationalAPI;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import pages.MyAccounts;

public class ProductListAPI extends GenericMethods implements MyAccounts {
	
	public void selectProduct(String productName) {
		WebElement element = waitForElementVisible_xpath("//a[normalize-space(text())='"+productName+"' or  normalize-space(.)='"+productName+"']/../../..//img");
		Actions actions = new Actions(getDriverInstance());
		actions.moveToElement(element).build().perform();
	}
	
}