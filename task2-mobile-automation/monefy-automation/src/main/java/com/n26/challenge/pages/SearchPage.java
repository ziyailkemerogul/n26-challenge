package com.n26.challenge.pages;

import com.n26.challenge.utils.Constants;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchPage {
    private AndroidDriver driver;
    private WebDriverWait wait;

    public SearchPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = Constants.Locators.SEARCH_INPUT_FIELD_ID)
    private WebElement searchInputField;

    @AndroidFindBy(id = Constants.Locators.ACTION_BAR_TITLE_ID)
    private WebElement barTitle;

    @AndroidFindBy(id = Constants.Locators.ACTION_BAR_SUBTITLE_ID)
    private WebElement actionBarSubtitle;

    @AndroidFindBy(id = Constants.Locators.SEARCH_RESULTS_LIST_ID)
    private WebElement searchResultsList;

    @AndroidFindBy(id = Constants.Locators.NOTE_TEXT_VIEW_ID)
    private List<WebElement> noteTextViews;

    @AndroidFindBy(id = Constants.Locators.AMOUNT_TEXT_VIEW_ID)
    private List<WebElement> amountTextViews;

    @AndroidFindBy(id = Constants.Locators.EMPTY_RESULTS_IMAGE_ID)
    private WebElement emptyResultsImage;

    @AndroidFindBy(id = Constants.Locators.ACTION_MODE_CLOSE_BUTTON_ID)
    private WebElement backButton;

    @AndroidFindBy(xpath = Constants.Locators.SNACKBAR_LAYOUT_XPATH)
    private WebElement snackbarLayout;

    public void searchForTransaction(String searchTerm) {
        // Wait for the search field to be visible
        WebElement element = wait.until(ExpectedConditions.visibilityOf(searchInputField));
        element.clear();
        element.sendKeys(searchTerm);
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
        wait.until(ExpectedConditions.textToBePresentInElement(barTitle, Constants.Texts.SEARCH_RESULTS));
        wait.until(ExpectedConditions.textToBePresentInElement(actionBarSubtitle, searchTerm));
        wait.until(ExpectedConditions.visibilityOf(searchResultsList));
    }

    public void selectTransaction(String note) {
        wait.until(ExpectedConditions.visibilityOfAllElements(noteTextViews));
        for (WebElement noteElement : noteTextViews) {
            if (noteElement.getText().equals(note)) {
                wait.until(ExpectedConditions.elementToBeClickable(noteElement)).click();
                return;
            }
        }
        throw new IllegalArgumentException("Transaction with note '" + note + "' not found in search results");
    }

    public void verifyUpdatedAmount(String note, String expectedAmount) {
        wait.until(ExpectedConditions.visibilityOf(searchResultsList));
        wait.until(ExpectedConditions.visibilityOfAllElements(noteTextViews));
        wait.until(ExpectedConditions.visibilityOfAllElements(amountTextViews));
        for (int i = 0; i < noteTextViews.size(); i++) {
            if (noteTextViews.get(i).getText().equals(note)) {
                String actualAmount = amountTextViews.get(i).getText().replace(",", "");
                if (!actualAmount.equals(expectedAmount)) {
                    throw new AssertionError("Expected amount " + expectedAmount + " for note '" + note + "', but found " + actualAmount);
                }
                return;
            }
        }
        throw new IllegalArgumentException("Transaction with note '" + note + "' not found in search results after editing");
    }

    public void verifyEmptyResults() {
        wait.until(ExpectedConditions.textToBePresentInElement(actionBarSubtitle, Constants.Notes.ORDER));
        // Verify the empty results image visibility
        wait.until(ExpectedConditions.visibilityOf(emptyResultsImage));
        // Verify the search results list is empty
        try {
            wait.withTimeout(Duration.ofSeconds(2))
                .until(ExpectedConditions.visibilityOfAllElements(noteTextViews));
            if (!noteTextViews.isEmpty()) {
                throw new AssertionError("Search results list is not empty after deletion");
            }
        } catch (Exception e) {
            // Expected no results
        }
    }

    public void dismissSnackbar() {
        try {
            WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(20));
            longWait.until(ExpectedConditions.invisibilityOf(snackbarLayout));
        } catch (Exception e) {
        }
    }

    public void returnToMainScreen() {
        wait.until(ExpectedConditions.elementToBeClickable(backButton)).click();
    }
}