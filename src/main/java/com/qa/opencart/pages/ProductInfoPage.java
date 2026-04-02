package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	private WebDriver driver;
	private ElementUtil eleUtil;

	private By searchedProductName = By.tagName("h1");
	private By imgCount = By.cssSelector("#content *> img");
	private By productMetaData = By.xpath("(//div[@class='col-sm-4']//ul)[1]//li");
	private By productPriceData = By.xpath("(//div[@class='col-sm-4']//ul)[2]//li");

	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		this.eleUtil = new ElementUtil(driver);
	}

	Map<String, String> productMap;

	public String getProductHeader() {
		String productName = eleUtil.waitForElementVisible(searchedProductName, AppConstants.DEFAULT_TIMEOUT).getText();
		System.out.println("Selected Product Name: " + productName);
		return productName;
	}

	public int getProductImgCount() {
		int totalImgs = eleUtil.waitForAllElementsVisible(imgCount, AppConstants.DEFAULT_TIMEOUT).size();
		System.out.println("Total Images Count: " + totalImgs);
		return totalImgs;
	}

	public Map<String, String> getProductDetails() {
		productMap = new HashMap<String, String>();
		productMap.put("Product header", getProductHeader());
		productMap.put("Product Images", String.valueOf(getProductImgCount()));
		getProductMetaData();
		getProductPriceData();
		System.out.println("Product all details: " + productMap);
		return productMap;

	}

//	Brand: Apple
//	Product Code: Product 18
//	Reward Points: 800
//	Availability: Out Of Stock

	public void getProductMetaData() {
		List<WebElement> productDataList = eleUtil.waitForAllElementsVisible(productMetaData,
				AppConstants.DEFAULT_TIMEOUT);
		for (WebElement e : productDataList) {
			String[] data = e.getText().split(":");
			String dataKey = data[0].trim();
			String dataValue = data[1].trim();
			productMap.put(dataKey, dataValue);
		}
	}

	
//	$2,000.00
//	Ex Tax: $2,000.00
	
	public void getProductPriceData() {
		List<WebElement> productPriceList = eleUtil.waitForAllElementsVisible(productPriceData,
				AppConstants.DEFAULT_TIMEOUT);
		String productPrice = productPriceList.get(0).getText();
		String extaxPrice = productPriceList.get(1).getText();
		productMap.put("product price", productPrice);
		productMap.put("Ex Tax", extaxPrice);
	}

}
