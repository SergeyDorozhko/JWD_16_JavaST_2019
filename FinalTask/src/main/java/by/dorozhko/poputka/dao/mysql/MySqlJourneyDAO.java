package by.dorozhko.poputka.dao.mysql;

import by.dorozhko.poputka.dao.JourneyDAO;
import by.dorozhko.poputka.dao.exception.ExceptionDao;
import by.dorozhko.poputka.entity.Address;
import by.dorozhko.poputka.entity.Journey;
import by.dorozhko.poputka.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlJourneyDAO implements JourneyDAO {

    private final Logger logger = LogManager.getLogger(getClass().getName());

    private Connection connection;
    //TODO only actual and sort by date then time.
    private static final String SELECT_ALL_JOURNEYS
            = "SELECT journey.id, journey.driver_id, journey.start_address_id,"
            + " journey.destination_address_id, journey.departure_time,"
            + " journey.departure_date,"
            + " journey.cost, currencies.currency, journey.number_of_passengers"
            + " FROM journey"
            + " INNER JOIN currencies ON journey.currency_id = currencies.id";

    private static final String SELECT_ADDRESS_BY_CITY_ID
            = "SELECT countries.country_name, region_name, city_name"
            + " FROM countries"
            + " INNER JOIN regions r ON countries.id = r.country_id"
            + " INNER JOIN cities c ON r.id = c.region_id WHERE c.id = ?;";

    /**
     * Method take connection to database and set it to realisation of Dao.
     *
     * @param newConnection - connection to database.
     */
    @Override
    public void setConnection(final Connection newConnection) {
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
    public Journey create(Journey entity) throws ExceptionDao {
        return entity;
    }

    /**
     * Remove entity from database.
     *
     * @param id - identity of removing entity.
     * @return - true value if successfully removed, otherwise false.
     */
    @Override
    public boolean delete(Integer id) {
        return false;
    }


    /**
     * Update entity values.
     *
     * @param entity - entity which needed to be update
     *               with new params.
     * @return - entity with new params.
     */
    @Override
    public Journey update(Journey entity) {
        return null;
    }

    /**
     * Find all entity from current data table.
     *
     * @return - list of entities which were found.
     * @throws ExceptionDao - generated if some problems
     *                      with connecting to database.
     */
    @Override
    public List<Journey> findAll() throws ExceptionDao {
        List<Journey> list = new ArrayList<>();

        try (ResultSet journeyQuery = connection.createStatement()
                .executeQuery(SELECT_ALL_JOURNEYS);
             PreparedStatement getAddress = connection.prepareStatement(SELECT_ADDRESS_BY_CITY_ID)) {
            while (journeyQuery.next()) {
                Journey journey = new Journey();

                journey.setId(Integer.parseInt(journeyQuery.getString("id")));
                User driver = new User();
                driver.setId(Integer.parseInt(journeyQuery.getString("driver_id")));
                journey.setDriver(driver);
                getAddress.setInt(1, journeyQuery.getInt("start_address_id"));
                ResultSet addressQuery = getAddress.executeQuery();

                Address startAddress = new Address();
                while (addressQuery.next()) {
                    startAddress.setCountry(
                            addressQuery.getString("country_name"));
                    startAddress.setRegionalCenter(
                            addressQuery.getString("region_name"));
                    startAddress.setCity(addressQuery.getString("city_name"));
                }
                journey.setStartAddress(startAddress);

                getAddress.setInt(1,
                        journeyQuery.getInt("destination_address_id"));
                addressQuery = getAddress.executeQuery();

                Address destinationAddress = new Address();
                while (addressQuery.next()) {
                    destinationAddress.setCountry(
                            addressQuery.getString("country_name"));
                    destinationAddress.setRegionalCenter(
                            addressQuery.getString("region_name"));
                    destinationAddress.setCity(
                            addressQuery.getString("city_name"));
                }

                journey.setDestinationAddress(destinationAddress);

                journey.setDepartureTime(
                        journeyQuery.getString("departure_time"));
                journey.setDepartureDate(
                        journeyQuery.getString("departure_date"));

                journey.setCost(Double.parseDouble(
                        journeyQuery.getString("cost")));
                journey.setCurrency(journeyQuery.getString("currency"));
                journey.setPassengersNumber(Integer.parseInt(
                        journeyQuery.getString("number_of_passengers")));

                list.add(journey);
            }

        } catch (SQLException e) {
            logger.error(e);
            throw new ExceptionDao(e);
        }

        return list;
    }

    /**
     * Find entity of target type by identity.
     *
     * @param id - identity of target entity.
     * @return - entity which corresponds to identity.
     */
    @Override
    public Journey findEntityById(Integer id) {
        return null;
    }
}
