package by.dorozhko.transport.dal;


import by.dorozhko.transport.dal.exception.DALException;

import java.util.List;

public interface TransportDAL {
    /**
     * Method for writing to the file.
     *
     * @param transportEntity String list of entities.
     * @return true if successful.
     * @throws DALException cover IOException.
     */
    boolean write(List<String> transportEntity) throws DALException;

    /**
     * Method read from the data.
     *
     * @return list of strings.
     * @throws DALException cover IOExeption.
     */
    List<String> read() throws DALException;

}
