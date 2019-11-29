package by.dorozhko.poputka.services;

import java.util.Map;

public interface DataFromCatalogService extends Service{
    Map<Integer, String> getGendors();
}
