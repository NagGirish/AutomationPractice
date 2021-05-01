package test;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import api.commonAPI.Testcase;
import api.operationalAPI.ProductListAPI;
import pages.ButtonLabel;
import pages.Menus.FirstLevelMenu;

public class OrderTest extends Testcase {

	@Test(priority = 1, dataProvider = "product")
	public void placeOrder(String productName) {
		ProductListAPI product = new ProductListAPI();
		selectMenu(FirstLevelMenu.BLOUSE);
		product.selectProduct(productName);
		clickSpanButton(ButtonLabel.ADD_TO_CART);
		WebDriverWait wait = new WebDriverWait(getDriverInstance(), 30);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space(.)='Proceed to checkout']")));
		startCheckout();
		Assert.assertTrue(isElementPresent(By.xpath("//*[text()='Your order on My Store is complete.']")), "Order not placed");
	}
	
	@DataProvider
	public Object[][] product() {
		return new Object[][] { {"Blouse"}};
	}
}
