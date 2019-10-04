package by.dorozhko.matrix.repository.impl;

import by.dorozhko.matrix.repository.MatrixRepository;
import by.dorozhko.matrix.repository.exception.RepositoryException;
import by.dorozhko.matrix.repository.storage.MatrixStorage;
import by.dorozhko.matrix.repository.storage.SpecificationProvider;
import by.dorozhko.matrix.repository.storage.StorageSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class MatrixRepositoryImpl implements MatrixRepository {
    /**
     * logger.
     */
    private final Logger logger = LogManager.getLogger(getClass().getName());
    /**
     * link to single tone of storage.
     */
    private MatrixStorage storage = MatrixStorage.getInstance();

    /**
     * set matrix size.
     *
     * @param size matrix size NxN.
     */
    @Override
    public void setMatrixSize(final int size) {
        logger.debug("size is set up.");
        storage.setSize(size);
    }

    /**
     * set matrix element.
     *
     * @param verticalPosition   vertical position of element.
     * @param horizontalPosition horizontal position of element.
     * @param value              value of matrix element to set up.
     * @return result of setting.
     */
    @Override
    public boolean setMatrixElement(final int verticalPosition,
                                    final int horizontalPosition,
                                    final int value) {
        if (storage.getSize() <= 0) {
            logger.error("Matrix not set up. Can't set element value.");
            return false;
        }


        storage.setElement(verticalPosition, horizontalPosition, value);
        logger.debug("Matrix value successfully set up.");
        return true;
    }

    /**
     * Set velue of matrix element (Thread secured).
     *
     * @param specification      choose type of synchronization.
     * @param verticalPosition   vertical position of element.
     * @param horizontalPosition horizontal position of element.
     * @param value              value of matrix element to set up.
     * @return result.
     */
    @Override
    public boolean setMatrixElementByThreads(final String specification,
                                             final int verticalPosition,
                                             final int horizontalPosition,
                                             final int value) {
        SpecificationProvider specificationProvider
                = SpecificationProvider.getInstance();
        StorageSpecification storageSpecification
                = specificationProvider.getSpecification(specification);
        storageSpecification.setMatrixElement(verticalPosition,
                horizontalPosition,
                value);
        return false;
    }

    /**
     * Get value of matrix element.
     *
     * @param verticalPosition   vertical position of element.
     * @param horizontalPosition horizontal position of element.
     * @return value of element.
     * @throws RepositoryException throws when matrix not initialized.
     */
    @Override
    public int getMatrixElement(final int verticalPosition,
                                final int horizontalPosition)
            throws RepositoryException {
        if (storage.getSize() <= 0) {
            logger.error("Matrix not set up. Can't get element value.");
            throw new RepositoryException("matrix not set up");
        }
        logger.debug("element successfully got.");
        return storage.getElement(verticalPosition, horizontalPosition);
    }

    /**
     * Get size of matrix NxN.
     *
     * @return size of matrix.
     */
    @Override
    public int getMatrixSize() {
        return storage.getSize();
    }

    /**
     * get matrix.
     *
     * @return matrix.
     * @throws RepositoryException throws when matrix not initialized.
     */
    @Override
    public int[][] getMatrix() throws RepositoryException {
        logger.debug("get matrix.");
        if (storage.getSize() <= 0) {
            logger.error("Matrix not set up. Can't get matrix.");
            throw new RepositoryException("Matrix not set up. "
                    + "Can't get matrix.");
        }
        int[][] matrix = new int[storage.getSize()][storage.getSize()];
        for (int i = 0; i < storage.getSize(); i++) {
            for (int j = 0; j < storage.getSize(); j++) {
                matrix[i][j] = storage.getElement(i, j);
            }
        }
        return matrix;
    }
}
