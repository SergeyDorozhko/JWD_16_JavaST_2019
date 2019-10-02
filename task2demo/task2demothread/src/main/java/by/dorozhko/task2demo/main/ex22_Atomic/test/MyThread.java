package by.dorozhko.task2demo.main.ex22_Atomic.test;

import java.util.concurrent.TimeUnit;

public class MyThread extends Thread {
    SafeCounterWithLock safeCounterWithLock;

    MyThread(String name, SafeCounterWithLock safeCounterWithLock) {
        super(name);
        this.safeCounterWithLock = safeCounterWithLock;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            safeCounterWithLock.increment();
            System.out.println(Thread.currentThread().getName() + " " + safeCounterWithLock.getCounter());

        }
    }
}
