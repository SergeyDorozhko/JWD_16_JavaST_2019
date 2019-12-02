package by.dorozhko.poputka.dao;

import by.dorozhko.poputka.dao.exception.ExceptionDao;

import java.util.Map;

public interface CatalogDAO extends InterfaceDAO {
    Map<Integer, String> getCountryList() throws ExceptionDao;

    Map<Integer, String> getGenderList() throws ExceptionDao;

    Map<Integer, String> getCarBrandList() throws ExceptionDao;

    Map<Integer, String> getCarModelList(int brand) throws ExceptionDao;

    Map<Integer, String> getCarClimateTypesList() throws ExceptionDao;
    String getGender(int id) throws ExceptionDao;


}