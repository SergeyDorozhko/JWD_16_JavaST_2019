package by.dorozhko.task2demo.main.ex12_threadProducerConsumerReentrantLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Store {
    private int product = 0;
    ReentrantLock locker;
    Condition condition;


    Store() {
        locker = new ReentrantLock();
        condition = locker.newCondition();
    }

    public void put() {
        locker.lock();
        try {
            // пока на складе 3 товара, ждем освобождения места
            while (product >= 3)
                condition.await();

            product++;
            System.out.println("Производитель добавил 1 товар");
            System.out.println("Товаров на складе: " + product);
            // сигнализируем
            condition.signalAll();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            locker.unlock();
        }
    }



    public  void get() {
        locker.lock();
        try {
            // пока нет доступных товаров на складе, ожидаем
            while (product < 1)
                condition.await();

            product--;
            System.out.println("Покупатель купил 1 товар");
            System.out.println("Товаров на складе: " + product);

            // сигнализируем
            condition.signalAll();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        } finally {
            locker.unlock();
        }

    }

}
