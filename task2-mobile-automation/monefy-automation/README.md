# Monefy Android App Test Automation

This project contains automated end-to-end (E2E) tests for the Monefy Android application, for the Task 2.

## Chosen E2E User Flows
Based on exploratory testing of the Monefy app, the following critical user flows were selected for automation:
1. **Adding Income**: Adding an income transaction and verifying the updated balance.
2. **Adding Expense**: Adding an expense transaction and verifying the updated balance.
3. **Managing Transactions**: Adding multiple transactions (some of them with note), searching transactions via entered notes, editing an income transaction, deleting an expense transaction, and verifying the final balance.

These flows cover core functionalities of money management: adding income/expenses, managing transactions (edit and delete) and the main searching functionality ensuring the app’s core features are tested.

## Setup
### Prerequisites (Local)
1. **Install Dependencies**:
   - Java 17
   - Maven
   - Docker
   - Android SDK (with emulator `Pixel_6_API_34` or equivalent)
   - Appium (`npm install -g appium`)
   - Appium UIAutomator2 driver (`appium driver install uiautomator2`)

2. **Start the Emulator**:
   ```bash
   emulator -avd Pixel_6_API_34

3. **Disable Animations (to improve test execution speed)**:
    - adb shell settings put global window_animation_scale 0
    - adb shell settings put global transition_animation_scale 0
    - adb shell settings put global animator_duration_scale 0

4. **Start the Appium Server**:
    - appium

5. **Running with Docker**:
    - Update Appium Server URL: In "src/test/java/com/n26/challenge/utils/Constants.java", set "APPIUM_SERVER_URL" to your local IP (mine: "http://192.168.2.134:4723").
    - Build the Docker Image: docker build -t monefy-automation .
    - Run the Tests in Docker: docker run --network host monefy-automation

6. **Running Locally**:
    - Install Project Dependencies: mvn clean install
    - Run the Tests: mvn test

## Test Execution Report
After running the tests (via Docker or locally), the report is available in target/surefire-reports/. It includes:
    - Test results (pass/fail)
    - Execution time (~468 seconds with animations disabled)
    - Detailed logs for debugging

## Tech Stack and Approach
### Tech Stack:
    - Appium: For Android automation, chosen for its cross-platform support and alignment with N26 tech stack.
    - JUnit 5: For test execution and assertions and for clear test structure.
    - Maven: For dependency management and test execution, for a standard build process.
    - Page Object Model (POM): For organizing test code, with pages like MainPage, TransactionPage, SearchPage, and WelcomeScreenPage.
    - Docker: For containerization, ensuring consistent test execution environments.

### Approach:
    - Used POM to separate UI interactions (pages package) from test logic (tests package), enhancing maintainability.
    - Centralized locators and constants in Constants.java to reduce duplication and simplify updates.
    - Each test (IncomeTest, ExpenseTest, BalanceTest) is independent, using @BeforeEach to reset the app state for reliability.
    - Dockerized the solution to ensure consistent test runs across environments.

## Scalability and Maintainability
    - Scalability: New tests can be added by creating new test classes in the tests package, reusing existing page objects. The POM structure supports adding new pages.
    - Maintainability: Constants.java centralizes locators and values, making updates straightforward. POM reduces code duplication, and independent tests prevent interference.

## Challenges Faced
    - User Onboarding Overlays and Pop-ups: Handling the app’s onboarding screens and pop-ups (premium feature prompts) was time-consuming due to inconsistent behavior on the emulator.
    - Emulator Animation Slowness: Emulator animations slowed test execution significantly. Disabling animations via ADB commands was a quick workaround, reducing execution time from ~452 seconds (Docker overhead included).

## Potential Improvements
Due to time constraints, the following improvements couldn’t be implemented but I know that they add value:
    - Test Suites: While test suite classes (Income, Expense, Balance) were created, they couldn’t be executed with tags due to Maven Surefire plugin issues. Fixing this would allow selective test runs, improving efficiency.
    - Parallel Execution: Running tests in parallel could reduce execution time further (to ~150 seconds), leveraging JUnit 5’s parallel execution features.
    - Optimize Overlay Handling: Implement snackbar and settings overlay dismissal into a single utility method.
    - Reusable Test Data Setup: During implementation I faced emulator errors and lost time so I reverted back unfortunately.

Thanks for your time and review!