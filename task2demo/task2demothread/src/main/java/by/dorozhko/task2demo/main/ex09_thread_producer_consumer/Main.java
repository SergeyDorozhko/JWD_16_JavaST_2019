package by.dorozhko.task2demo.main.ex09_thread_producer_consumer;

public class Main {
    public static void main(String[] args) {

        Store store = new Store();
        new Producer(store).start();
        new Customer(store).start();
    }

}
