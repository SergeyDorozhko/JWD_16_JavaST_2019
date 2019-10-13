package by.dorozhko.composite.dal;

import by.dorozhko.composite.dal.impl.FileCompositeDAL;

public final class FactoryDAL {
    private static final FactoryDAL INSTANCE = new FactoryDAL();
    private CompositeDAL compositeDAL = new FileCompositeDAL();

    private FactoryDAL() {
    }

    public static FactoryDAL getInstance() {
        return INSTANCE;
    }

    public CompositeDAL getCompositeDAL() {
        return compositeDAL;
    }
}
