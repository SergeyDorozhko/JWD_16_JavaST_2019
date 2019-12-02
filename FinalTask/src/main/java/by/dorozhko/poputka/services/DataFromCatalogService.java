package by.dorozhko.poputka.services;

import java.util.Map;

public interface DataFromCatalogService extends Service{
    Map<Integer, String> getGenders();
    Map<Integer, String> getCountries();
    Map<Integer, String> getCarBrands();
    Map<Integer, String> getCarModelsOfBrand(int brandId);
    Map<Integer, String> getCarClimateTypes();



}
