package ru.otus;

public class CounterSynchronized {
    private int count = 0;
    private int count2 = 0;
    private final static int limit = 10;
    private final Object monitor = new Object();


    public static void main(String[] args) throws InterruptedException {
        CounterSynchronized counter = new CounterSynchronized();
        counter.go();
    }
    private void inc() {
        synchronized (monitor) {
            for (int i = 0; i < limit; i++) {
                count++;
                System.out.println("count: " + count);

                try {
                    Thread.sleep(1000);
                    monitor.notify();
                    monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i = limit; i > 0; i--) {
                count--;
                System.out.println("count: " + count);

                try {
                    Thread.sleep(1000);
                    monitor.notify();
                    monitor.wait();
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private void inc1() {
        synchronized (monitor) {
            for (int i = 0; i < limit; i++) {
                count2++;
                System.out.println("count2: " + count2);
                try {
                    Thread.sleep(1000);
                    monitor.notify();
                    monitor.wait();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            for (int i = limit; i > 0; i--) {
                count2--;
               System.out.println("count2: " + count2);

                try {
                    Thread.sleep(1000);
                    monitor.notify();
                    if (count2 == 0){
                        break;
                    }
                    monitor.wait();

                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private void go() throws InterruptedException {
        Thread thread1 = new Thread(this::inc);
        Thread thread2 = new Thread(this::inc1);


        thread1.start();
        thread2.start();


        thread1.join();
        thread2.join();


    }
}
