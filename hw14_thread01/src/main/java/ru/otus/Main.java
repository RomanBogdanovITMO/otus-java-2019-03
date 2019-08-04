package ru.otus;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        int count = 0;
        int count2 = 0;
        CounterSynchronized1 counterSynchronized1 = new CounterSynchronized1();

        Thread t1 = new Thread(() -> counterSynchronized1.inc(count));

        Thread t2 = new Thread(() -> counterSynchronized1.inc(count2));
        t1.start();
        t2.start();

        t1.join();
        t2.join();


    }
}
