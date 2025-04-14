package com.n26.challenge.tests;

import com.n26.challenge.pages.MainPage;
import com.n26.challenge.pages.TransactionPage;
import com.n26.challenge.utils.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ExpenseTest extends BaseTest {
    private MainPage mainPage;
    private TransactionPage transactionPage;

    @BeforeEach
    public void setUp() {
        try {
            super.setUp();
        } catch (MalformedURLException e) {
            fail("Failed to set up the test due to a malformed URL: " + e.getMessage());
        }
        mainPage = new MainPage(driver);
        transactionPage = new TransactionPage(driver);
    }

    @Test
    public void testAddExpense() {
        mainPage.clickAddExpense();
        transactionPage.enterAmount("50");
        transactionPage.selectCategory(Constants.Categories.FOOD);
        mainPage.dismissOverlays();
        assertEquals(Constants.Amounts.EXPENSE_50, mainPage.getExpenseAmount(), "Expense should be $50.00");
        assertEquals(Constants.Amounts.BALANCE_NEGATIVE_50, mainPage.getBalance(), "Balance should be -$50.00 after adding expense");
    }
}