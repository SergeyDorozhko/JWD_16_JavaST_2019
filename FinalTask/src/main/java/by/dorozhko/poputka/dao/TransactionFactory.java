package by.dorozhko.poputka.dao;

import by.dorozhko.poputka.dao.connection.ConnectionPool;
import by.dorozhko.poputka.dao.connection.EntityTransaction;

public final class TransactionFactory {

    /**
     * static variable single_instance of type TransactionFactory.
     */
    private static final TransactionFactory INSTANCE
            = new TransactionFactory();


    private TransactionFactory() {

    }

    /**
     * Access to single instance of type TransactionFactory.
     *
     * @return link to instance of class.
     */
    public static TransactionFactory getInstance() {
        return INSTANCE;
    }


    /**
     * Create new transaction.
     *
     * @return link to transaction.
     */
    public Transaction getTransaction() {
        return new EntityTransaction();
    }

    public void initConnectionPool() {
        ConnectionPool.getInstance();
    }

    public void closeConnectionPool() {
        ConnectionPool.getInstance().closePool();
    }
}
