package com.n26.challenge.tests;

import com.n26.challenge.pages.MainPage;
import com.n26.challenge.pages.TransactionPage;
import com.n26.challenge.utils.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class IncomeTest extends BaseTest {
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
    public void testAddIncome() {
        mainPage.clickAddIncome();
        transactionPage.enterAmount("100");
        transactionPage.selectCategory(Constants.Categories.SALARY);
        mainPage.dismissOverlays();
        assertEquals(Constants.Amounts.INCOME_100, mainPage.getIncomeAmount(), "Income should be $100.00");
        assertEquals(Constants.Amounts.BALANCE_100, mainPage.getBalance(), "Balance should be $100.00 after adding income");
    }
}