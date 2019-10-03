package by.dorozhko.matrix.dal;

import by.dorozhko.matrix.dal.exeption.ExceptionDAL;

import java.util.List;

public interface MatrixDAL {
    /**
     * method read data from database.
     * @return List of string lines with data.
     * @throws ExceptionDAL cover all exceptions throws by DAL layout.
     */
    List<String> readData() throws ExceptionDAL;
}
