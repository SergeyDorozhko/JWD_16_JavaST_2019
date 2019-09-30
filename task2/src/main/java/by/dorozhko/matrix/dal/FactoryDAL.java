package by.dorozhko.matrix.dal;

import by.dorozhko.matrix.dal.impl.FileMatrixDAL;

public class FactoryDAL {

    private final static FactoryDAL instance = new FactoryDAL();

    private FactoryDAL() { }
    private MatrixDAL matrixDAL = new FileMatrixDAL();

    /**
     * Get single object of Factory.
     * @return
     */
    public static FactoryDAL getInstance() {
        return instance;
    }

    /**
     * accese to the data.
     * @return interface of access to data.
     */
    public MatrixDAL getMatrixDAL() {
        return matrixDAL;
    }
}
