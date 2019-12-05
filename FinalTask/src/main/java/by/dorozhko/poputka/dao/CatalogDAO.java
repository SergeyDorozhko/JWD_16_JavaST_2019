package by.dorozhko.poputka.dao;

import by.dorozhko.poputka.dao.exception.ExceptionDao;
import by.dorozhko.poputka.entity.Address;
import by.dorozhko.poputka.entity.Car;

import java.util.Map;

public interface CatalogDAO extends InterfaceDAO {
    Map<Integer, String> getCountryList() throws ExceptionDao;
    Map<Integer, String> getRegionOfCountryList(int countryId) throws ExceptionDao;
    Map<Integer, String> getCitiesOfRegionList(int regionId) throws ExceptionDao;
    Map<Integer, String> getGenderList() throws ExceptionDao;

    Map<Integer, String> getCarBrandList() throws ExceptionDao;

    Map<Integer, String> getCarModelList(int brand) throws ExceptionDao;

    Map<Integer, String> getCarClimateTypesList() throws ExceptionDao;

    Map<Integer, String> getCurrenciesList() throws ExceptionDao;


    String getGender(int id) throws ExceptionDao;
    String getCountry(int id) throws ExceptionDao;
    String getClimateType(int id) throws ExceptionDao;
    Car getCar(int modelId) throws ExceptionDao;
    Address getAddressByCityId(int id) throws ExceptionDao;


}