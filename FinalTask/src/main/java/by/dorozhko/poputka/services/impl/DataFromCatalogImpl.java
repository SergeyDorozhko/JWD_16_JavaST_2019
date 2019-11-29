package by.dorozhko.poputka.services.impl;

import by.dorozhko.poputka.dao.FactoryDao;
import by.dorozhko.poputka.dao.Transaction;
import by.dorozhko.poputka.dao.TransactionFactory;
import by.dorozhko.poputka.dao.UserDAO;
import by.dorozhko.poputka.dao.exception.ExceptionDao;
import by.dorozhko.poputka.services.DataFromCatalogService;
import by.dorozhko.poputka.services.Service;

import java.util.HashMap;
import java.util.Map;

public class DataFromCatalogImpl implements DataFromCatalogService {
    @Override
    public Map<Integer, String> getGendors() {

        Map<Integer, String> map = new HashMap<>();
        UserDAO userDAO = FactoryDao.getInstance().getUserDAO();
        Transaction transaction = TransactionFactory.getInstance().getTransaction();
        transaction.begin(userDAO);
        try {
            map = userDAO.getGenderList();
            transaction.commit();
        } catch (ExceptionDao exceptionDao) {
            transaction.rollback();
            exceptionDao.printStackTrace();
        } finally {
            transaction.end();
        }


        return map;
    }
}
