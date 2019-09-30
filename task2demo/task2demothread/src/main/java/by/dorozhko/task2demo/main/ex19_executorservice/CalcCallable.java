package by.dorozhko.task2demo.main.ex19_executorservice;

import java.util.Random;
import java.util.concurrent.Callable;

public class CalcCallable implements Callable<Number> {
    public Number call() throws Exception {
        Number res = new Random().nextGaussian(); // имитация вычислений
        return res;
    }
}
