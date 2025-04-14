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
import java.util.List;

public class TransactionPage {
    private AndroidDriver driver;
    private WebDriverWait wait;

    public TransactionPage(AndroidDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id = Constants.Locators.BUTTON_KEYBOARD_0_ID)
    private WebElement calculatorButton0;

    @AndroidFindBy(id = Constants.Locators.BUTTON_KEYBOARD_1_ID)
    private WebElement calculatorButton1;

    @AndroidFindBy(id = Constants.Locators.BUTTON_KEYBOARD_2_ID)
    private WebElement calculatorButton2;

    @AndroidFindBy(id = Constants.Locators.BUTTON_KEYBOARD_3_ID)
    private WebElement calculatorButton3;

    @AndroidFindBy(id = Constants.Locators.BUTTON_KEYBOARD_4_ID)
    private WebElement calculatorButton4;

    @AndroidFindBy(id = Constants.Locators.BUTTON_KEYBOARD_5_ID)
    private WebElement calculatorButton5;

    @AndroidFindBy(id = Constants.Locators.BUTTON_KEYBOARD_6_ID)
    private WebElement calculatorButton6;

    @AndroidFindBy(id = Constants.Locators.BUTTON_KEYBOARD_7_ID)
    private WebElement calculatorButton7;

    @AndroidFindBy(id = Constants.Locators.BUTTON_KEYBOARD_8_ID)
    private WebElement calculatorButton8;

    @AndroidFindBy(id = Constants.Locators.BUTTON_KEYBOARD_9_ID)
    private WebElement calculatorButton9;

    @AndroidFindBy(id = Constants.Locators.BUTTON_KEYBOARD_DOT_ID)
    private WebElement calculatorButtonDot;

    @AndroidFindBy(id = Constants.Locators.BUTTON_KEYBOARD_PLUS_ID)
    private WebElement calculatorButtonPlus;

    @AndroidFindBy(id = Constants.Locators.BUTTON_KEYBOARD_MINUS_ID)
    private WebElement calculatorButtonMinus;

    @AndroidFindBy(id = Constants.Locators.BUTTON_KEYBOARD_MULTIPLY_ID)
    private WebElement calculatorButtonMultiply;

    @AndroidFindBy(id = Constants.Locators.BUTTON_KEYBOARD_DIVIDE_ID)
    private WebElement calculatorButtonDivide;

    @AndroidFindBy(id = Constants.Locators.BUTTON_KEYBOARD_EQUALS_ID)
    private WebElement calculatorButtonEquals;

    @AndroidFindBy(id = Constants.Locators.KEYBOARD_ACTION_BUTTON_ID)
    private WebElement chooseCategoryButton;

    @AndroidFindBy(id = Constants.Locators.TEXT_CATEGORY_NAME_ID)
    private List<WebElement> categoryNames;

    @AndroidFindBy(id = Constants.Locators.TEXT_VIEW_NOTE_ID)
    private WebElement noteField;

    @AndroidFindBy(id = Constants.Locators.AMOUNT_TEXT_ID)
    private WebElement amountField;

    @AndroidFindBy(id = Constants.Locators.BUTTON_KEYBOARD_CLEAR_ID)
    private WebElement clearButton;

    @AndroidFindBy(id = Constants.Locators.DELETE_BUTTON_ID)
    private WebElement deleteButton;

    public void clickCalculatorButton0() {
        wait.until(ExpectedConditions.elementToBeClickable(calculatorButton0)).click();
    }

    public void clickChooseCategory() {
        wait.until(ExpectedConditions.elementToBeClickable(chooseCategoryButton)).click();
    }

    public void verifyAmount(String expectedAmount) {
        WebElement element = wait.until(ExpectedConditions.visibilityOf(amountField));
        String actualAmount = element.getText().replace(",", "");
        if (!actualAmount.equals(expectedAmount)) {
            throw new AssertionError("Expected amount " + expectedAmount + ", but found " + actualAmount);
        }
    }

    public void clearAmount(int numberOfClicks) {
        for (int i = 0; i < numberOfClicks; i++) {
            wait.until(ExpectedConditions.elementToBeClickable(clearButton)).click();
        }
        // Amount field is cleared
        wait.until(ExpectedConditions.textToBePresentInElement(amountField, Constants.Amounts.DEFAULT_ZERO));
    }

    public void enterAmount(String amount) {
        wait.until(ExpectedConditions.elementToBeClickable(calculatorButton0));

        for (char character : amount.toCharArray()) {
            switch (character) {
                case '0':
                    wait.until(ExpectedConditions.elementToBeClickable(calculatorButton0)).click();
                    break;
                case '1':
                    wait.until(ExpectedConditions.elementToBeClickable(calculatorButton1)).click();
                    break;
                case '2':
                    wait.until(ExpectedConditions.elementToBeClickable(calculatorButton2)).click();
                    break;
                case '3':
                    wait.until(ExpectedConditions.elementToBeClickable(calculatorButton3)).click();
                    break;
                case '4':
                    wait.until(ExpectedConditions.elementToBeClickable(calculatorButton4)).click();
                    break;
                case '5':
                    wait.until(ExpectedConditions.elementToBeClickable(calculatorButton5)).click();
                    break;
                case '6':
                    wait.until(ExpectedConditions.elementToBeClickable(calculatorButton6)).click();
                    break;
                case '7':
                    wait.until(ExpectedConditions.elementToBeClickable(calculatorButton7)).click();
                    break;
                case '8':
                    wait.until(ExpectedConditions.elementToBeClickable(calculatorButton8)).click();
                    break;
                case '9':
                    wait.until(ExpectedConditions.elementToBeClickable(calculatorButton9)).click();
                    break;
                case '.':
                    wait.until(ExpectedConditions.elementToBeClickable(calculatorButtonDot)).click();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid character in amount: " + character);
            }
        }
    }

    public void setNote(String note) {
        WebElement element = wait.until(ExpectedConditions.visibilityOf(noteField));
        element.clear();
        element.sendKeys(note);
    }

    public void selectCategory(String category) {
        // Click the "Choose Category" or "Change Category" button
        clickChooseCategory();

        wait.until(ExpectedConditions.visibilityOfAllElements(categoryNames));

        // Find and click the category matches
        for (WebElement categoryElement : categoryNames) {
            if (categoryElement.getText().equals(category)) {
                wait.until(ExpectedConditions.elementToBeClickable(categoryElement)).click();
                return;
            }
        }
        throw new IllegalArgumentException("Category not found: " + category);
    }

    public void deleteTransaction() {
        wait.until(ExpectedConditions.elementToBeClickable(deleteButton)).click();
    }
}