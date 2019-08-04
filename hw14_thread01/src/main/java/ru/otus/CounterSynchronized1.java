package ru.otus;

public class CounterSynchronized1 extends Thread {
    private final static int limit = 10;
    private final Object monitor = new Object();


    public void inc(int count) {
        synchronized (monitor) {
            try {
                for (int i = 0; i < limit; i++) {
                    monitor.notify();
                    count++;
                    System.out.println(Thread.currentThread().getName() + " : " + count);
                    monitor.wait();
                }
                for (int i = limit; i > 0; i--) {
                    monitor.notify();
                    count--;
                    System.out.println(Thread.currentThread().getName() + " : " + count);
                    if (count == 0) {
                        break;
                    }
                    monitor.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
