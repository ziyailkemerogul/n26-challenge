# N26 Home Challenge - Senior Quality Engineer

## Overview of the Challenge
N26 Home Challenge includes three mandatory tasks; Monefy app exploratory testing, mobile app automation, and RESTful API test automation.
1. **Task 1 - Exploratory Testing**: Performing an exploratory testing session on the Monefy mobile app (iOS) and document charters, findings, prioritization, and risks.
2. **Task 2 - Mobile App Test Automation**: Developing an automation framework for the Monefy Android app, covering three critical E2E user flows, with a focus on scalability, maintainability, and dockerization.
3. **Task 3 - REST API Test Automation**: Building an automation framework for the PET endpoint of the Swagger Petstore API, testing CRUD operations with 10 test cases (all passing), ensuring scalability, maintainability, and dockerization. The framework includes a retry mechanism for deletion tests to handle API delays and a TestNG report confirming successful execution.

**Note**: All three tasks have their own `README.md` files with detailed explanations, setup instructions, approach, tech stack and deliverables.

## Repository Structure
- `task1.md`: Task 1 deliverables.
- `task2-mobile-automation/`: Task 2 deliverables.
- `task3-api-automation/`: Task 3 deliverables.

## Tech Stack Overview
For the tasks, I used the following tools, which align with N26’s tech stack:
- **Exploratory Testing**: Manual testing on iOS physical device.
- **Mobile Automation**: Appium, Appium Inspector, JUnit 5, Maven, Page Object Model, Docker.
- **REST API Testing**: RestAssured, TestNG, Maven, Docker.

## Potential Improvements
I’m pleased with the submission, however there are areas where I could have done better, reflecting my awareness of best practices:
- **Task 1**: In general, using Jira for more detailed and clear bug reporting with a proper structure (steps to reproduce, version, environment details, screen recordings etc.) is one of my main "must have"s. Also, my expertise on a test management tool, TestRail, for creating, planning, and fully documenting projects from scratch in which would make the testing process clearer and better organized.
- **Task 2**: The test suite tagging (`Income`, `Expense`, etc.) didn’t work due to Maven Surefire plugin issues and due to the time constraints I had to skip this unfortunately. Fixing this would allow selective test runs, improving efficiency. Additionally, implementing parallel test execution could reduce the execution time significantly.
- **Task 3**: While negative test cases were included (invalid inputs, non-existent pets), adding more scenarios like special characters or large payloads would further improve robustness. Integrating Allure reporting would help visualize the retry mechanism’s impact in deletion tests.
- **Enhanced Reporting**: For both automation tasks, integrating a reporting tool like Allure could provide richer reports with screenshots and detailed logs, enhancing debugging capabilities.

These improvements were noted in the related README files, and I normally implement them, as they align with industry standards for quality engineering.

## What I Learned
This challenge was a highly informative experience for me, and I gained valuable insights:
- **Exploratory Testing**: I remembered my ability to structure testing charters and prioritize findings based on user impact, especially for a financial app where accuracy and reliability are critical. After working for more than 5 years in finance and banking projects, I once again noticed how structure changes and gets more crucial and stable compared to other sectors (due to high risks, etc.).
- **Mobile Automation**: Handling onboarding overlays and emulator slowness taught me the importance of optimizing test environments (disabling animations) and writing robust, maintainable tests using the Page Object Model.
- **REST API Testing**: Deepened my experience with RestAssured, focusing on modular test design with a separate API client for CRUD operations. I implemented a retry mechanism for deletion tests to handle API delays, ensuring reliable test execution.
- **Dockerization**: I recalled my skills in containerizing test frameworks, ensuring consistent execution across environments.

## Reflections
N26 Home Challenge was an informative and engaging experience for me. It made me think a lot and pushed me towards problem solving sessions more than once (which I really enjoy). It allowed me to apply my skills in exploratory testing, automation, and containerization. For Task 3, I’m happy with the dockerized REST API testing framework, which ensures consistent execution of all 10 test cases and includes a thoughtful retry mechanism for deletion tests. The tasks pushed me to think critically about scalability, maintainability, and user impact.

## Final Message
It was a very nice, challenging, and fun assignment for me! Hopefully, I will have the chance to prove the best of me as part of N26 and grow together.

Thanks for your time and review!

Best Regards,  
Ziya Ilkem Erogul