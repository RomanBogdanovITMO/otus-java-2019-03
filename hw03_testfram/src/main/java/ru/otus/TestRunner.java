package ru.otus;

import ru.otus.annotation.After;
import ru.otus.annotation.Before;
import ru.otus.annotation.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.logging.Logger;

public class TestRunner {

    static Logger logger = Logger.getLogger(TestRunner.class.getName());

    public static void runTests(final String className) throws ClassNotFoundException {
        Class<?> testClass = Class.forName(className);
        try {
            Method[] methods = testClass.getDeclaredMethods();

            ArrayList<Method> beforeMethods = new ArrayList<>();
            ArrayList<Method> testMethods = new ArrayList<>();
            ArrayList<Method> afterMethods = new ArrayList<>();

            for (Method method : methods) {
                Annotation[] annotations = method.getDeclaredAnnotations();
                for (Annotation annotation : annotations) {
                    if (annotation instanceof Before) {
                        beforeMethods.add(method);
                    } else if (annotation instanceof Test) {
                        testMethods.add(method);
                    } else if (annotation instanceof After) {
                        afterMethods.add(method);
                    }
                }
            }

            if (!testMethods.isEmpty()) {
                logger.info("Start tests for the class: " + className);
            } else {
                logger.info(" tests not found in the class: " + className);
                return;
            }


            for (Method testMethod : testMethods) {
                try {
                    Object testObject = testClass.getDeclaredConstructor().newInstance();

                    for (Method method : beforeMethods) {
                        method.invoke(testObject);
                    }

                    testMethod.invoke(testObject);

                    for (Method method : afterMethods) {
                        method.invoke(testObject);
                    }
                } catch (Exception e) {
                    logger.warning("test failed: " + e.getCause().getMessage());
                }
            }


        } catch (Throwable e) {
            logger.warning(e.getMessage());
        }

    }
}
