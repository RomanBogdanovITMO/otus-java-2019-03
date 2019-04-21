package ru.otus;

import ru.otus.annotation.After;
import ru.otus.annotation.Before;
import ru.otus.annotation.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class TestRunner {
    public static void runTest(String className) throws ClassNotFoundException {

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
                System.out.println("Start test for the - " + className);
            } else {
                System.out.println("Test not found in the -" + className);
                return;
            }

            int countTest = 0;

            for (Method testMethod : testMethods) {
                try {
                    Object testObject = testClass.getDeclaredConstructor().newInstance();
                    countTest++;

                    for (Method method : beforeMethods) {
                        method.invoke(testObject);
                    }

                    testMethod.invoke(testObject);

                    for (Method method : afterMethods) {
                        method.invoke(testObject);
                    }
                } catch (Exception e) {
                    System.out.println("filed " + e);
                    countTest--;
                }
            }

        } catch (Throwable e) {
            System.out.println(e.getMessage());
        }
    }
}
