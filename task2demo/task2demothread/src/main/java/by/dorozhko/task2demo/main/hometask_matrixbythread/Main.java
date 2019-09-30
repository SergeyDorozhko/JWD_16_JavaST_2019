package by.dorozhko.task2demo.main.hometask_matrixbythread;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        MatrixStorage matrixStorage = MatrixStorage.getInstance();
        matrixStorage.setMatrixSize(20);

        System.out.println(matrixStorage);

        MatrixFillInThread fillInOne = new MatrixFillInThread(" One ");
        MatrixFillInThread fillInTwo = new MatrixFillInThread(" TWO ");
        MatrixFillInThread fillInThree = new MatrixFillInThread(" THREE ");

        fillInOne.start();
        fillInTwo.start();
        fillInThree.start();

        try {


            fillInOne.join();
            fillInTwo.join();
            fillInThree.join();
        } catch (InterruptedException e) {
            System.err.println(e);
        }

        System.out.println(matrixStorage);
    }

}
