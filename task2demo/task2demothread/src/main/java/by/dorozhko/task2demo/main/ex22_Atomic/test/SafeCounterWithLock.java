package by.dorozhko.task2demo.main.ex22_Atomic.test;

public class SafeCounterWithLock {
    private volatile int counter;

    public void increment(){
        counter++;
    }

    public int getCounter(){
        return counter;
    }
}
