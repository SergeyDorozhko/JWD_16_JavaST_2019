package by.dorozhko.matrix.repository;

import by.dorozhko.matrix.repository.impl.MatrixRepositoryImpl;

public class RepositoryFactory {
    private final static RepositoryFactory instace = new RepositoryFactory();

    private MatrixRepository matrixRepository = new MatrixRepositoryImpl();

    private RepositoryFactory() {}

    public static RepositoryFactory getInstance(){
        return instace;
    }

    public MatrixRepository getMatrixRepository(){
        return matrixRepository;
    }

}
