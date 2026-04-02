package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class RegistrationTest extends BaseTest {

	@BeforeClass
	public void userRegistrationSetUp() {
		registerPage = loginpage.navigateToRegisterPage();
	}

	@DataProvider
	public Object[][] getUserRegTestData() {
		return new Object[][] { { "testu1", "testlname", "1234567890", "test@123", "yes" },
				{ "testu2", "test2name", "1234567680", "test@123", "no" }, };
	}

	@DataProvider
	public Object[][] getUserExcelTestData() {
		return ExcelUtil.getTestData(AppConstants.EXCEL_SHEET_NAME);
	}

	@Test(dataProvider = "getUserExcelTestData")
	public void userRegistraionTest(String fName, String lName, String telephone, String password, String subscribe) {
		Assert.assertTrue(registerPage.userRegistraion(fName, lName, telephone, password, subscribe));
	}

}
