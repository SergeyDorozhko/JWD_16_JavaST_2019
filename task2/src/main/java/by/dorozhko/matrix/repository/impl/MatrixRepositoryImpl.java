package by.dorozhko.matrix.repository.impl;

import by.dorozhko.matrix.repository.MatrixRepository;
import by.dorozhko.matrix.repository.exception.RepositoryException;
import by.dorozhko.matrix.repository.storage.MatrixStorage;
import by.dorozhko.matrix.repository.storage.SpecificationProvider;
import by.dorozhko.matrix.repository.storage.StorageSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class MatrixRepositoryImpl implements MatrixRepository {

    private final Logger logger = LogManager.getLogger(getClass().getName());

    private MatrixStorage storage = MatrixStorage.getInstance();

    @Override
    public void setMatrixSize(int size) {
        logger.debug("size is set up.");
        storage.setSize(size);
    }

    @Override
    public boolean setMatrixElement(int verticalPosition, int horizontalPosition, int value) {
        if (storage.getSize() <= 0) {
            logger.error("Matrix not set up. Can't set element value.");
            return false;
        }


        storage.setElement(verticalPosition, horizontalPosition, value);
        logger.debug("Matrix value successfully set up.");
        return true;
    }

    @Override
    public boolean setMatrixElementByThreads(String specification, int verticalPosition, int horizontalPosition, int value) {
        SpecificationProvider specificationProvider = SpecificationProvider.getInstance();
        StorageSpecification storageSpecification = specificationProvider.getSpecification(specification);
        storageSpecification.setMatrixElement(verticalPosition, horizontalPosition, value);
        return false;
    }

    @Override
    public int getMatrixElement(int verticalPosition, int horizontalPosition) throws RepositoryException {
        if (storage.getSize() <= 0) {
            logger.error("Matrix not set up. Can't get element value.");
            throw new RepositoryException("matrix not set up");
        }
        logger.debug("element successfully got.");
        return storage.getElement(verticalPosition, horizontalPosition);
    }

    @Override
    public int getMatrixSize() {
        logger.debug("get matrix size + " + storage.getSize());
        return storage.getSize();
    }

    @Override
    public int[][] getMatrix() throws RepositoryException{
        logger.debug("get matrix.");
        if(storage.getSize() <= 0){
            logger.error("Matrix not set up. Can't get matrix.");
            throw new RepositoryException("Matrix not set up. Can't get matrix.");
        }
        int[][] matrix = new int[storage.getSize()][storage.getSize()];
        for (int i = 0; i < storage.getSize(); i++){
            for (int j = 0; j < storage.getSize(); j++){
                matrix[i][j] = storage.getElement(i,j);
            }
        }
        return matrix;
    }
}
