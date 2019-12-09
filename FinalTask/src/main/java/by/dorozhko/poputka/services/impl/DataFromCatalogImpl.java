package by.dorozhko.poputka.services.impl;

import by.dorozhko.poputka.dao.*;
import by.dorozhko.poputka.dao.exception.ExceptionDao;
import by.dorozhko.poputka.services.AbstractService;
import by.dorozhko.poputka.services.DataFromCatalogService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class DataFromCatalogImpl extends AbstractService implements DataFromCatalogService {
    private final Logger logger = LogManager.getLogger(getClass().getName());
    private Map<Integer, String> map = new HashMap<>();

    @Override
    public Map<Integer, String> getGenders() {

        CatalogDAO catalogDAO = FactoryDao.getInstance().getCatalogDAO();
        Transaction transaction = TransactionFactory.getInstance().getTransaction();
        transaction.begin(catalogDAO);
        try {
            map = catalogDAO.getGenderList();
            transaction.commit();
        } catch (ExceptionDao exceptionDao) {
            transaction.rollback();
            logger.error(exceptionDao);
        } finally {
            transaction.end();
        }


        return map;
    }

    @Override
    public Map<Integer, String> getCountries() {
        CatalogDAO catalogDAO = FactoryDao.getInstance().getCatalogDAO();
        Transaction transaction = TransactionFactory.getInstance().getTransaction();
        transaction.begin(catalogDAO);
        try {
            map = catalogDAO.getCountryList();
            transaction.commit();
        } catch (ExceptionDao exceptionDao) {
            transaction.rollback();
            logger.error(exceptionDao);
        } finally {
            transaction.end();
        }


        return map;
    }

    @Override
    public Map<Integer, String> getRegionsOfCountry(int countryId) {
        Map<Integer, String> mapOfRegions = null;
        CatalogDAO catalogDAO = FactoryDao.getInstance().getCatalogDAO();
        transaction.begin(catalogDAO);
        try {
            mapOfRegions = catalogDAO.getRegionOfCountryList(countryId);
            transaction.commit();
        } catch (ExceptionDao exceptionDao) {
            logger.error(exceptionDao);
            transaction.rollback();
        } finally {
            transaction.end();
        }

        return mapOfRegions;
    }

    @Override
    public Map<Integer, String> getCitiesOfRegion(int regionId) {
        Map<Integer, String> citiesMap = null;
        CatalogDAO catalogDAO = FactoryDao.getInstance().getCatalogDAO();
        transaction.begin(catalogDAO);
        try {
            citiesMap = catalogDAO.getCitiesOfRegionList(regionId);
            transaction.commit();
        } catch (ExceptionDao exceptionDao) {
            logger.error(exceptionDao);
            transaction.rollback();
        } finally {
            transaction.end();
        }

        return citiesMap;
    }

    @Override
    public Map<Integer, String> getCarBrands() {
        CatalogDAO catalogDAO = FactoryDao.getInstance().getCatalogDAO();
        Transaction transaction = TransactionFactory.getInstance().getTransaction();
        transaction.begin(catalogDAO);
        try {
            map = catalogDAO.getCarBrandList();
            transaction.commit();
        } catch (ExceptionDao exceptionDao) {
            transaction.rollback();
            logger.error(exceptionDao);
        } finally {
            transaction.end();
        }


        return map;
    }

    @Override
    public Map<Integer, String> getCarModelsOfBrand(int brandId) {
        CatalogDAO catalogDAO = FactoryDao.getInstance().getCatalogDAO();
        Transaction transaction = TransactionFactory.getInstance().getTransaction();
        transaction.begin(catalogDAO);
        try {
            map = catalogDAO.getCarModelList(brandId);
            transaction.commit();
        } catch (ExceptionDao exceptionDao) {
            transaction.rollback();
            logger.error(exceptionDao);
        } finally {
            transaction.end();
        }


        return map;
    }

    @Override
    public Map<Integer, String> getCarClimateTypes() {
        CatalogDAO catalogDAO = FactoryDao.getInstance().getCatalogDAO();
        Transaction transaction = TransactionFactory.getInstance().getTransaction();
        transaction.begin(catalogDAO);
        try {
            map = catalogDAO.getCarClimateTypesList();
            transaction.commit();
        } catch (ExceptionDao exceptionDao) {
            transaction.rollback();
            logger.error(exceptionDao);
        } finally {
            transaction.end();
        }


        return map;
    }

    @Override
    public Map<Integer, String> getCurrencies() {
        CatalogDAO catalogDAO = FactoryDao.getInstance().getCatalogDAO();
        Transaction transaction = TransactionFactory.getInstance().getTransaction();
        transaction.begin(catalogDAO);
        try {
            map = catalogDAO.getCurrenciesList();
            transaction.commit();
        } catch (ExceptionDao exceptionDao) {
            transaction.rollback();
            logger.error(exceptionDao);
        } finally {
            transaction.end();
        }


        return map;    }
}
