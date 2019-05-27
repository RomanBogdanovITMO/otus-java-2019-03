package ru.otus;

public class Main {
    public static void main(String[] args) {
        startTest("ru.otus.test.FirstTest");

    }
    public static void startTest(String className){
        System.out.println("Testing class: " + className);
        try {
            TestRunner.runTests(className);
        } catch (ClassNotFoundException e){
            System.out.println("Error: Class not found");
            e.printStackTrace();
        }
    }
}
