package ru.otus;

import java.util.logging.Logger;

public class Main {

    static Logger logger = Logger.getLogger(TestRunner.class.getName());

    public static void main(String[] args) {
        startTest("ru.otus.test.FirstTest");

    }

    public static void startTest(String className) {
        logger.info("Testing class: " + className);
        try {
            TestRunner.runTests(className);
        } catch (ClassNotFoundException e) {
            logger.warning("Error: Class not found: " + e.getMessage());
        }
    }
}
