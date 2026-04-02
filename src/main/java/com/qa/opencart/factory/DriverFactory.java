package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.exceptions.BrowserException;
import com.qa.opencart.exceptions.FrameworkException;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	OptionsManager optionsManager;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	/**
	 * it will launch the browser basis on given browser name
	 * 
	 * @param browserName
	 * @return
	 */
	public WebDriver initDriver(Properties prop) {
		String browserName = prop.getProperty("browser");
		System.out.println("Browser Name: " + browserName);
		optionsManager = new OptionsManager(prop);
		switch (browserName.trim().toLowerCase()) {
		case "chrome":
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			// driver = new ChromeDriver(optionsManager.getChromeOptions());
			break;
		case "edge":
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			// driver = new EdgeDriver(optionsManager.getEdgeOptions());
			break;
		case "firefox":
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			// driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			break;
		case "safari":
			tlDriver.set(new SafariDriver());
			// driver = new SafariDriver();
			break;
		default:
			System.out.println("Please pass correct browser name: " + browserName);
			throw new BrowserException("===invalid Browser====");
		}

		getDriver().get(prop.getProperty("url"));
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		return getDriver();
	}

	public static WebDriver getDriver() {
		return tlDriver.get();
	}

	/**
	 * To read the Properties file
	 * 
	 * @return
	 */
	public Properties getProperties() {

		String envName = System.getProperty("env");
		FileInputStream file = null;
		prop = new Properties();
		try {
			if (envName == null) {
				System.out.println("env is null, hence running the test on QA env by default..");
				file = new FileInputStream(
						"../opencart-selenium-automation/src/test/resource/config/qa.config.properties");
			} else {
				System.out.println("Running tests on env: " + envName);
				switch (envName.toLowerCase().trim()) {
				case "qa":
					file = new FileInputStream(
							"../opencart-selenium-automation/src/test/resource/config/qa.config.properties");
					break;
				case "dev":
					file = new FileInputStream(
							"../opencart-selenium-automation/src/test/resource/config/dev.config.properties");
					break;
				case "stage":
					file = new FileInputStream(
							"../opencart-selenium-automation/src/test/resource/config/stage.config.properties");
					break;
				case "prod":
					file = new FileInputStream(
							"../opencart-selenium-automation/src/test/resource/config/prod.config.properties");
					break;
				default:
					throw new FrameworkException("=====Invalid ENV Name======");
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			prop.load(file);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	/**
	 * Take screenshot
	 * @return screenshot file
	 */
	public static File getScreenshotFile() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);// temp dir
		return srcFile;
	}

	public static byte[] getScreenshotByte() {
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);// temp dir

	}

	public static String getScreenshotBase64() {
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);// temp dir

	}
}
