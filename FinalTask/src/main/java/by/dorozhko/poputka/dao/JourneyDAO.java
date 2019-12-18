package by.dorozhko.poputka.dao;

import by.dorozhko.poputka.dao.exception.ExceptionDao;
import by.dorozhko.poputka.entity.Journey;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface JourneyDAO extends InterfaceDAO<Integer, Journey> {

    List<Journey> takeListOfNearestTripForMain(final LocalDate today,
                                               final LocalTime currentTime,
                                               int limitFrom, int limitTo)
            throws ExceptionDao;
    Journey takeJourneyByJourneyIdAndDriverId (int journeyId,
                                               int driverId)
            throws ExceptionDao;

}
