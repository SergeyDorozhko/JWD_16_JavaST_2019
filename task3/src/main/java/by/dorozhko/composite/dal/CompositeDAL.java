package by.dorozhko.composite.dal;

import by.dorozhko.composite.dal.exception.ExceptionDAL;

public interface CompositeDAL {
    /**
     * method to read data from data.
     * @param pathToData path where data is stored.
     * @return string data.
     * @throws ExceptionDAL covering IOException.
     */
    String read(String pathToData) throws ExceptionDAL;

    /**
     * method for write data to data.
     * @param text information in String format.
     * @param pathToData path to storage.
     * @return result.
     * @throws ExceptionDAL covering IOException.
     */
    String write(String text, String pathToData) throws ExceptionDAL;
}
