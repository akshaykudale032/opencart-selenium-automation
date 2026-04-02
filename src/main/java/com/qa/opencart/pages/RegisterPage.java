package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.StringUtil;

public class RegisterPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	By fName = By.id("input-firstname");
	By lName = By.name("lastname");
	By email = By.cssSelector("#input-email");
	By telephone = By.id("input-telephone");
	By password = By.xpath("//input[@name='password']");
	By ConfPassword = By.id("input-confirm");
	By subscribeYes = By.xpath("//label[@class='radio-inline'][position()=1]/input[@type='radio']");
	By subscribeNo = By.xpath("//label[@class='radio-inline'][position()=2]/input[@type='radio']");
	By policy = By.xpath("//input[@name='agree']");
	By btnContinue = By.className("btn-primary");

	private By successMessg = By.cssSelector("div#content h1");

	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	public boolean userRegistraion(String fiName, String lName, String telephone, String password, String subscribe) {
		eleUtil.waitForElementVisible(this.fName, AppConstants.DEFAULT_TIMEOUT).sendKeys(fiName);
		eleUtil.doSendKeys(this.lName, lName);
		eleUtil.doSendKeys(this.email, StringUtil.getRandomEmail());
		eleUtil.doSendKeys(this.telephone, telephone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(this.ConfPassword, password);
		if (subscribe.equalsIgnoreCase("Yes")) {
			eleUtil.doCLick(subscribeYes);
		} else {
			eleUtil.doCLick(subscribeNo);
		}
		eleUtil.doCLick(policy);
		eleUtil.doCLick(btnContinue);
		String succMessage = eleUtil.waitForElementVisible(successMessg, AppConstants.MEDIUM_TIMEOUT).getText();
		System.out.println(succMessage + "for user ==>"+fiName );
		if (succMessage.contains(AppConstants.REGISTER_SUCCESS_MESSG)) {
			eleUtil.doCLick(logoutLink);
			eleUtil.doCLick(registerLink);
			return true;
		}

		return false;

	}

}
