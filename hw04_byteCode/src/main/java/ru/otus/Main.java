package ru.otus;

public class Main {
    public static void main(String[] args) {
        MyinterfacClass myinterfacClass = MyAop.creatMyClass();
        myinterfacClass.print("Hello world");
    }
}
