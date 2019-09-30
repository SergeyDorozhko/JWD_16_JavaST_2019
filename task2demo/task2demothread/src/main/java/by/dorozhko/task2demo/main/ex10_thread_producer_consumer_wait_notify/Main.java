package by.dorozhko.task2demo.main.ex10_thread_producer_consumer_wait_notify;

public class Main {
    public static void main(String[] args) {
        Store store = new Store();
        new Producer(store).start();
        new Consumer(store).start();
    }
}
