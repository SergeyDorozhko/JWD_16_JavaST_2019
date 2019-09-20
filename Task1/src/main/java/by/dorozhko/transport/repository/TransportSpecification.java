package by.dorozhko.transport.repository;

import by.dorozhko.transport.entity.TransportEntity;

import java.util.List;

public interface TransportSpecification {
    List<TransportEntity> getAllEntityOfQuery();
}
