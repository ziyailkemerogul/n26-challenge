package com.n26.challenge.tests;

import com.n26.challenge.pages.MainPage;
import com.n26.challenge.pages.TransactionPage;
import com.n26.challenge.pages.SearchPage;
import com.n26.challenge.utils.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class BalanceTest extends BaseTest {
    private MainPage mainPage;
    private TransactionPage transactionPage;
    private SearchPage searchPage;

    @BeforeEach
    public void setUp() {
        try {
            super.setUp();
        } catch (MalformedURLException e) {
            fail("Failed to set up the test due to a malformed URL: " + e.getMessage());
        }
        mainPage = new MainPage(driver);
        transactionPage = new TransactionPage(driver);
        searchPage = new SearchPage(driver);
    }

    @Test
    public void testBalanceAfterMultipleTransactions() {
        // Add income 500 Deposits
        mainPage.clickAddIncome();
        transactionPage.enterAmount("500");
        transactionPage.selectCategory(Constants.Categories.DEPOSITS);
        mainPage.dismissOverlays();
        assertEquals(Constants.Amounts.BALANCE_500, mainPage.getBalance(), "Balance should be $500.00 after adding 500 Deposits");
        assertEquals(Constants.Amounts.INCOME_500, mainPage.getIncomeAmount(), "Income should be $500.00");
        mainPage.clickAddIncome();

        // Add income 3000 Salary
        transactionPage.enterAmount("3000");
        transactionPage.selectCategory(Constants.Categories.SALARY);
        mainPage.dismissOverlays();
        assertEquals(Constants.Amounts.BALANCE_3500, mainPage.getBalance(), "Balance should be $3500.00 after adding 3000 Salary");
        assertEquals(Constants.Amounts.INCOME_3500, mainPage.getIncomeAmount(), "Income should be $3500.00");
        mainPage.clickAddIncome();

        // Add income with note 7000 Savings
        transactionPage.enterAmount(Constants.Amounts.AMOUNT_7000);
        transactionPage.setNote(Constants.Notes.GOLD);
        transactionPage.selectCategory(Constants.Categories.SAVINGS);
        mainPage.dismissOverlays();
        assertEquals(Constants.Amounts.BALANCE_10500, mainPage.getBalance(), "Balance should be $10500.00 after adding 7000 Savings");
        assertEquals(Constants.Amounts.INCOME_10500, mainPage.getIncomeAmount(), "Income should be $10500.00");
        mainPage.clickAddExpense();

        // Add expense with note 50 Food
        transactionPage.enterAmount("50");
        transactionPage.setNote(Constants.Notes.ORDER);
        transactionPage.selectCategory(Constants.Categories.FOOD);
        mainPage.dismissOverlays();
        assertEquals(Constants.Amounts.BALANCE_10450, mainPage.getBalance(), "Balance should be $10450.00 after adding 50 Food expense");
        assertEquals(Constants.Amounts.EXPENSE_50, mainPage.getExpenseAmount(), "Expense should be $50.00");
        mainPage.clickAddExpense();

        // Add expense 150 House
        transactionPage.enterAmount("150");
        transactionPage.selectCategory(Constants.Categories.HOUSE);
        mainPage.dismissOverlays();
        assertEquals(Constants.Amounts.BALANCE_10300, mainPage.getBalance(), "Balance should be $10300.00 after adding 150 House expense");
        assertEquals(Constants.Amounts.EXPENSE_200, mainPage.getExpenseAmount(), "Expense should be $200.00");
        mainPage.clickAddExpense();

        // Add expense 100 Bills
        transactionPage.enterAmount("100");
        transactionPage.selectCategory(Constants.Categories.BILLS);
        mainPage.dismissOverlays();
        assertEquals(Constants.Amounts.BALANCE_10200, mainPage.getBalance(), "Balance should be $10200.00 after adding 100 Bills expense");
        assertEquals(Constants.Amounts.EXPENSE_300, mainPage.getExpenseAmount(), "Expense should be $300.00");

        // Search for the "Gold" transaction and edit
        mainPage.clickSearchButton();
        searchPage.searchForTransaction(Constants.Notes.GOLD);
        searchPage.selectTransaction(Constants.Notes.GOLD);
        transactionPage.verifyAmount(Constants.Amounts.AMOUNT_7000);
        transactionPage.clearAmount(Constants.CLEAR_AMOUNT_CLICKS);
        transactionPage.enterAmount("8000");
        transactionPage.selectCategory(Constants.Categories.SAVINGS);
        // Verify the amount in the search results
        searchPage.verifyUpdatedAmount(Constants.Notes.GOLD, Constants.Amounts.AMOUNT_8000);
        searchPage.dismissSnackbar();
        searchPage.returnToMainScreen();
        mainPage.clickSearchButton();

        // Search for the "Order" transaction and delete
        searchPage.searchForTransaction(Constants.Notes.ORDER);
        searchPage.selectTransaction(Constants.Notes.ORDER);
        transactionPage.deleteTransaction();
        // Verify the search results are empty
        searchPage.verifyEmptyResults();
        searchPage.returnToMainScreen();
        mainPage.dismissOverlays();
        // Verify the final balance
        assertEquals(Constants.Amounts.BALANCE_11250, mainPage.getBalance(), "Balance should be $11250.00 after editing Gold to 8000 and deleting Order");
        assertEquals(Constants.Amounts.INCOME_11500, mainPage.getIncomeAmount(), "Income should be $11500.00 after editing Gold to 8000");
        assertEquals(Constants.Amounts.EXPENSE_250, mainPage.getExpenseAmount(), "Expense should be $250.00 after deleting Order");
    }
}