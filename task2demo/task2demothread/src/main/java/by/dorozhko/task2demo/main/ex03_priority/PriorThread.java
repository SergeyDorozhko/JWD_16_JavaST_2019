package by.dorozhko.task2demo.main.ex03_priority;

import java.util.concurrent.TimeUnit;

public class PriorThread extends Thread {
    public PriorThread(String name) {
        super(name);
    }

    public void run () {
        for (int i = 0; i < 50; i++) {
            System.out.println(getName() + " " + i);
            try {
                TimeUnit.MILLISECONDS.sleep(10); // попробовать sleep(1),sleep(0),sleep(10)
            } catch (InterruptedException e) {
                System.err.print(e);
            }

        }
    }
}
