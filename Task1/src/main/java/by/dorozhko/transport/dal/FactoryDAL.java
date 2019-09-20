package by.dorozhko.transport.dal;

import by.dorozhko.transport.dal.impl.FileTransportDAL;

public final class FactoryDAL {
    /**
     * singletone.
     */
    private static final FactoryDAL instance = new FactoryDAL();

    /**
     * Creating only one DAL object.
     */
    private final TransportDAL fileTransportDAO = new FileTransportDAL();

    private FactoryDAL() {
    }

    /**
     * single object.
     *
     * @return new FactoryDAL
     */
    public static FactoryDAL getInstance() {
        return instance;
    }

    /**
     * Single object of file access.
     *
     * @return TransportDAL interface.
     */
    public TransportDAL getTransportDAO() {
        return fileTransportDAO;
    }
}
