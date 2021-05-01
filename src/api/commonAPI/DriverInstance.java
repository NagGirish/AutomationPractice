package api.commonAPI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import pages.PropertiesLabel;

public class DriverInstance {

	private static WebDriver driver;
	private static String ifileChrome = "./resources/chromedriver.exe";
	private static String iFileFirefox = "./resources/geckodriver.exe";


	public static WebDriver getDriverInstance() {
		if (driver == null || driver.toString().contains("null")) {

			if(CommonUtils.getDataFromProperties(PropertiesLabel.BROWSER).equalsIgnoreCase("chrome")){
				driver = new ChromeDriver(setChromeOptions());
			} else if (CommonUtils.getDataFromProperties(PropertiesLabel.BROWSER).equalsIgnoreCase("firefox")){
				System.setProperty("webdriver.gecko.driver",iFileFirefox);
		        FirefoxOptions options = new FirefoxOptions();
		        options.addPreference("browser.privatebrowsing.autostart", true);
		        driver = new FirefoxDriver(options);
		        String zoomInJS = "document.body.style.zoom='85%'";
		        JavascriptExecutor js = (JavascriptExecutor) driver;
		        js.executeScript(zoomInJS);
			} else {
				System.out.println("Please mention some value for browser in config file");
			}
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		return driver;
	}

	public static ChromeOptions setChromeOptions() {
		System.setProperty("webdriver.chrome.driver", ifileChrome);
		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("profile.default_content_settings.popups", 0);
		prefs.put("credentials_enable_service", false);
		prefs.put("password_manager_enabled", false);
		ChromeOptions options = new ChromeOptions();
		options.addArguments(Arrays.asList("--no-sandbox", "--ignore-certificate-errors", "--disable-extensions", "--disable-popup-blocking", "--disable-cache", "--account-consistency"));
		options.setExperimentalOption("prefs", prefs);
		options.setExperimentalOption("excludeSwitches", new String[] {"load-extension", "enable-automation"});
		options.addArguments(Arrays.asList("--start-maximized", "--incognito", "--allow-running-insecure-content"));
		options.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
		options.setCapability(CapabilityType.SUPPORTS_APPLICATION_CACHE, false);
		options.setCapability("nativeEvents", false);
		options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		options.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
		return options;
	}

	public static FirefoxOptions setFirefoxOptions() {
		System.setProperty("webdriver.gecko.driver", iFileFirefox);
		FirefoxOptions firefoxOptions = new FirefoxOptions();
		FirefoxProfile profile = new FirefoxProfile();
		DesiredCapabilities dc = DesiredCapabilities.firefox();
		profile.setAcceptUntrustedCertificates(false);
		profile.setAssumeUntrustedCertificateIssuer(true);
		dc = DesiredCapabilities.firefox();
		dc.setCapability(FirefoxDriver.PROFILE, profile);
		firefoxOptions.merge(dc);
		return firefoxOptions;
	}

	/**
	 * Checks for all the visible elements and performs action
	 * 
	 * @param xPath
	 */
	public WebElement waitForElementVisible_xpath(String sXPath) {
		List<WebElement> lsDisplayed = new ArrayList<WebElement>();
		try {
			for (int i = 0; i < 5; i++) {

				try {
					List<WebElement> lsw = getDriverInstance().findElements(By.xpath(sXPath));
					lsDisplayed = getDisplayedElements(lsw);
					if (lsDisplayed.size() > 0) {
						//System.out.println("Element Found");
						break;
					} else {
						System.out.println("waiting for Element Visible : " + i);
					}
					if (i == 5) {
						throw new Exception("Not able to find the element with xpath : [" + sXPath + "]");
					}
				} catch (Exception e) {
					System.out.println("waiting for Element Visible : " + i);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (lsDisplayed.size() < 1) {
			try {
				throw new Exception("Not able to find the element with xpath : [" + sXPath + "]");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return lsDisplayed.get(0);
	}

	/**
	 * Retrieves all the elements that are available and passes the condition isDisplayed to perform actions on.
	 * 
	 * @param lsElmts
	 * @return
	 */
	public List<WebElement> getDisplayedElements(List<WebElement> lsElmts) {

		List<WebElement> elementsWithText = new ArrayList<WebElement>();
		System.out.println("\n\t\t\t\t\t\t\t\t\t\t\t\t\tTotal No of Elements : " + lsElmts.size());
		for (WebElement w : lsElmts) {
			if (w.isDisplayed()) {
				elementsWithText.add(w);
				System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\t Visible Element - [" + w.getText() + "]");
			}
		}
		System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t\tTotal No of Visible Elements : " + elementsWithText.size());
		return elementsWithText;
	}

}
