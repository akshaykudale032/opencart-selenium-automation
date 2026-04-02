package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.qa.opencart.constants.AppConstants.*;

import java.util.ArrayList;
import java.util.List;

import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	private By logoutLink = By.linkText("Logout");
	private By headers = By.tagName("h2");
	private By search = By.name("search");
	private By btnSearch = By.cssSelector("#search .btn ");

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		this.eleUtil = new ElementUtil(driver);
	}

	public String getAccountsPageTitle() {
		String title = eleUtil.waitFotTitleIs(HOME_PAGE_TITLE, DEFAULT_TIMEOUT);
		System.out.println("Account Page Title: " + title);
		return title;
	}

	public String getAccountsPageURL() {
		String title = eleUtil.waitForURLContains(HOME_PAGE_FRACTION_URL, DEFAULT_TIMEOUT);
		System.out.println("Account Page Title: " + title);
		return title;
	}

	public boolean isLogoutLinkDisplayed() {
		return driver.findElement(logoutLink).isDisplayed();
	}

	public List<String> getAccountsPageHeaders() {
		List<WebElement> headerEleList = eleUtil.waitForAllElementsVisible(headers, DEFAULT_TIMEOUT);
		List<String> hradersList = new ArrayList<String>();
		for (WebElement e : headerEleList) {
			String text = e.getText();
			hradersList.add(text);
		}
		System.out.println("Accounts page heades: " + hradersList);
		return hradersList;

	}
	
	public SearchResultsPage doProductSearch(String searchKey) {
		System.out.println("Search product: "+searchKey);
		eleUtil.doSendKeys(search, searchKey);
		eleUtil.doCLick(btnSearch);
		return new SearchResultsPage(driver);
	}

}
