package com.qa.opencart.tests;

import static com.qa.opencart.constants.AppConstants.*;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class AccountsPageTest extends BaseTest {
	
	@BeforeClass
	public void accountPageSetup() {
		acctPage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void accountPageTitleTest() {
		String accTitle = acctPage.getAccountsPageTitle();
		Assert.assertEquals(accTitle, HOME_PAGE_TITLE);
	}
	
	@Test
	public void accountPageURLTest() {
		Assert.assertTrue(acctPage.getAccountsPageURL().contains(HOME_PAGE_FRACTION_URL));
		
	}
	
	@Test
	public void forgotPasswordLinkTest() {
		Assert.assertTrue(acctPage.isLogoutLinkDisplayed());
		
	}
	
	@Test
	public void accountsPageHeadersTest() {
		List<String> accHeaderList = acctPage.getAccountsPageHeaders();
		Assert.assertEquals(accHeaderList, AccountsPageHeaders);
		
	}
	
	

}
