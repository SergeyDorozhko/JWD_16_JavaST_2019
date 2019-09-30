package by.dorozhko.matrix.entity;

import by.dorozhko.matrix.repository.MatrixRepository;
import by.dorozhko.matrix.repository.RepositoryFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InitialisationThread extends Thread{
    private final Logger logger = LogManager.getLogger(getClass().getName());

    private int value;

    private String repositorySpecification;
    public InitialisationThread(int newValue, String newRepositorySpecification) {
        value = newValue;
        repositorySpecification = newRepositorySpecification;
    }


    public void run(){
        logger.debug("start thread with value: " + value + " and speciication: " + repositorySpecification);
        RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
        MatrixRepository repository = repositoryFactory.getMatrixRepository();


        for (int i = 0; i < repository.getMatrixSize(); i++){
            repository.setMatrixElementByThreads(repositorySpecification, i, i, value);
        }
    }
}
