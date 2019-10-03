package by.dorozhko.matrix.service.impl;

import by.dorozhko.matrix.dal.FactoryDAL;
import by.dorozhko.matrix.dal.MatrixDAL;
import by.dorozhko.matrix.dal.exeption.ExceptionDAL;
import by.dorozhko.matrix.entity.InitialisationThread;
import by.dorozhko.matrix.repository.MatrixRepository;
import by.dorozhko.matrix.repository.RepositoryFactory;
import by.dorozhko.matrix.repository.exception.RepositoryException;
import by.dorozhko.matrix.service.MatrixService;
import by.dorozhko.matrix.service.parser.Parser;
import by.dorozhko.matrix.service.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Service implements MatrixService {
    /**
     * logger.
     */
    private final Logger logger = LogManager.getLogger(getClass().getName());
    /**
     * single tone.
     */
    private RepositoryFactory repositoryFactory
            = RepositoryFactory.getInstance();
    /**
     * creating link to access to repository.
     */
    private MatrixRepository matrixRepository
            = repositoryFactory.getMatrixRepository();
    /**
     * Method create matrix from data in storage.
     *
     * @return result of creation.
     */
    @Override
    public String createMatrix() {
        logger.debug("start creating matrix");
        FactoryDAL factoryDAL = FactoryDAL.getInstance();
        MatrixDAL matrixDAL = factoryDAL.getMatrixDAL();
        List<String> matrixData = null;
        try {
            matrixData = matrixDAL.readData();
        } catch (ExceptionDAL ex) {
            logger.error(ex);
            return "some problems with data.";
        }
        logger.debug("data was taken.");
        if (!Validator.isValidMatrix(matrixData)) {
            logger.error("Validation: incorrect data.");
            return "incorrect data.";
        }

        logger.debug("date is valid.");

        int matrixSize = Parser.parseBySpace(matrixData.get(0)).length;
        matrixRepository.setMatrixSize(matrixSize);

        for (int i = 0; i < matrixSize; i++) {
            String[] elements = Parser.parseBySpace(matrixData.get(i));
            for (int j = 0; j < matrixSize; j++) {
                matrixRepository.setMatrixElement(
                        i, j, Integer.parseInt(elements[j]));
            }
        }
        logger.debug("matrix is set up and initialized");
        return "matrix is set up and initialized.";
    }

    /**
     * display matrix.
     * @return string view matrix.
     */
    @Override
    public String viewMatrix() {
        logger.debug("try displey matrix.");
        int[][] matrix = null;
        try {
            matrix = matrixRepository.getMatrix();
        } catch (RepositoryException ex) {
            logger.error("view matrix error:", ex);
            return "can't display matrix. try to initialize one.";
        }
        logger.debug("matrix successfully taken.");
        StringBuilder result = new StringBuilder();
        for (int[] line : matrix) {
            result.append(Arrays.toString(line) + "\n");
        }
        return result.toString();
    }

    /**
     * initialization of main matrix diagonal
     * by specification (choose synchronization method).
     * @param specification specification.
     * @return result.
     */
    @Override
    public String initialiseMainDiagonal(final String specification) {
        logger.debug("start initialise threads");
        FactoryDAL factoryDAL = FactoryDAL.getInstance();
        MatrixDAL matrixDAL = factoryDAL.getMatrixDAL();
        List<String> threadData = new ArrayList<>();
        try {
            threadData = matrixDAL.readData();
        } catch (ExceptionDAL ex) {
            logger.error("some problems with data: ", ex);
            return "some problems with data.";
        }

        logger.debug("thread data from file read.");

        for (String threadValue : threadData) {
            logger.debug(threadValue);
            if (Validator.isValidThreadData(threadValue)) {
                logger.debug("valid data of thread.");
                int insertValueOfThread = Integer.
                        parseInt(Parser.parseByEqualSing(threadValue)[1]);
                InitialisationThread thread = new InitialisationThread(
                        insertValueOfThread, specification);
                thread.start();
            }
        }

        return "main diagonal successfully update.";
    }
}
