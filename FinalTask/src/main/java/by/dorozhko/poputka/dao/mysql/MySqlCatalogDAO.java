package by.dorozhko.poputka.dao.mysql;

import by.dorozhko.poputka.dao.CatalogDAO;
import by.dorozhko.poputka.dao.exception.ExceptionDao;
import by.dorozhko.poputka.entity.Address;
import by.dorozhko.poputka.entity.Car;
import by.dorozhko.poputka.entity.Entity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MySqlCatalogDAO implements CatalogDAO {
    private Connection connection;

    private final Logger logger = LogManager.getLogger(getClass().getName());

    public static final String COUNTRY_NAME = "country_name";
    public static final String CLIMATE_TYPE = "climate_type";
    public static final String MODEL = "model";
    public static final String REGION_NAME = "region_name";
    public static final String CITY_NAME = "city_name";
    public static final String ID = "id";
    public static final String GENDER = "gender";
    public static final String BRAND = "brand";
    public static final String CURRENCY = "currency";
    public static final String COUNTRY_ID = "country_id";


    private static final String SELECT_ALL_GENDERS
            = "SELECT id, gender FROM gender ORDER BY gender ASC ;";

    private static final String SELECT_ALL_COUNTRIES
            = "SELECT id, country_name FROM countries ORDER BY country_name ASC ;";
    private static final String SELECT_ALL_REGIONS_OF_COUNTRY
            = "SELECT id, region_name FROM regions WHERE country_id = ? ORDER BY region_name ASC ;";

    private static final String SELECT_ALL_CITIES_OF_REGION
            = "SELECT id, city_name FROM cities WHERE region_id = ? ORDER BY city_name ASC ;";
    private static final String SELECT_ALL_CAR_BRANDS
            = "SELECT id, brand FROM car_brands ORDER BY brand ASC ";

    private static final String SELECT_ALL_CAR_MODELS_OF_BRAND
            = "SELECT id, model FROM car_models " +
            " WHERE brand_id = ? ORDER BY model ASC ;";

    private static final String SELECT_ALL_CAR_CLIMATE
            = "SELECT id, climate_type FROM car_climate ORDER BY climate_type ASC";

    private static final String SELECT_ALL_CURRENCIES
            = "SELECT id, currency FROM currencies ORDER BY currency ASC";

    private static final String SELECT_GENDER_BY_ID
            = "SELECT gender FROM gender WHERE id = ?";

    private static final String SELECT_COUNTRY_BY_ID
            = "SELECT country_name FROM countries WHERE id = ?";

    private static final String SELECT_CLIMATE_TYPE_BY_ID
            = "SELECT climate_type FROM car_climate WHERE id = ?";
    private static final String SELECT_CURRENCY_BY_ID
            = "SELECT currency FROM currencies WHERE id = ?";
    private static final String SELECT_ADDRESS_BY_CITY_ID
            = "SELECT countries.country_name, region_name, city_name"
            + " FROM countries"
            + " INNER JOIN regions r ON countries.id = r.country_id"
            + " INNER JOIN cities c ON r.id = c.region_id WHERE c.id = ?;";
    private static final String SELECT_ADDRESS_ID_BY_CITY_ID
            = " SELECT r.country_id, r.id"
            + " FROM regions r "
            + " INNER JOIN cities c ON r.id = c.region_id WHERE c.id = ?;";

    private static final String SELECT_CAR_BRAND_MODEL_BY_MODEL_ID
            = "SELECT car_brands.brand, car_models.model FROM car_brands" +
            " INNER JOIN car_models ON car_models.brand_id = car_brands.id" +
            " WHERE car_models.id = ?;";

    @Override
    public Map<Integer, String> getCarClimateTypesList() throws ExceptionDao {
        return takeListOfCatalogData(SELECT_ALL_CAR_CLIMATE, CLIMATE_TYPE);
    }

    @Override
    public Map<Integer, String> getCarModelList(int brand) throws ExceptionDao {
        return takeListOfCatalogByMajorId(SELECT_ALL_CAR_MODELS_OF_BRAND, brand, MODEL);
    }

    @Override
    public Map<Integer, String> getCountryList() throws ExceptionDao {
        return takeListOfCatalogData(SELECT_ALL_COUNTRIES, COUNTRY_NAME);
    }

    @Override
    public Map<Integer, String> getRegionOfCountryList(int countryId) throws ExceptionDao {
        return takeListOfCatalogByMajorId(SELECT_ALL_REGIONS_OF_COUNTRY, countryId, REGION_NAME);
    }

    @Override
    public Map<Integer, String> getCitiesOfRegionList(int regionId) throws ExceptionDao {
        return takeListOfCatalogByMajorId(SELECT_ALL_CITIES_OF_REGION, regionId, CITY_NAME);
    }

    private Map<Integer, String> takeListOfCatalogByMajorId(String query,
                                                            int statemantId,
                                                            String columnValue)
            throws ExceptionDao {
        Map<Integer, String> map = new LinkedHashMap<>();
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, statemantId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Integer key = Integer.parseInt(resultSet.getString(ID));
                String value = resultSet.getString(columnValue);
                map.put(key, value);
            }
        } catch (SQLException ex) {
            logger.error(ex);
            throw new ExceptionDao(ex);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    logger.error(e);
                }
            }
        }
        return map;
    }

    @Override
    public Map<Integer, String> getGenderList() throws ExceptionDao {
        return takeListOfCatalogData(SELECT_ALL_GENDERS, GENDER);
    }

    @Override
    public Map<Integer, String> getCarBrandList() throws ExceptionDao {
        return takeListOfCatalogData(SELECT_ALL_CAR_BRANDS, BRAND);
    }

    @Override
    public Map<Integer, String> getCurrenciesList() throws ExceptionDao {
        return takeListOfCatalogData(SELECT_ALL_CURRENCIES, CURRENCY);
    }

    private Map<Integer, String> takeListOfCatalogData(String query,
                                                       String columnName)
            throws ExceptionDao {

        Map<Integer, String> map = new LinkedHashMap<>();

        try (ResultSet resultSet = connection.createStatement()
                .executeQuery(query);) {
            while (resultSet.next()) {

                Integer key = Integer.parseInt(resultSet.getString(ID));
                String value = resultSet.getString(columnName);
                map.put(key, value);
            }

        } catch (SQLException e) {
            logger.error(e);
            throw new ExceptionDao(e);
        }

        return map;
    }

    @Override
    public String getGender(int id) throws ExceptionDao {

        return takeDataByQuery(SELECT_GENDER_BY_ID, GENDER, id);
    }

    @Override
    public String getCountry(int id) throws ExceptionDao {

        return takeDataByQuery(SELECT_COUNTRY_BY_ID, COUNTRY_NAME, id);
    }

    @Override
    public String getClimateType(int id) throws ExceptionDao {

        return takeDataByQuery(SELECT_CLIMATE_TYPE_BY_ID, CLIMATE_TYPE, id);
    }

    @Override
    public String getCurrency(int id) throws ExceptionDao {
        return takeDataByQuery(SELECT_CURRENCY_BY_ID, CURRENCY, id);
    }

    private String takeDataByQuery(String query,
                                   String columnName,
                                   int id) throws ExceptionDao {
        String result = null;
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result = resultSet.getString(columnName);
            }
            logger.debug("data taken");
        } catch (SQLException ex) {
            logger.error(ex);
            throw new ExceptionDao(ex);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    logger.error(e);
                }
            }
        }
        return result;
    }

    @Override
    public Car getCar(int modelId) throws ExceptionDao {
        Car car = null;
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection
                .prepareStatement(SELECT_CAR_BRAND_MODEL_BY_MODEL_ID)) {
            statement.setInt(1, modelId);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                car = new Car();
                car.setBrand(resultSet.getString(BRAND));
                car.setModel(resultSet.getString(MODEL));
            }
        } catch (SQLException ex) {
            logger.error(ex);
            throw new ExceptionDao(ex);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    logger.error(e);
                }
            }
        }
        return car;
    }

    @Override
    public Address getAddressByCityId(int id) throws ExceptionDao {
        Address address = null;
        ResultSet addressQuery = null;
        try (PreparedStatement getAddress = connection
                .prepareStatement(SELECT_ADDRESS_BY_CITY_ID)) {
            getAddress.setInt(1, id);
            addressQuery = getAddress.executeQuery();
            address = new Address();
            while (addressQuery.next()) {
                address.setCountry(
                        addressQuery.getString(COUNTRY_NAME));
                address.setRegionalCenter(
                        addressQuery.getString(REGION_NAME));
                address.setCity(addressQuery.getString(CITY_NAME));
            }
        } catch (SQLException ex) {
            logger.error(ex);
            throw new ExceptionDao(ex);
        } finally {
            if (addressQuery != null) {
                try {
                    addressQuery.close();
                } catch (SQLException e) {
                    logger.error(e);
                }
            }
        }
        return address;
    }

    @Override
    public Address getAddressIdByCityId(int id) throws ExceptionDao {

        Address address = null;
        ResultSet addressQuery = null;
        try (PreparedStatement getAddress = connection
                .prepareStatement(SELECT_ADDRESS_ID_BY_CITY_ID)) {
            getAddress.setInt(1, id);
            addressQuery = getAddress.executeQuery();
            address = new Address();
            while (addressQuery.next()) {
                address.setCountry(
                        addressQuery.getString(COUNTRY_ID));
                address.setRegionalCenter(
                        addressQuery.getString(ID));
                address.setCity(Integer.toString(id));
            }
        } catch (SQLException ex) {
            logger.error(ex);
            throw new ExceptionDao(ex);
        } finally {
            if (addressQuery != null) {
                try {
                    addressQuery.close();
                } catch (SQLException e) {
                    logger.error(e);
                }
            }
        }
        return address;
    }

    /**
     * Method take connection to database and set it to realisation of Dao.
     *
     * @param newConnection - connection to database.
     */
    @Override
    public void setConnection(Connection newConnection) {
        connection = newConnection;
    }

    /**
     * Save new entity to database.
     *
     * @param entity -entity which will saved to database.
     * @return - result of saving. return true value if successfully.
     * @throws ExceptionDao - generating if some values conflicts
     *                      with existed in database.
     */
    @Override
    public Entity create(Entity entity) throws ExceptionDao {
        throw new UnsupportedOperationException();
    }

    /**
     * Remove entity from database.
     *
     * @param id - identity of removing entity.
     * @return - true value if successfully removed, otherwise false.
     */
    @Override
    public boolean delete(Object id) {
        throw new UnsupportedOperationException();
    }


    /**
     * Update entity values.
     *
     * @param entity - entity which needed to be update
     *               with new params.
     * @return - entity with new params.
     */
    @Override
    public Entity update(Entity entity) {
        throw new UnsupportedOperationException();
    }

    /**
     * Find all entity from current data table.
     *
     * @return - list of entities which were found.
     * @throws ExceptionDao - generated if some problems
     *                      with connecting to database.
     */
    @Override
    public List findAll() throws ExceptionDao {
        throw new UnsupportedOperationException();
    }

    /**
     * Find entity of target type by identity.
     *
     * @param id - identity of target entity.
     * @return - entity which corresponds to identity.
     */
    @Override
    public Entity findEntityById(Object id) throws ExceptionDao {
        throw new UnsupportedOperationException();
    }
}
