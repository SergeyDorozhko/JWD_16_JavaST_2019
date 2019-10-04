package by.dorozhko.matrix.dal.impl;

import by.dorozhko.matrix.dal.MatrixDAL;
import by.dorozhko.matrix.dal.exeption.ExceptionDAL;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileMatrixDAL implements MatrixDAL {
    /**
     * path to directory with matrix data.
     */
    private File file = new File("data\\programdata.txt");

    /**
     * Method read data from file.
     * @return list of lines with data.
     * @throws ExceptionDAL
     */
    @Override
    public List<String> readData() throws ExceptionDAL {

        List<String> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            while ((reader.ready())) {
                list.add(reader.readLine());
            }
        } catch (IOException ex) {
            throw new ExceptionDAL(ex);
        }
        return list;
    }
}
