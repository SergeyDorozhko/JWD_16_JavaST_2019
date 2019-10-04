package by.dorozhko.matrix.dal;

import by.dorozhko.matrix.dal.impl.FileMatrixDAL;

public final class FactoryDAL {
    /**
     * Singletone.
     */
    private static final FactoryDAL INSTANCE = new FactoryDAL();

    private FactoryDAL() { }

    /**
     * Connection to data from file by interface.
     */
    private MatrixDAL matrixDAL = new FileMatrixDAL();

    /**
     * Get single object of Factory.
     * @return link to single example.
     */
    public static FactoryDAL getInstance() {
        return INSTANCE;
    }

    /**
     * accese to the data.
     * @return interface of access to data.
     */
    public MatrixDAL getMatrixDAL() {
        return matrixDAL;
    }
}
