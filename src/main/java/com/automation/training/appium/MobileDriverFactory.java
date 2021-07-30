package com.automation.training.appium;

import static io.appium.java_client.remote.MobileCapabilityType.*;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.openqa.selenium.remote.CapabilityType.PLATFORM_NAME;

import com.google.common.collect.ImmutableMap;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

public class MobileDriverFactory {

	public AppiumDriver<?> getDriver() {
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream("src/test/resources/appium.config"));

			DesiredCapabilities cap = new DesiredCapabilities();
			URL remoteServer = new URL(properties.getProperty("remoteServer"));

			cap.setCapability(BROWSER_NAME, properties.getProperty("browserName"));
			cap.setCapability(DEVICE_NAME, properties.getProperty("deviceName"));
			cap.setCapability(PLATFORM_NAME, properties.getProperty("platformName"));
			cap.setCapability(PLATFORM_VERSION, properties.getProperty("platformVersion"));
			cap.setCapability(NEW_COMMAND_TIMEOUT, properties.getProperty("commandTimeout"));
			cap.setCapability(AUTOMATION_NAME, properties.getProperty("automationName"));

			AppiumDriver<?> driver = MobileDriver.valueOf(properties.getProperty("platformName").toUpperCase()).setDriver(remoteServer, cap, properties);
			driver.manage().timeouts().implicitlyWait(1, SECONDS);
			return driver;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public enum MobileDriver {

		ANDROID {
			@Override
			public AndroidDriver<AndroidElement> setDriver(URL remoteServer, DesiredCapabilities cap, Properties properties) {
				cap.setCapability("chromeOptions", ImmutableMap.of("w3c", false));
				return new AndroidDriver<>(remoteServer, cap);
			}
		},
		
		IOS {
			@Override
			public IOSDriver<IOSElement> setDriver(URL remoteServer, DesiredCapabilities cap,
					Properties properties) {
				cap.setCapability("appium:absoluteWebLocations", true);
				return new IOSDriver<>(remoteServer, cap);
			}
		};

		public abstract AppiumDriver<?> setDriver(URL remoteServer, DesiredCapabilities cap,
				Properties properties);
	}

}