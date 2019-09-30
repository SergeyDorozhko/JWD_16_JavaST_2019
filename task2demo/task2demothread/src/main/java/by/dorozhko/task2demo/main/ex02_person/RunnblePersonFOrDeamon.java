package by.dorozhko.task2demo.main.ex02_person;

public class RunnblePersonFOrDeamon extends PersoneRunnable {

    public RunnblePersonFOrDeamon(String name) {
        super(name);
    }

    public void run() {
        super.run();
        for (int i = 0; i < 15; i++) {
            System.out.println(getName() + " from " + Thread.currentThread().getName() + " hello world-------" + i);
        }
    }
}
