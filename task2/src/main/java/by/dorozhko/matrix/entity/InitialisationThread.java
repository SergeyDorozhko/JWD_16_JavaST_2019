package by.dorozhko.matrix.entity;

import by.dorozhko.matrix.repository.MatrixRepository;
import by.dorozhko.matrix.repository.RepositoryFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InitialisationThread extends Thread {
    /**
     * logger.
     */
    private final Logger logger = LogManager.getLogger(getClass().getName());
    /**
     * value of thread which will input to the min diagonal of matrix.
     */
    private int value;

    /**
     * param which chose the realisation of synchronization.
     */
    private String repositorySpecification;

    /**
     * public constructor with params.
     *
     * @param newValue                   value of thread which will input to
     *                                   the min diagonal of matrix.
     * @param newRepositorySpecification param which chose
     *                                   the realisation of synchronization.
     */
    public InitialisationThread(final int newValue,
                                final String newRepositorySpecification) {
        value = newValue;
        repositorySpecification = newRepositorySpecification;
    }

    /**
     * started thread method wich connecting to the matrix.
     */
    @Override
    public void run() {
        final String massage = "start thread with value: " + value
                + " and speciication: " + repositorySpecification;
        logger.debug(massage);
        RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
        MatrixRepository repository = repositoryFactory.getMatrixRepository();


        for (int i = 0; i < repository.getMatrixSize(); i++) {
            repository.setMatrixElementByThreads(
                    repositorySpecification, i, i, value);
        }
    }
}
