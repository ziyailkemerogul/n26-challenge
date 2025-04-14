package com.n26.challenge.tests;

import java.net.MalformedURLException;

public class InspectorTest extends BaseTest {
    public static void main(String[] args) {
        InspectorTest inspectorTest = new InspectorTest();
        try {
            inspectorTest.setUp();
            System.out.println("Appium session started. Use Appium Inspector to inspect the app.");
            System.out.println("Press Ctrl+C to stop the session.");
            Thread.sleep(Long.MAX_VALUE);
        } catch (MalformedURLException e) {
            System.err.println("Failed to start Appium session: " + e.getMessage());
            e.printStackTrace();
        } catch (InterruptedException e) {
            System.out.println("Session interrupted. Shutting down...");
        } finally {
            inspectorTest.tearDown();
        }
    }
}
