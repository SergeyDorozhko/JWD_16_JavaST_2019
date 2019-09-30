package by.dorozhko.task2demo.main.ex12_threadProducerConsumerReentrantLock;

public class Producer extends Thread{

    Store store;

    Producer(Store store) {
        this.store = store;
    }

    public void run() {
        for (int i = 1; i < 6; i++) {
            store.put();
        }
    }

}
