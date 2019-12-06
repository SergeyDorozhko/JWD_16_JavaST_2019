package by.dorozhko.poputka.services;

import by.dorozhko.poputka.entity.Journey;
import by.dorozhko.poputka.services.exception.ExceptionService;

import java.util.List;

public interface JourneyService extends Service {

    List<Journey> findAllActualForMainPage();
    List<Journey> findAllActualForJourneyPage(int limitFrom, int limitTo);

    Journey createNewJourney(Journey journey) throws ExceptionService;
    Journey findJourney(int id);
}
