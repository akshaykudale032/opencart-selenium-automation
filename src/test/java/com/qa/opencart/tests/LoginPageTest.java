package com.qa.opencart.tests;

import static com.qa.opencart.constants.AppConstants.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Feature("Login page improvement")
@Epic("100: Login Page")
@Story("101: Login page functionality")
public class LoginPageTest extends BaseTest {

	@Description("Verify Login Page Title....")
	@Severity(SeverityLevel.MINOR)
	@Test
	public void loginPageTitleTest() {
		String actTitle = loginpage.getLoginPageTitle();
		Assert.assertEquals(actTitle, LOGIN_PAGE_TITLE);
	}

	@Description("Verify Login Page URL")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void loginPageUrlTest() {
		String actUrl = loginpage.getLoginPageURL();
		Assert.assertTrue(actUrl.contains(LOGIN_PAGE_FRACTION_URL));
	}

	@Description("Verify forgot password link exist")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void loginPageForgotPasswordLinkTest() {
		Assert.assertTrue(loginpage.isForgotPasswordLinkDisplayed());
	}

	@Description("Verify Login with valid user")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = Short.MAX_VALUE)
	public void loginTest() {
		acctPage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(acctPage.getAccountsPageTitle(), HOME_PAGE_TITLE);
	}

}
