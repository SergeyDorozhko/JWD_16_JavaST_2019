package by.dorozhko.poputka.services.impl;

import by.dorozhko.poputka.dao.FactoryDao;
import by.dorozhko.poputka.services.ConnectionService;

public class ConnectionServiceImpl implements ConnectionService {
    @Override
    public void initConnectionPool() {
        FactoryDao.getInstance().initConnectionPool();
    }

    @Override
    public void closeConnectionPool() {
        FactoryDao.getInstance().closeConnectionPool();
    }
}
