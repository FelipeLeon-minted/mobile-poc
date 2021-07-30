package com.automation.training.pageobject;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;


public class BasePage<T extends AppiumDriver>{

	private final T driver;
	private int defaultWait;

	public BasePage(AppiumDriver<?> driver) {
		this.driver = (T) driver;
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream("src/test/resources/appium.config"));
			defaultWait = Integer.valueOf(properties.getProperty("waitTimeout"));
		} catch (IOException e){
			e.printStackTrace();
		}
		initElements();
	}

	private void initElements() {
		PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
	}

	protected T getDriver() {
		return (T) driver;
	}

	public void dispose() {
		if (driver != null) {
			driver.quit();
		}
	}

	protected <K> FluentWait<K> waitOn(K object, int timeOutSeconds) {
		return new FluentWait<>(object).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class).withTimeout(Duration.ofSeconds(timeOutSeconds))
				.pollingEvery(Duration.ofSeconds(timeOutSeconds));
	}

	protected FluentWait<T> getWait() {
		return waitOn(getDriver(), defaultWait);
	}

}
