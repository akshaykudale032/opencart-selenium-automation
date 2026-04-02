package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class SearchResultsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By searchResultCount = By.className("product-thumb");

	public SearchResultsPage(WebDriver driver) {
		this.driver = driver;
		this.eleUtil = new ElementUtil(driver);
	}

	public int getSearchProductResultCount() {
		int productsCount = eleUtil.waitForAllElementsVisible(searchResultCount, AppConstants.DEFAULT_TIMEOUT).size();
		System.out.println("Total Products: " + productsCount);
		return productsCount;

	}

	public ProductInfoPage selectProduct(String productName) {
		System.out.println("Product Name: " + productName);
		eleUtil.doCLick(By.linkText(productName));
		return new ProductInfoPage(driver);
	}

}
