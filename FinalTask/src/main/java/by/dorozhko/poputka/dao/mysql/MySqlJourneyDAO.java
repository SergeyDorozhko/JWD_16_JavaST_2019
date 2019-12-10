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
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class MySqlJourneyDAO implements JourneyDAO {

    private final Logger logger = LogManager.getLogger(getClass().getName());

    private Connection connection;

    private static final String SELECT_ACTUAL_TRIPS_WITH_LIMIT
            = " SELECT id, driver_id, start_address_id, destination_address_id, "
            + " departure_time, departure_date, cost, currency_id, number_of_passengers, "
            + " additional_information FROM journey "
            + " WHERE departure_date > ? OR departure_date = ? AND departure_time > ? "
            + " ORDER BY departure_date, departure_time ASC "
            + " limit ?, ? ";

    private static final String SELECT_ALL_JOURNEYS
            = "SELECT journey.id, journey.driver_id, journey.start_address_id,"
            + " journey.destination_address_id, journey.departure_time,"
            + " journey.departure_date,"
            + " journey.cost, currencies.currency, journey.number_of_passengers"
            + " FROM journey"
            + " INNER JOIN currencies ON journey.currency_id = currencies.id";

    private static final String INSERT_NEW_JOURNEY
            = " INSERT INTO journey (driver_id, start_address_id,"
            + " destination_address_id, departure_time, departure_date, cost,"
            + " currency_id, number_of_passengers, additional_information)"
            + " VALUES (?,?,?,?,?,?,?,?,?);";

    private static final String SELECT_JOURNEY_BY_ID
            = " SELECT id, driver_id, start_address_id, destination_address_id,"
            + " departure_time, departure_date, cost, currency_id,"
            + " number_of_passengers, additional_information FROM journey"
            + " WHERE id = ? ;";

    private static final String SELECT_JOURNEY_BY_ID_AND_DRIVER_ID
            = " SELECT id, driver_id, start_address_id, destination_address_id,"
            + " departure_time, departure_date, cost, currency_id,"
            + " number_of_passengers, additional_information FROM journey"
            + " WHERE id = ? and driver_id = ?;";

    private static final String UPDATE_JOURNEY_BY_ID_AND_DRIVER_ID
            = " UPDATE journey SET start_address_id = ?, destination_address_id = ?,"
            + " departure_time = ?, departure_date = ?, cost = ?, currency_id = ? ,"
            + " number_of_passengers = ?, additional_information = ?  "
            + "WHERE id = ? and driver_id = ? ;";

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
        try (PreparedStatement statement = connection.prepareStatement(INSERT_NEW_JOURNEY)) {
            statement.setInt(1, entity.getDriver().getId());
            statement.setInt(2, Integer.parseInt(entity.getStartAddress().getCity()));
            statement.setInt(3, Integer.parseInt(entity.getDestinationAddress().getCity()));
            statement.setString(4, entity.getDepartureTime().toString());
            statement.setString(5, entity.getDepartureDate().toString());
            statement.setDouble(6, entity.getCost());
            statement.setInt(7, Integer.parseInt(entity.getCurrency()));
            statement.setInt(8, entity.getPassengersNumber());
            statement.setString(9, entity.getAdditionalInformation());
            statement.executeUpdate();
        } catch (SQLException ex) {
            logger.error(ex);
            throw new ExceptionDao(ex);
        }
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
    public Journey update(Journey entity) throws ExceptionDao {
        logger.debug(String.format("journey params: %d, %d, all %s", entity.getId(), entity.getDriver().getId(), entity));
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_JOURNEY_BY_ID_AND_DRIVER_ID)) {
            statement.setInt(1, Integer.parseInt(entity.getStartAddress().getCity()));
            statement.setInt(2, Integer.parseInt(entity.getDestinationAddress().getCity()));
            statement.setString(3, entity.getDepartureTime().toString());
            statement.setString(4, entity.getDepartureDate().toString());
            statement.setDouble(5, entity.getCost());
            statement.setInt(6, Integer.parseInt(entity.getCurrency()));
            statement.setInt(7, entity.getPassengersNumber());
            statement.setString(8, entity.getAdditionalInformation());
            statement.setInt(9, entity.getId());
            statement.setInt(10, entity.getDriver().getId());
            statement.executeUpdate();
        } catch (SQLException ex) {
            logger.error(ex);
            entity = null;
            throw new ExceptionDao(ex);
        }

        return entity;
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
                .executeQuery(SELECT_ALL_JOURNEYS);) {
            while (journeyQuery.next()) {

                Journey journey = createJourney(journeyQuery);
                list.add(journey);
            }

        } catch (SQLException e) {
            logger.error(e);
            throw new ExceptionDao(e);
        }

        return list;
    }

    private Journey createJourney(ResultSet resultSet) throws SQLException {
        Journey journey = new Journey();

        journey.setId(Integer.parseInt(resultSet.getString("id")));
        User driver = new User();
        driver.setId(Integer.parseInt(resultSet.getString("driver_id")));
        journey.setDriver(driver);


        Address startAddress = new Address();
        startAddress.setCity(resultSet.getString("start_address_id"));


        journey.setStartAddress(startAddress);


        Address destinationAddress = new Address();
        destinationAddress.setCity(resultSet.getString("destination_address_id"));


        journey.setDestinationAddress(destinationAddress);

        journey.setDepartureTime(
                resultSet.getString("departure_time"));
        journey.setDepartureDate(
                resultSet.getString("departure_date"));

        journey.setCost(Double.parseDouble(
                resultSet.getString("cost")));
        journey.setCurrency(resultSet.getString("currency_id"));
        journey.setPassengersNumber(Integer.parseInt(
                resultSet.getString("number_of_passengers")));
        return journey;
    }

    /**
     * Find entity of target type by identity.
     *
     * @param id - identity of target entity.
     * @return - entity which corresponds to identity.
     */
    @Override
    public Journey findEntityById(Integer id) throws ExceptionDao {
        ResultSet resultSet = null;
        Journey journey = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_JOURNEY_BY_ID)) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                journey = createJourney(resultSet);
                journey.setAdditionalInformation(resultSet.getString("additional_information"));
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


        return journey;
    }


    @Override
    public List<Journey> takeListOfNearestTripForMain(final LocalDate today, final LocalTime currentTime, int limitFrom, int limitTo) throws ExceptionDao {
        List<Journey> list = new ArrayList<>();
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ACTUAL_TRIPS_WITH_LIMIT);) {
            statement.setString(1, today.toString());
            statement.setString(2, today.toString());
            statement.setString(3, currentTime.toString());
            statement.setInt(4, limitFrom);
            statement.setInt(5, limitTo);

            resultSet = statement.executeQuery();
            while (resultSet.next()) {

                Journey journey = createJourney(resultSet);
                list.add(journey);
            }

        } catch (SQLException e) {
            logger.error(e);
            throw new ExceptionDao(e);
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    logger.error(e);
                }
            }
        }

        return list;
    }

    @Override
    public Journey takeJourneyByJourneyIdAndDriverId(int journeyId, int driverId) throws ExceptionDao {
        Journey journey = null;
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_JOURNEY_BY_ID_AND_DRIVER_ID)) {
            statement.setInt(1, journeyId);
            statement.setInt(2, driverId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                journey = createJourney(resultSet);
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
        return journey;
    }
}
