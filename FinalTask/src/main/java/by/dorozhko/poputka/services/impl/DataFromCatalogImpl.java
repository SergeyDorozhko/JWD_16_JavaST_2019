package by.dorozhko.poputka.services.impl;

import by.dorozhko.poputka.dao.*;
import by.dorozhko.poputka.dao.exception.ExceptionDao;
import by.dorozhko.poputka.services.DataFromCatalogService;

import java.util.HashMap;
import java.util.Map;

public class DataFromCatalogImpl implements DataFromCatalogService {
    @Override
    public Map<Integer, String> getGenders() {

        Map<Integer, String> map = new HashMap<>();
        CatalogDAO catalogDAO = FactoryDao.getInstance().getCatalogDAO();
        Transaction transaction = TransactionFactory.getInstance().getTransaction();
        transaction.begin(catalogDAO);
        try {
            map = catalogDAO.getGenderList();
            transaction.commit();
        } catch (ExceptionDao exceptionDao) {
            transaction.rollback();
            exceptionDao.printStackTrace();
        } finally {
            transaction.end();
        }


        return map;
    }

    @Override
    public Map<Integer, String> getCountries() {
        Map<Integer, String> map = new HashMap<>();
        CatalogDAO catalogDAO = FactoryDao.getInstance().getCatalogDAO();
        Transaction transaction = TransactionFactory.getInstance().getTransaction();
        transaction.begin(catalogDAO);
        try {
            map = catalogDAO.getCountryList();
            transaction.commit();
        } catch (ExceptionDao exceptionDao) {
            transaction.rollback();
            exceptionDao.printStackTrace();
        } finally {
            transaction.end();
        }


        return map;    }
}
