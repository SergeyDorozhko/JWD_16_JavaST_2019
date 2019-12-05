package by.dorozhko.poputka.services;

import by.dorozhko.poputka.entity.Journey;
import by.dorozhko.poputka.services.exception.ExceptionService;

import java.util.List;

public interface JourneyService extends Service {

    List<Journey> findAllJourneyShort();
    Journey createNewJourney(Journey journey) throws ExceptionService;
}
