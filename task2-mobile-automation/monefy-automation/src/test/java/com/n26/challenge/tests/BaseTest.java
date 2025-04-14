package com.n26.challenge.tests;

import com.n26.challenge.utils.Constants;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class BaseTest {
    protected AndroidDriver driver;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        // Set capabilities
        UiAutomator2Options options = new UiAutomator2Options()
            .setPlatformName(Constants.PLATFORM_NAME)
            .setDeviceName(Constants.DEVICE_NAME)
            .setPlatformVersion(Constants.PLATFORM_VERSION)
            .setAutomationName(Constants.AUTOMATION_NAME)
            .setAppPackage(Constants.APP_PACKAGE)
            .setAppActivity(Constants.APP_ACTIVITY)
            .setNewCommandTimeout(Duration.ofSeconds(0));

        // Log capabilities
        System.out.println("Appium Capabilities: " + options.toJson());

        // Appium server URL
        URL appiumServerURL = new URL(Constants.APPIUM_SERVER_URL);

        // Initialize the driver
        driver = new AndroidDriver(appiumServerURL, options);

        // Log the session ID for debugging
        System.out.println("Appium Session ID: " + driver.getSessionId());

        // Pass the onboarding screen
        for (int i = 0; i < 3; i++) {
            try {
                driver.findElement(By.id(Constants.Locators.BUTTON_CONTINUE_ID)).click();
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Close the premium screen
        try {
            driver.findElement(By.id(Constants.Locators.BUTTON_CLOSE_ID)).click();
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public AndroidDriver getDriver() {
        return driver;
    }
}