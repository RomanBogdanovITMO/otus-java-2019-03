package ru.otus;

public class CounterSynchronized1 extends Thread {
    private final static int limit = 10;
    private final Object monitor = new Object();


    public void inc(int count) {
        synchronized (monitor) {

            for (int i = 0; i < limit; i++) {
                count++;
                System.out.println(Thread.currentThread().getName() + " : " + count);
                try {
                    monitor.notify();
                    monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            for (int i = limit; i > 0; i--) {
                count--;
                System.out.println(Thread.currentThread().getName() + " : " + count);
                try {
                    monitor.notify();
                    if (count == 0) {
                        break;
                    }
                    monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
