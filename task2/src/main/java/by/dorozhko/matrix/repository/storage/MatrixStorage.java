package by.dorozhko.matrix.repository.storage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class MatrixStorage {
    private static final MatrixStorage instance = new MatrixStorage();

    private int[][] matrix;

    private final Logger logger = LogManager.getLogger(getClass().getName());

    private ReentrantLock locker;
    private Semaphore semaphore;
    private Phaser phaser;


    private MatrixStorage() {
        locker = new ReentrantLock();
        semaphore = new Semaphore(1);
        phaser = new Phaser();
    }

    public static MatrixStorage getInstance() {
        return instance;
    }

    public int getSize() {
        if (matrix != null) {
            return matrix.length;
        }
        return 0;
    }

    public void setSize(int size) {
        matrix = new int[size][size];
    }

    public int getElement(int verticalPosition, int horizontalPosition) {
        return matrix[verticalPosition][horizontalPosition];
    }

    public void setElement(int verticalPosition, int horizontalPosition, int value) {
        matrix[verticalPosition][horizontalPosition] = value;
    }


    public void setElementByReentrantLock(int verticalPosition, int horizontalPosition, int value) {
        logger.debug("start REENTRANTLOCK");
        locker.lock();
        try {
            if (matrix[verticalPosition][horizontalPosition] == 0) {
                logger.trace(horizontalPosition);

                matrix[verticalPosition][horizontalPosition] = value;

                TimeUnit.MILLISECONDS.sleep(200);
            }

        } catch (InterruptedException e) {
            logger.error(Thread.currentThread().getName(), e);
            Thread.currentThread().interrupt();
        } finally {
            locker.unlock();
        }
    }

    public void setElementBySemaphore(int verticalPosition, int horizontalPosition, int value) {
        logger.debug("start SEMAPHORE");
        try {
            semaphore.acquire();
            if (matrix[verticalPosition][horizontalPosition] == 0) {
                logger.trace(horizontalPosition);
                matrix[verticalPosition][horizontalPosition] = value;
                TimeUnit.MILLISECONDS.sleep(200);
            }
            semaphore.release();
        } catch (InterruptedException e) {
            logger.error(e);
            Thread.currentThread().interrupt();
        }

    }

    public void setElementByPhase(int verticalPosition, int horizontalPosition, int value) {
        logger.debug("start PHASE");
        phaser.register();

        try {
            TimeUnit.MILLISECONDS.sleep(new Random().nextInt(50));

            if (matrix[verticalPosition][horizontalPosition] == 0) {
                logger.trace(horizontalPosition);
                matrix[verticalPosition][horizontalPosition] = value;
                TimeUnit.MILLISECONDS.sleep(100);
            }
            phaser.arrive();

        } catch (InterruptedException e) {
            logger.error(e);
            Thread.currentThread().interrupt();
        }
    }
}
