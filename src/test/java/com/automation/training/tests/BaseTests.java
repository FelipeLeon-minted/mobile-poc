package com.automation.training.tests;

import com.automation.training.pageobject.BasePage;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.automation.training.appium.MobileDriverFactory;

import io.appium.java_client.AppiumDriver;

public class BaseTests {

	private BasePage basePage;
	private AppiumDriver<?> driver;

	@BeforeSuite(alwaysRun=true)
	public void beforeSuite() {
		MobileDriverFactory mobileDriverFactory = new MobileDriverFactory();
		driver = mobileDriverFactory.getDriver();
	}

	@AfterSuite(alwaysRun=true)
	public void afterSuite() {
		driver.quit();
	}

	public AppiumDriver<?> getDriver() {
		return driver;
	}
}
