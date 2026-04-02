package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.base.BaseTest;

public class ProductInfoPageTest extends BaseTest {

	@BeforeClass
	public void productInfoPageSetUp() {
		acctPage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@DataProvider
	public Object[][] getProductSearchTestData() {
		return new Object[][] { { "macbook", "MacBook Pro" }, { "macbook", "MacBook Air" },
				{ "samsung", "Samsung Galaxy Tab 10.1" }, { "imac", "iMac" }, { "canon", "Canon EOS 5D" } };
	}

	@Test(dataProvider = "getProductSearchTestData")
	public void productNameTest(String searchKey, String productName) {
		serchResultPage = acctPage.doProductSearch(searchKey);
		productInfo = serchResultPage.selectProduct(productName);
		String accSearchedProduct = productInfo.getProductHeader();
		Assert.assertEquals(accSearchedProduct, productName);

	}

	@DataProvider
	public Object[][] getProductSearchImgData() {
		return new Object[][] { { "macbook", "MacBook Pro", 4 }, { "macbook", "MacBook Air", 4 },
				{ "samsung", "Samsung Galaxy Tab 10.1", 7 }, { "imac", "iMac", 4 }, { "canon", "Canon EOS 5D", 3 } };
	}

	@Test(dataProvider = "getProductSearchImgData")
	public void productImagesCountTest(String searchKey, String productName, int count) {
		serchResultPage = acctPage.doProductSearch(searchKey);
		productInfo = serchResultPage.selectProduct(productName);
		int actImgCount = productInfo.getProductImgCount();
		Assert.assertEquals(actImgCount, count);
	}
	
	
	@Test
	public void productDetailsTest() {
		serchResultPage = acctPage.doProductSearch("macbook");
		productInfo = serchResultPage.selectProduct("MacBook Pro");
		Map<String, String> accPtoductDetails = productInfo.getProductDetails();
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertEquals(accPtoductDetails.get("Brand"), "Apple");
		softAssert.assertEquals(accPtoductDetails.get("Product Code"), "Product 18");
		softAssert.assertEquals(accPtoductDetails.get("Reward Points"), "800");
		softAssert.assertEquals(accPtoductDetails.get("Availability"), "Out Of Stock");
		softAssert.assertAll();
	}

}
