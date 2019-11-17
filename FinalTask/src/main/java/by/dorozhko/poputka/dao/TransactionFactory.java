package by.dorozhko.poputka.dao;

import by.dorozhko.poputka.dao.connection.EntityTransaction;

public class TransactionFactory {
    private static final TransactionFactory instance = new TransactionFactory();

    private Transaction transaction = new EntityTransaction();

    private TransactionFactory(){

    }

    public static TransactionFactory getInstance() {
        return instance;
    }

    public Transaction getTransaction(){
        return transaction;
    }

}
