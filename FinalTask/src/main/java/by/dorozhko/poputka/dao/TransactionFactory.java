package by.dorozhko.poputka.dao;

import by.dorozhko.poputka.dao.connection.EntityTransaction;

public final class TransactionFactory {

    /**
     * static variable single_instance of type TransactionFactory.
     */
    private static final TransactionFactory INSTANCE = new TransactionFactory();


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

    //TODO access to create only one object?

    /**
     * Create new transaction.
     *
     * @return link to transaction.
     */
    public Transaction getTransaction() {
        return new EntityTransaction();
    }

}
