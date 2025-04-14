package com.n26.challenge.utils;

public final class Constants {
    private Constants() {
    }

    // Appium Configuration
    public static final String PLATFORM_NAME = "Android";
    public static final String DEVICE_NAME = "Pixel_6_API_34";
    public static final String PLATFORM_VERSION = "14.0";
    public static final String AUTOMATION_NAME = "UiAutomator2";
    public static final String APP_PACKAGE = "com.monefy.app.lite";
    public static final String APP_ACTIVITY = "com.monefy.activities.main.MainActivity_";
    // public static final String APPIUM_SERVER_URL = "http://127.0.0.1:4723";
    public static final String APPIUM_SERVER_URL = "http://192.168.2.134:4723"; // Docker run verification with my local IP

    // Locators
    public static final class Locators {
        // MainPage
        public static final String INCOME_BUTTON_ID = "com.monefy.app.lite:id/income_button";
        public static final String EXPENSE_BUTTON_ID = "com.monefy.app.lite:id/expense_button";
        public static final String BALANCE_AMOUNT_ID = "com.monefy.app.lite:id/balance_amount";
        public static final String INCOME_AMOUNT_TEXT_ID = "com.monefy.app.lite:id/income_amount_text";
        public static final String EXPENSE_AMOUNT_TEXT_ID = "com.monefy.app.lite:id/expense_amount_text";
        public static final String BALANCE_CONTAINER_ID = "com.monefy.app.lite:id/balance_container";
        public static final String MENU_SEARCH_ID = "com.monefy.app.lite:id/menu_search";
        public static final String SNACKBAR_LAYOUT_XPATH = "//android.view.ViewGroup[@resource-id=\"com.monefy.app.lite:id/coordinator_layout\"]/android.widget.FrameLayout[2]";
        public static final String SETTINGS_OVERLAY_XPATH = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout";

        // SearchPage
        public static final String SEARCH_INPUT_FIELD_ID = "com.monefy.app.lite:id/et_search";
        public static final String ACTION_BAR_TITLE_ID = "com.monefy.app.lite:id/action_bar_title";
        public static final String ACTION_BAR_SUBTITLE_ID = "com.monefy.app.lite:id/action_bar_subtitle";
        public static final String SEARCH_RESULTS_LIST_ID = "com.monefy.app.lite:id/result_list_view";
        public static final String NOTE_TEXT_VIEW_ID = "com.monefy.app.lite:id/note_text_view";
        public static final String AMOUNT_TEXT_VIEW_ID = "com.monefy.app.lite:id/amount_text_view";
        public static final String EMPTY_RESULTS_IMAGE_ID = "com.monefy.app.lite:id/empty_results_image";
        public static final String ACTION_MODE_CLOSE_BUTTON_ID = "com.monefy.app.lite:id/action_mode_close_button";

        // TransactionPage
        public static final String BUTTON_KEYBOARD_0_ID = "com.monefy.app.lite:id/buttonKeyboard0";
        public static final String BUTTON_KEYBOARD_1_ID = "com.monefy.app.lite:id/buttonKeyboard1";
        public static final String BUTTON_KEYBOARD_2_ID = "com.monefy.app.lite:id/buttonKeyboard2";
        public static final String BUTTON_KEYBOARD_3_ID = "com.monefy.app.lite:id/buttonKeyboard3";
        public static final String BUTTON_KEYBOARD_4_ID = "com.monefy.app.lite:id/buttonKeyboard4";
        public static final String BUTTON_KEYBOARD_5_ID = "com.monefy.app.lite:id/buttonKeyboard5";
        public static final String BUTTON_KEYBOARD_6_ID = "com.monefy.app.lite:id/buttonKeyboard6";
        public static final String BUTTON_KEYBOARD_7_ID = "com.monefy.app.lite:id/buttonKeyboard7";
        public static final String BUTTON_KEYBOARD_8_ID = "com.monefy.app.lite:id/buttonKeyboard8";
        public static final String BUTTON_KEYBOARD_9_ID = "com.monefy.app.lite:id/buttonKeyboard9";
        public static final String BUTTON_KEYBOARD_DOT_ID = "com.monefy.app.lite:id/buttonKeyboardDot";
        public static final String BUTTON_KEYBOARD_PLUS_ID = "com.monefy.app.lite:id/buttonKeyboardPlus";
        public static final String BUTTON_KEYBOARD_MINUS_ID = "com.monefy.app.lite:id/buttonKeyboardMinus";
        public static final String BUTTON_KEYBOARD_MULTIPLY_ID = "com.monefy.app.lite:id/buttonKeyboardMultiply";
        public static final String BUTTON_KEYBOARD_DIVIDE_ID = "com.monefy.app.lite:id/buttonKeyboardDivide";
        public static final String BUTTON_KEYBOARD_EQUALS_ID = "com.monefy.app.lite:id/buttonKeyboardEquals";
        public static final String KEYBOARD_ACTION_BUTTON_ID = "com.monefy.app.lite:id/keyboard_action_button";
        public static final String TEXT_CATEGORY_NAME_ID = "com.monefy.app.lite:id/textCategoryName";
        public static final String TEXT_VIEW_NOTE_ID = "com.monefy.app.lite:id/textViewNote";
        public static final String AMOUNT_TEXT_ID = "com.monefy.app.lite:id/amount_text";
        public static final String BUTTON_KEYBOARD_CLEAR_ID = "com.monefy.app.lite:id/buttonKeyboardClear";
        public static final String DELETE_BUTTON_ID = "com.monefy.app.lite:id/delete";

        // WelcomeScreenPage
        public static final String TEXT_VIEW_TITLE_ID = "com.monefy.app.lite:id/textViewTitle";
        public static final String BUTTON_CONTINUE_ID = "com.monefy.app.lite:id/buttonContinue";
        public static final String BUTTON_CLOSE_ID = "com.monefy.app.lite:id/buttonClose";
    }

    // Categories (Income and Expense)
    public static final class Categories {
        public static final String DEPOSITS = "Deposits";
        public static final String SALARY = "Salary";
        public static final String SAVINGS = "Savings";
        public static final String FOOD = "Food";
        public static final String HOUSE = "House";
        public static final String BILLS = "Bills";
    }

    // Notes 
    public static final class Notes {
        public static final String GOLD = "Gold";
        public static final String ORDER = "Order";
    }

    // Expected Formatted Amounts for Assertions
    public static final class Amounts {
        public static final String BALANCE_0 = "$0.00"; // Initial balance
        public static final String BALANCE_100 = "$100.00"; // IncomeTest
        public static final String BALANCE_500 = "$500.00"; // BalanceTest
        public static final String BALANCE_3500 = "$3500.00"; // BalanceTest
        public static final String BALANCE_10500 = "$10500.00"; // BalanceTest
        public static final String BALANCE_10450 = "$10450.00"; // BalanceTest
        public static final String BALANCE_10300 = "$10300.00"; // BalanceTest
        public static final String BALANCE_10200 = "$10200.00"; // BalanceTest
        public static final String BALANCE_11250 = "$11250.00"; // BalanceTest
        public static final String BALANCE_11500 = "$11500.00"; // BalanceTest (income amount)
        public static final String BALANCE_NEGATIVE_50 = "-$50.00"; // ExpenseTest
        public static final String INCOME_100 = "$100.00"; // IncomeTest
        public static final String INCOME_500 = "$500.00"; // BalanceTest
        public static final String INCOME_3500 = "$3500.00"; // BalanceTest
        public static final String INCOME_10500 = "$10500.00"; // BalanceTest
        public static final String INCOME_11500 = "$11500.00"; // BalanceTest
        public static final String EXPENSE_50 = "$50.00"; // BalanceTest, ExpenseTest
        public static final String EXPENSE_150 = "$150.00"; // BalanceTest
        public static final String EXPENSE_100 = "$100.00"; // BalanceTest
        public static final String EXPENSE_200 = "$200.00"; // BalanceTest (cumulative)
        public static final String EXPENSE_250 = "$250.00"; // BalanceTest (after deletion)
        public static final String EXPENSE_300 = "$300.00"; // BalanceTest (before deletion)
        public static final String AMOUNT_7000 = "7000"; // BalanceTest
        public static final String AMOUNT_8000 = "$8000.00"; // BalanceTest
        public static final String DEFAULT_ZERO = "0"; // TransactionPage
    }

    // Others
    public static final class Texts {
        public static final String SEARCH_RESULTS = "Search results"; // SearchPage
    }

    // Test-Specific Constant
    public static final int CLEAR_AMOUNT_CLICKS = 4; // BalanceTest
}