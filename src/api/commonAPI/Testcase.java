package api.commonAPI;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

import api.operationalAPI.GenericMethods;
import pages.PropertiesLabel;

public class Testcase extends GenericMethods {

	Login login;
	int testExecTimeMin = 0;
	int testExecTimeSec = 0;

	@BeforeMethod(alwaysRun = true)
	public void Login() {
		login = new Login();
		cmdExecutor("taskkill /f /im " + CommonUtils.getDataFromProperties(PropertiesLabel.KILL_BROWSER) + "* /T");
		login.login_App();
	}

	@AfterMethod(alwaysRun = true)
	public void logout(ITestResult result) {
		login = new Login();
		login.logoutApp();

		int endTime = (int) result.getEndMillis();
		int startTime = (int) result.getStartMillis();
		int execTime = (endTime - startTime) / 1000;
		int execTimeSec = execTime % 60;
		int execTimeMin = execTime / 60;
		testExecTimeMin += execTimeMin;
		testExecTimeSec += execTimeSec;
		System.out.println("*********************************************************************************************************");
		System.out.println("Time taken to run test case : " + execTimeMin + " minutes " + execTimeSec + " seconds");

		if (result.isSuccess()) {
			System.out.println("Test name [ " + result.getName() + " ] : Passed.");
			System.out.println("*********************************************************************************************************");

		} else {
			Throwable thrown = result.getThrowable();
			System.out.println("Test name [ " + result.getName() + " ] : Failed.");
			System.out.println("Error message : \n" + thrown.getMessage());
			System.out.println("*********************************************************************************************************");
		}
	}

	@AfterTest(alwaysRun = true)
	public void afterTest(ITestContext context) {
		int excessMin = 0;
		if (testExecTimeSec >= 60) {
			excessMin = testExecTimeSec / 60;
			testExecTimeSec %= 60;
		}
		System.out.println("Total Testcases Run -> " + context.getSuite().getAllMethods().size());
		System.out.println("Testcases Passed -> " + context.getPassedTests().size() + " | Testcases Failed -> " + context.getFailedTests().size() + " | Testcases Skipped -> " + context.getSkippedTests().size());
		System.out.println("Total time taken to run the entire Suite : " + (testExecTimeMin + excessMin) + " minutes " + testExecTimeSec + " seconds");
		System.out.println("*********************************************************************************************************");
	}

}
