package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.qameta.allure.Step;

public class ElementUtil {

	private WebDriver driver;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
	}

	@Step("Filling value: {1} in element {0]")
	public void doSendKeys(By locator, String value) {
		nullCheck(value);
		getElement(locator).clear();
		getElement(locator).sendKeys(value);
	}

	public void doSendKeys(By locator, CharSequence... value) {
		nullCheck(value);
		getElement(locator).clear();
		getElement(locator).sendKeys(value);
	}

	private void nullCheck(CharSequence... value) {
		if (value == null) {
			throw new RuntimeException("=====Value can not be Null=====");
		}
	}

	@Step("Clicking og the element:{0}")
	public void doCLick(By locator) {
		getElement(locator).click();
	}

	public String doElementGetText(By locator) {
		String eleText = getElement(locator).getText();
		System.out.println("Element text is: " + eleText);
		return eleText;
	}

	public String getElementDomAttributeValue(By locator, String attributeName) {
		nullCheck(attributeName);
		return getElement(locator).getDomAttribute(attributeName);
	}

	public String getElementDomPropertyValue(By locator, String propertyName) {
		nullCheck(propertyName);
		return getElement(locator).getDomProperty(propertyName);
	}

	public boolean isElementDisplayed(By locator) {
		try {
			return getElement(locator).isDisplayed();
		} catch (NoSuchElementException e) {
			System.out.println("Element is not present on the page .." + locator);
			return false;
		}
	}

	@Step("Finding element by {0}")
	public WebElement getElement(By locator) {
		return driver.findElement(locator);
	}

	// **************findElements Utils******************* //

	public List<String> getElementsTextList(By locator) {
		List<WebElement> eleList = getElements(locator);
		List<String> eleTextList = new ArrayList<String>();
		for (WebElement e : eleList) {
			String text = e.getText();
			if (text.length() != 0) {
				System.out.println(text);
				eleTextList.add(text);
			}
		}
		return eleTextList;
	}

	public int getElementsSize(By locator) {
		int eleCount = getElements(locator).size();
		System.out.println("Elements count==>" + eleCount);
		return eleCount;
	}

	public boolean checkElementDisplayed(By locator) {
		if (getElements(locator).size() == 1) {
			System.out.println("element : " + locator + " is displayed on the page one time");
			return true;
		} else {
			return false;
		}
	}

	public boolean checkElementDisplayed(By locator, int expEleCount) {
		if (getElements(locator).size() == expEleCount) {
			System.out.println("element : " + locator + " is displayed on the page one time");
			return true;
		} else {
			return false;
		}
	}

	public void clickElement(By locator, String value) {
		List<WebElement> langELeList = getElements(locator);
		for (WebElement e : langELeList) {
			String text = e.getText();
			if (text.contains(value)) {
				e.click();
				break;
			}
		}
	}

	public List<WebElement> getElements(By locator) {
		return driver.findElements(locator);
	}

	// ***************Wiat Utils****************************//

	@Step("Waiting for the element: {0} for {1}")
	public WebElement waitForElementVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

	}

	public String waitFotTitleIs(String title, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
			wait.until(ExpectedConditions.titleIs(title));
			return driver.getTitle();
		} catch (TimeoutException e) {
			return null;
		}
	}

	public String waitForURLContains(String fractionURL, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
			wait.until(ExpectedConditions.urlContains(fractionURL));
			return driver.getCurrentUrl();
		} catch (TimeoutException e) {
			return null;
		}

	}
	
	public List<WebElement> waitForAllElementsVisible(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		try {
			return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		} catch (TimeoutException e) {
			return Collections.emptyList();
		}

	}

}
