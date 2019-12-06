package by.dorozhko.poputka.dao;

import by.dorozhko.poputka.dao.exception.ExceptionDao;
import by.dorozhko.poputka.entity.Journey;

import java.time.LocalDate;
import java.util.List;

public interface JourneyDAO extends InterfaceDAO<Integer, Journey> {

    List<Journey> takeListOfNearestTripForMain(final LocalDate today, int limitFrom, int limitTo) throws ExceptionDao;
}
