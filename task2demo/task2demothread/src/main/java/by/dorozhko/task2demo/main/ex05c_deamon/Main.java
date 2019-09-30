package by.dorozhko.task2demo.main.ex05c_deamon;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main (String[] args) {

        System.out.println("START MAIN");
        SimpleThread usual = new SimpleThread();
        SimpleThread daemon = new SimpleThread();

        daemon.setDaemon(true);
        daemon.start();
        usual.start();

        try {
            TimeUnit.SECONDS.sleep(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("END MAIN");
    }
}
