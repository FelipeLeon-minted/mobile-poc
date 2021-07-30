package com.automation.training.pages;

import com.automation.training.pageobject.BasePage;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	@FindBy(css = "[data-localize='favorite-tools']")
	WebElement freeSampleButton;

	private static final String pageUrl = "https://appium.io/";

	public HomePage(AppiumDriver<?> driver) {
		super(driver);
	}

	public void goToHome(){
		getDriver().get(pageUrl);
	}

	public void getFreeSampleButtonLocation(){
		Point location = freeSampleButton.getRect().getPoint();
		System.out.println(location);;
	}

}
