package by.dorozhko.composite.dal;

import by.dorozhko.composite.dal.impl.FileCompositeDAL;

public class FactoryDAL {
    private static final FactoryDAL instance = new FactoryDAL();
    private CompositeDAL compositeDAL = new FileCompositeDAL();

    private FactoryDAL() {
    }

    public static FactoryDAL getInstance() {
        return instance;
    }

    public CompositeDAL getCompositeDAL() {
        return compositeDAL;
    }
}
