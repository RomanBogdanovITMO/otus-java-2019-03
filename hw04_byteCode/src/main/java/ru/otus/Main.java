package ru.otus;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = MyAop.creatMyClass();
        calculator.getCalculator1(8);
        calculator.getCalculator2(4);
        calculator.getCalculator3(4);

    }
}
