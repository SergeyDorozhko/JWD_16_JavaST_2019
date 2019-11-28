package by.dorozhko.poputka.services.impl;

import by.dorozhko.poputka.dao.TransactionFactory;
import by.dorozhko.poputka.services.ConnectionService;

public class ConnectionServiceImpl implements ConnectionService {
    @Override
    public void initConnection() {
        TransactionFactory.getInstance().initConnectionPool();
    }

    @Override
    public void closeConnection() {
        TransactionFactory.getInstance().closeConnectionPool();
    }
}
