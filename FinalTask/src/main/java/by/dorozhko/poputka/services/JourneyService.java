package by.dorozhko.poputka.services;

import by.dorozhko.poputka.entity.Journey;

import java.util.List;

public interface JourneyService extends Service {

    List<Journey> findAllJourneyShort();
}
