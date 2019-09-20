package by.dorozhko.transport.repository.storage;

import by.dorozhko.transport.entity.TransportEntity;

import java.util.ArrayList;
import java.util.List;

public class Storage {

    private static final Storage instanse = new Storage();

    private List<TransportEntity> dataList = new ArrayList<>();

    private Storage() {
    }

    public static Storage getInstanse() {
        return instanse;
    }

    public boolean add(final TransportEntity entity) {
        return dataList.add(entity);
    }

    public TransportEntity get(final int id) {
        return dataList.get(id);
    }

    public String deleteEntity(final int id) {
        return dataList.remove(id).getClass().getSimpleName();
    }

    public List<TransportEntity> getAll() {
        List<TransportEntity> resultList = new ArrayList<>();

        for (TransportEntity entity : dataList) {
            resultList.add(entity);
        }

        return resultList;
    }
}
