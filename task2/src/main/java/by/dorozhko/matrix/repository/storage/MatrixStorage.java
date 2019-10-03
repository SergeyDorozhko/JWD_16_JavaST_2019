package by.dorozhko.matrix.repository.storage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public final class MatrixStorage {
    /**
     * single tone.
     */
    private static final MatrixStorage instance = new MatrixStorage();
    /**
     * matrix.
     */
    private int[][] matrix;
    /**
     * value of time to sleep.
     */
    private int timeToSleep = 50;
    /**
     * logger.
     */
    private final Logger logger = LogManager.getLogger(getClass().getName());
    /**
     * locker to synchronize.
     */
    private ReentrantLock locker;
    /**
     * semaphore to synchronize.
     */
    private Semaphore semaphore;

    private MatrixStorage() {
        locker = new ReentrantLock();
        semaphore = new Semaphore(1);
    }

    /**
     * create link to single object of matrix.
     * @return link.
     */
    public static MatrixStorage getInstance() {
        return instance;
    }

    /**
     * get matrix size.
     * @return size of matrix NxN if one is initialized, other case "0".
     */
    public int getSize() {
        if (matrix != null) {
            return matrix.length;
        }
        return 0;
    }

    /**
     * create matrix with size NxN.
     * @param size of matrix.
     */
    public void setSize(final int size) {
        matrix = new int[size][size];
    }

    /**
     * Get value of matrix element.
     * @param verticalPosition vertical position of element.
     * @param horizontalPosition horizontal position of element.
     * @return value of element.
     */
    public int getElement(final int verticalPosition,
                          final int horizontalPosition) {
        return matrix[verticalPosition][horizontalPosition];
    }

    /**
     * Set value of matrix element.
     * @param verticalPosition vertical position of element.
     * @param horizontalPosition horizontal position of element.
     * @param value value of element.
     */
    public void setElement(final int verticalPosition,
                           final int horizontalPosition,
                           final int value) {
        matrix[verticalPosition][horizontalPosition] = value;
    }

    /**
     * Set value of matrix element synchronized by Locker.
     * @param verticalPosition vertical position of element.
     * @param horizontalPosition horizontal position of element.
     * @param value value of element.
     */
    public void setElementByReentrantLock(final int verticalPosition,
                                          final int horizontalPosition,
                                          final int value) {
        logger.debug("start REENTRANTLOCK");
        locker.lock();
        try {
            if (matrix[verticalPosition][horizontalPosition] == 0) {
                logger.trace(horizontalPosition);

                matrix[verticalPosition][horizontalPosition] = value;

                TimeUnit.MILLISECONDS.sleep(timeToSleep);
            }

        } catch (InterruptedException e) {
            logger.error(Thread.currentThread().getName(), e);
            Thread.currentThread().interrupt();
        } finally {
            locker.unlock();
        }
    }

    /**
     * Set value of matrix element synchronized by semaphore.
     * @param verticalPosition vertical position of element.
     * @param horizontalPosition horizontal position of element.
     * @param value value of element.
     */
    public void setElementBySemaphore(final int verticalPosition,
                                      final int horizontalPosition,
                                      final int value) {
        logger.debug("start SEMAPHORE");
        try {
            semaphore.acquire();
            if (matrix[verticalPosition][horizontalPosition] == 0) {
                logger.trace(horizontalPosition);
                matrix[verticalPosition][horizontalPosition] = value;
                TimeUnit.MILLISECONDS.sleep(timeToSleep);
            }
            semaphore.release();
        } catch (InterruptedException e) {
            logger.error(e);
            Thread.currentThread().interrupt();
        }

    }

    /**
     * Set value of matrix element synchronized by TimeUnit.
     * @param verticalPosition vertical position of element.
     * @param horizontalPosition horizontal position of element.
     * @param value value of element.
     */
    public void setElementByTimeUnit(final int verticalPosition,
                                     final int horizontalPosition,
                                     final int value) {
        logger.debug("start TimeUnit");

        try {
            TimeUnit.MILLISECONDS.sleep(new Random().nextInt(timeToSleep));

            if (matrix[verticalPosition][horizontalPosition] == 0) {
                logger.trace(horizontalPosition);
                matrix[verticalPosition][horizontalPosition] = value;
                TimeUnit.MILLISECONDS.sleep(timeToSleep);
            }

        } catch (InterruptedException e) {
            logger.error(e);
            Thread.currentThread().interrupt();
        }
    }
}
