package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class SearchTest extends BaseTest{
	
	@BeforeClass
	public void searchResultPageSetup() {
		acctPage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void productSearchTest() {
		serchResultPage = acctPage.doProductSearch("macbook");
		int accProductsCount = serchResultPage.getSearchProductResultCount();
		Assert.assertEquals(accProductsCount, 3);
	}

}
