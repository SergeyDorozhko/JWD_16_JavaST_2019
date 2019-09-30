package by.dorozhko.task2demo.main.ex12_threadProducerConsumerReentrantLock;

public class Main {
    public static void main(String[] args) {
        Store store = new Store();
        new Producer(store).start();
        new Consumer(store).start();
    }
}
