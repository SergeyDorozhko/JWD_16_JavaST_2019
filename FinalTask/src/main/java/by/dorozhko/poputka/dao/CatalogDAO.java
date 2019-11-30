package by.dorozhko.poputka.dao;

import by.dorozhko.poputka.dao.exception.ExceptionDao;

import java.util.Map;

public interface CatalogDAO extends InterfaceDAO {
    Map<Integer, String> getCountryList() throws ExceptionDao;

    Map<Integer, String> getGenderList() throws ExceptionDao;
}