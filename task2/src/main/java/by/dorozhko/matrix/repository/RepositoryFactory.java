package by.dorozhko.matrix.repository;

import by.dorozhko.matrix.repository.impl.MatrixRepositoryImpl;

public final class RepositoryFactory {
    /**
     * single tone.
     */
    private static final RepositoryFactory instace = new RepositoryFactory();
    /**
     * link to matrix repository implementation.
     */
    private MatrixRepository matrixRepository = new MatrixRepositoryImpl();

    private RepositoryFactory() {
    }

    /**
     * link to single example of object of class.
     * @return link.
     */
    public static RepositoryFactory getInstance() {
        return instace;
    }

    /**
     * link to matrix repository implementation.
     * @return link.
     */
    public MatrixRepository getMatrixRepository() {
        return matrixRepository;
    }

}
