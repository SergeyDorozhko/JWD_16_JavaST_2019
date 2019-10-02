package by.dorozhko.task2demo.main.ex22_Atomic.test;

public class Main {
    public static void main(String[] args) {
        SafeCounterWithLock safeCounterWithLock = new SafeCounterWithLock();
        for (int i = 0; i <  10; i++){
        Thread t = new Thread(new MyThread("this is " + i, safeCounterWithLock));
        t.start();
        }

    }
}
