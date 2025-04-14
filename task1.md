# Exploratory Testing Session: Monefy App (iOS)

## Testing Overview
- **Platform**: iOS 18.3.2 on iPhone 14 Pro (Physical Device).
- **App Version**: Monefy 1.9.0 (Free Version).
- **Time Spent**: Approximately 1 hour testing, 1 hour documenting.
- **Approach**:  Structured exploratory testing with charters targeting core functionality, usability, edge cases, persistence, performance, and security, prioritized by user impact and risk. Focused on user-critical features (transactions, pie chart accuracy) and non-functional aspects (offline mode, app state transitions) to ensure reliability in a money management app.
- **Note**: All premium features have been ignored while testing.

## Charters

### Charter 1: Core Transaction Functionality
- **Goal**: Ensure transaction CRUD (Create, Read, Update, Delete) works reliably.
- **Priority**: High - Core feature critical to money management app; any failure blocks app purpose.
- **Steps**:
  1. Add expense: "5.99" (Food) -> Check balance (-5.99) on pie chart.
  2. Test locale: "5,99" (with comma) vs. "5.99" (decimal). No comma option.
  3. Add income: "100" (Salary) -> Check balance (94.01) on pie chart.
  4. Add "0.01" income -> Check display ("0.01" added, "100.01" total).
  5. Verify the balance amount on main screen displays decimal amounts.
  6. View list -> Tap on (Balance), scroll and verify no lags.
  7. On the list, tap on (Food), tap on the expense. Delete "5.99" expense -> Verify balance ("100.01").
  8. Fast add/delete test: Add/delete 5x, watch for expense counts under categories, crashes or any inconsistencies.
  9. Fast add/delete undo test: Add/delete then undo right away and watch for expense counts under categories, for crashes or any inconsistencies.
  10. Expense date update test: Update expense date (different than today), sort the list and observe the date grouping.
  11. Expense date update undo test: Update expense date, observe, then undo right away and watch for crashes or any inconsistencies.
  12. List sorting and grouping test: Amount sorting: Grouped by categories (incomes first, highest amount category first), amounts under categories; newest first (not the highest), sub-category dates in descending order. Calendar sorting: Grouped by date in ascending order. For each day, incomes first (in descending order), then expenses (in descending order.)
- **Findings**:
  - Decimal amounts displayed correctly on main screen ("100.01").
  - Sorting by amount and date worked as expected, with incomes prioritized.
  - When I was on April view, I added an income for May. May tab was created in the slider menu. I deleted that income but May tab didn't go away. It didn't go back to its previous state until app restart.
- **Bugs/Issues**:
  - After each expense entry, pie chart category icons shift positions, causing confusion (Food icon moved from top to bottom after adding expense. I thought I couldn't enter it successfully, then I realized the position change issue.).**Severity**: Medium - May lead to confusions in data accuracy.
  - Persistent May tab after expense deletion: 
  **Severity**: Low - Minor UI issue, resolved on restart.

### Charter 2: Usability and Navigation
- **Goal**: Validate intuitive UI and responsiveness.
- **Priority**: Medium - Impacts user experience and adoption, less critical than functionality.
- **Steps**:
  1. Explore dashboard: Tap pie chart, switch Day/Week/Month/Year views from the left filter menu -> Verify smoothness.
  2. Add 5 expenses -> Verify pie chart update.
  3. Add expense with a note -> Test copy/paste/cut in note field.
  4. Settings menu -> Verify responsiveness, readability.
  5. Settings menu -> User should be able to adjust the key settings, such as; "Currency", "First day of week", "First day of month".
  6. Settings menu -> User should be able to select "Balance" options successfully, such as; "Budget mode", "Carry over", "Future recurring records".
  7. Settings menu -> Verify "Budget mode" rejects empty or <0 records.
  8. Categories menu -> Change one category name to custom category “Test” -> Use in transaction, verify functionality, previously entered expenses and reports.
  9. Search by category/amount -> Verify deleted transactions/categories don’t appear.
  10. Search -> User should be able to search by notes.
  11. Search -> User should be able to go into details from search results and make update/delete actions.
  12. Search -> When there is no results, "No records have been found" screen should be displayed.
  13. Search -> Search results should be updated successfully after every character type (starting from the second character). 
  14. Search -> Make sure that copy/paste/cut functions work properly for the search field.
- **Findings**:
  - Dashboard navigation smooth, view switches (Day/Week/Month/Year) responded within 1s.
  - Settings menu responsive, adjustments (Currency to EUR, USD, TRY) applied instantly.
  - Budget mode correctly rejected empty and <0 inputs.
- **Bugs**:
  - When expenses are added to all categories, the pie chart line to one category ("House", 6%) is missing, reducing visibility. Although there are less percentage categories exist.
  **Severity**: Low - Minor visual issue.

### Charter 3: Edge Cases and Data Validation
- **Goal**: Test robustness against invalid/extreme inputs.
- **Priority**: High - Stability ensures trust; edge cases might reveal critical bugs.
- **Steps**:
  1. Add expenses: “-10”, “0”, “999999999999” -> Min acceptable amount to enter is "0.01", max "999999999".
  2. Try to add text to the amount field verify that it's not possible. No paste function.
  3. Add 15 expenses (1.00 each) -> Check category limits, performance, pie chart.
  4. Add 10 expenses in 20 seconds -> Check accuracy, no duplicates, verify sorting.
- **Findings**:
  - Minimum acceptable amount is "0.01", maximum is "999999999".
  - Negative amounts ("-10") and zero ("0") rejected as expected.
  - Text input not possible in amount field, paste function disabled.
  - 15 expenses added successfully, no performance issues, pie chart updated (mentioned missing line in Charter 2).
  - 10 expenses in 20 seconds recorded accurately, no duplicates, sorting correct.
- **Bugs**: No bugs observed.

### Charter 4: Settings and Persistence
- **Goal**: Confirm settings and data retention and non-functional behavior.
- **Priority**: Medium - Ensures continuity, less urgent than core features.
- **Steps**:
  1. Settings > Currency > “EUR” -> Add “10” expense, check “€”.
  2. Add 5 expenses -> Close/reopen app -> Verify retention.
  3. Add 10 entries -> Force quit, reopen -> Verify data.
  4. Send app to background, then foreground -> Check state preservation.
  5. Turn on Airplane Mode -> Add expense -> Verify it saves.
  6. Uninstall/reinstall app -> Verify data loss (expected in free version).
- **Findings**:
  - Data persists after app restart and force quit; all entries retained.
  - No authentication required on reopen, as expected in free version.
  - Background/foreground transition preserved state (stayed on same view).
  - Offline mode (Airplane Mode) allowed expense addition, saved successfully.
  - Uninstall/reinstall resulted in data loss, as expected in free version.
- **Bugs**: No bugs observed.

### Charter 5: Performance and Security Scenarios
- **Goal**: Verify app performance under load and identify basic security risks.
- **Priority**: Medium-High - Performance impacts retention; security is critical for financial apps.
- **Steps**:
  1. Add 20 expenses -> Switch views fast -> Verify responsiveness.
  2. Add 50 total entries -> View/export data -> Check speed (<1s).
  3. Close/reopen app -> Check for authentication (expect none in free version).
  4. Create data backup -> Created .bak file successfully.
  5. Restore data -> Deleted some records then restored data successfully through the backup file selection.
  6. Clear data -> Cleared all the entered data successfully.
- **Findings**:
  - After 15 entries "..." appears on the pie chart notifying minor expenses grouped under "...".
  - During backup file creation file name entry could be asked to the user for meaningful data restore process and better UX. (It could be hard to remember a file name like: (monefy_backup-2025-04-07_02-05-58.bak)).
  - No noticeable battery drain or overheating observed during 50-entry test.
- **Bugs**: No bugs observed.

## Prioritization of Charters
1. **Charter 1: High** - Core transaction functionality is the app’s backbone; bugs here could make the app unusable.
2. **Charter 3: High** - Edge cases test stability, critical for a financial.
3. **Charter 5: Medium-High** - Performance and security are key for long-term reliability and user trust.
4. **Charter 2: Medium** - Usability affects satisfaction but isn’t a major issue unless it's functional.
5. **Charter 4: Medium** - Persistence ensures continuity, but free version limitations reduce urgency.

## Risks to Mitigate
- **Data Loss**: No auto backup in free version; risk of losing all entries on uninstall. Tested in Charter 4.
- **Calculation Errors**: Misreported balances could mislead users (none observed on Charter 1).
- **Security**: No passcode in free version; unauthorized access risk on shared/lost devices—highlighted in Charter 4.
- **Performance**: Slowdowns with many entries could frustrate users (none observed on Charter 5).
- **Input Handling**: Invalidated inputs (negatives) could corrupt data (none observed on Charter 3).
- **UI Inconsistency**: Shifting pie chart icons or persistent tabs after deletion may confuse users, reducing trust in reliability—tested in Charter 1.

## Key Takeaways
- Core functionality (transactions, data persistence) is stable, ensuring basic reliability.
- UI inconsistencies (shifting pie chart icons, persistent tabs, missing category lines) should be prioritized to improve user trust and experience.
- Backup/restore functionality, available in the free version, could be enhanced with better UX for file naming to improve data management.
