package by.dorozhko.transport.repository;

import by.dorozhko.transport.entity.TransportEntity;

import java.util.List;

public interface TransportRepository {
    boolean add(TransportEntity transportEntity);

    String delete(int removeId);

    boolean update(TransportEntity transportEntity);

    List<TransportEntity> query(TransportSpecification specification);


}
