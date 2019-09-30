package by.dorozhko.task2demo.main.ex19_executorservice;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CalcRunner {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(5);
        List<Future<Number>> futureList = new ArrayList<>();


        for (int i = 0; i < 5; i++) {
            Future<Number> future = es.submit(new CalcCallable());
            futureList.add(future);
        }
        es.shutdown();
        try {
            for(Future<Number> future : futureList) {
                System.out.println(future.get() + " result");
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
