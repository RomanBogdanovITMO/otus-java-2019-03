package ru.otus;

public class Main {
    public static void main(String[] args) {
        startTest("ru.otus.test.FirstTest");

    }
    public static void startTest(String className){
        System.out.println("working class: " + className);
        try {
            TestRunner.runTest(className);
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
            e.printStackTrace();
        }
    }
}
