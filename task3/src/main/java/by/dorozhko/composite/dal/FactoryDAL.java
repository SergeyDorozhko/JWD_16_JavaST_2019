package by.dorozhko.composite.dal;

import by.dorozhko.composite.dal.impl.FileCompositeDAL;

public final class FactoryDAL {
    /**
     * single tone.
     */
    private static final FactoryDAL INSTANCE = new FactoryDAL();

    /**
     * single link to file DAL.
     */
    private CompositeDAL compositeDAL = new FileCompositeDAL();

    private FactoryDAL() { }

    /**
     * link to single object of class.
     * @return link to factory.
     */
    public static FactoryDAL getInstance() {
        return INSTANCE;
    }

    /**
     * access to link to dal realisation.
     * @return link to file DAL.
     */
    public CompositeDAL getCompositeDAL() {
        return compositeDAL;
    }
}
