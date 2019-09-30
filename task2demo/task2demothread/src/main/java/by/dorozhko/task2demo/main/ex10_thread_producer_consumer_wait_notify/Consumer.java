package by.dorozhko.task2demo.main.ex10_thread_producer_consumer_wait_notify;

public class Consumer extends Thread {
    Store store;

    Consumer(Store store) {
        this.store = store;
    }

    public void run() {
        for (int i = 1; i < 6; i++) {
            store.get();
        }
    }

}
