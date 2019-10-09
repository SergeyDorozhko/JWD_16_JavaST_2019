package by.dorozhko.composite.dal;

import by.dorozhko.composite.dal.exception.ExceptionDAL;

public interface CompositeDAL {
    String read(String pathToData) throws ExceptionDAL;
    String write(String text, String pathToData) throws ExceptionDAL;
}
