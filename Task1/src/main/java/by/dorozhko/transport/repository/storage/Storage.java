package by.dorozhko.transport.repository.storage;

import by.dorozhko.transport.entity.TransportEntity;

import java.util.ArrayList;
import java.util.List;

public final class Storage {

    /**
     * single tone.
     */
    private static final Storage instanse = new Storage();

    /**
     * data store.
     */
    private List<TransportEntity> dataList = new ArrayList<>();

    private Storage() {
    }

    /**
     * get access to storege.
     *
     * @return link to the storage.
     */
    public static Storage getInstanse() {
        return instanse;
    }

    /**
     * add entity t the storage.
     * @param entity new entity.
     * @return result.
     */
    public boolean add(final TransportEntity entity) {
        return dataList.add(entity);
    }

    /**
     * Method to take entity.
     *
     * @param line value of entity.
     * @return entity.
     */
    public TransportEntity get(final int line) {
        return dataList.get(line);
    }

    /**
     * method delete entity by line value.
     *
     * @param line value of entity.
     * @return type of deleted entity.
     */
    public String deleteEntity(final int line) {
        return dataList.remove(line).getClass().getSimpleName();
    }

    /**
     * Take list of all storage.
     * @return list.
     */
    public List<TransportEntity> getAll() {
        List<TransportEntity> resultList = new ArrayList<>();

        resultList.addAll(dataList);
        return resultList;
    }
}
