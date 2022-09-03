package ru.otus;

public class DemoCalculatorImpl implements Calculator {
    @log
    @Override
    public void getCalculator1(int number) {
        System.out.println(number);
    }
    @log
    @Override
    public void getCalculator2(int n) {
        System.out.println(n);
    }

    @Override
    public void getCalculator3(int s) {
        System.out.println("Your number: " + s);
    }


}
