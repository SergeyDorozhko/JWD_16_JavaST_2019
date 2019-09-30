package by.dorozhko.matrix.dal;

import by.dorozhko.matrix.dal.exeption.ExceptionDAL;

import java.util.List;

public interface MatrixDAL {
    List<String> readData() throws ExceptionDAL;
}
