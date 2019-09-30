package by.dorozhko.task2demo.main.ex01_helloworld;

public class MyThreadHello extends Thread {

    static  int count = 0;

    public void run() {
        System.out.println(Thread.currentThread() + " Hello world");

        count++;
        if(count < 10) {
            MyThreadHello t2 = new MyThreadHello();
            t2.start();

            MyThreadHello t3 = new MyThreadHello();
            t3.start();
        }
    }
}
