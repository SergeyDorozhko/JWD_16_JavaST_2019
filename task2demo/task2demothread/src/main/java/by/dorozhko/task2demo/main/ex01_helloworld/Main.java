package by.dorozhko.task2demo.main.ex01_helloworld;

public class Main {
    public static void main(String[] args) {
        Thread.currentThread().setName("tefdfdfgdgfhjf");
        MyThreadHello t = new MyThreadHello();
        t.setName("first main");
        t.start();


        System.out.println(Thread.currentThread());
    }
}
