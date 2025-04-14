package com.n26.challenge.pages;

import com.n26.challenge.utils.Constants;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;

public class MainPage {
    private AndroidDriver driver;
    private WebDriverWait wait;

    public MainPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = Constants.Locators.INCOME_BUTTON_ID)
    private WebElement addIncomeButton;

    @AndroidFindBy(id = Constants.Locators.EXPENSE_BUTTON_ID)
    private WebElement addExpenseButton;

    @AndroidFindBy(id = Constants.Locators.BALANCE_AMOUNT_ID)
    private WebElement balanceAmount;

    @AndroidFindBy(id = Constants.Locators.INCOME_AMOUNT_TEXT_ID)
    private WebElement incomeAmountText;

    @AndroidFindBy(id = Constants.Locators.EXPENSE_AMOUNT_TEXT_ID)
    private WebElement expenseAmountText;

    @AndroidFindBy(id = Constants.Locators.BALANCE_CONTAINER_ID)
    private WebElement balanceContainer;

    @AndroidFindBy(xpath = Constants.Locators.SNACKBAR_LAYOUT_XPATH)
    private WebElement snackbarLayout;

    @AndroidFindBy(xpath = Constants.Locators.SETTINGS_OVERLAY_XPATH)
    private WebElement settingsOverlay;

    @AndroidFindBy(id = Constants.Locators.MENU_SEARCH_ID)
    private WebElement searchButton;

    public void waitForSnackbarToDisappear() {
        try {
            // Wait for the snackbar disappear (20 seconds on emulator, 3 seconds on physical device)
            WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(20));
            longWait.until(ExpectedConditions.invisibilityOf(snackbarLayout));
        } catch (Exception e) {
            // Snackbar gone
        }
    }

    public void waitForSettingsOverlayToDisappear() {
        try {
            WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(20));
            longWait.until(ExpectedConditions.invisibilityOf(settingsOverlay));
        } catch (Exception e) {
        }
    }

    public void dismissSettingsOverlay() {
        try {
            // Check if the settings overlay popped up
            WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            shortWait.until(ExpectedConditions.visibilityOf(settingsOverlay));
            driver.executeScript("mobile: clickGesture", Map.of("x", 252, "y", 516));
            // Wait for the settings overlay disappear
            waitForSettingsOverlayToDisappear();
        } catch (Exception e) {
        }
    }

    public void dismissOverlays() {
        // Wait for the snackbar disappear
        waitForSnackbarToDisappear();
        // Dismiss the settings overlay
        dismissSettingsOverlay();
    }

    public void clickAddIncome() {
        wait.until(ExpectedConditions.visibilityOf(balanceAmount));
        WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(20));
        longWait.until(ExpectedConditions.elementToBeClickable(addIncomeButton)).click();
    }

    public void clickAddExpense() {
        wait.until(ExpectedConditions.visibilityOf(balanceAmount));
        WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(20));
        longWait.until(ExpectedConditions.elementToBeClickable(addExpenseButton)).click();
    }

    public void clickSearchButton() {
        wait.until(ExpectedConditions.elementToBeClickable(searchButton)).click();
    }

    public String getBalance() {
        WebElement element = wait.until(ExpectedConditions.visibilityOf(balanceAmount));
        String balanceText = element.getText(); // "Balance $3,500.00"
        // Remove "Balance " prefix and comma
        return balanceText.replace("Balance ", "").replace(",", "").trim();
    }

    public String getIncomeAmount() {
        WebElement element = wait.until(ExpectedConditions.visibilityOf(incomeAmountText));
        return element.getText().replace(",", "");
    }

    public String getExpenseAmount() {
        WebElement element = wait.until(ExpectedConditions.visibilityOf(expenseAmountText));
        return element.getText().replace(",", "");
    }
}