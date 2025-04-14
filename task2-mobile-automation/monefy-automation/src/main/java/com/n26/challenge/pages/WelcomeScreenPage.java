package com.n26.challenge.pages;

import com.n26.challenge.utils.Constants;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class WelcomeScreenPage {
    private AndroidDriver driver;

    public WelcomeScreenPage(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = Constants.Locators.TEXT_VIEW_TITLE_ID)
    private WebElement title;

    @AndroidFindBy(id = Constants.Locators.BUTTON_CONTINUE_ID)
    private WebElement continueButton;

    @AndroidFindBy(id = Constants.Locators.BUTTON_CLOSE_ID)
    private WebElement closeButton;

    public String getTitle() {
        return title.getText();
    }

    public void clickContinue() {
        continueButton.click();
    }

    public void clickClose() {
        closeButton.click();
    }
}