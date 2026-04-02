package com.qa.opencart.pages;

import static com.qa.opencart.constants.AppConstants.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	// 1. private By locators

	private By email = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@type='submit']");
	private By forgotPassLink = By.linkText("Forgotten Password");
	private By registerLink = By.linkText("Register");

	// 2. public constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// 3. Actions methods

	@Step("Getting login page title")
	public String getLoginPageTitle() {
		String title = eleUtil.waitFotTitleIs(LOGIN_PAGE_TITLE, DEFAULT_TIMEOUT);
		System.out.println("Login page title: " + title);
		return title;
	}

	@Step("Getting login page URL")
	public String getLoginPageURL() {
		String url = eleUtil.waitForURLContains(LOGIN_PAGE_FRACTION_URL, DEFAULT_TIMEOUT);
		System.out.println("Login page URL: " + url);
		return url;
	}

	@Step("Checking Forgot Password Link")
	public boolean isForgotPasswordLinkDisplayed() {
		return eleUtil.isElementDisplayed(forgotPassLink);
	}

	@Step("Login with the username: {0} and password: {1}")
	public AccountsPage doLogin(String username, String pwd) {
		eleUtil.waitForElementVisible(email, DEFAULT_TIMEOUT).sendKeys(username);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doCLick(loginBtn);
		return new AccountsPage(driver);
	}

	@Step("Navigating to Account registration page")
	public RegisterPage navigateToRegisterPage() {
		eleUtil.waitForElementVisible(registerLink, DEFAULT_TIMEOUT).click();
		return new RegisterPage(driver);

	}

}
