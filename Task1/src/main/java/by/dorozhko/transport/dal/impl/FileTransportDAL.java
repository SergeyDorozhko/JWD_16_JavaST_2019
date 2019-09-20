package by.dorozhko.transport.dal.impl;

import by.dorozhko.transport.dal.TransportDAL;
import by.dorozhko.transport.dal.exception.DALException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


import java.io.FileReader;
import java.io.FileOutputStream;
import java.io.File;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class FileTransportDAL implements TransportDAL {
    /**
     * file with data.
     */
    private File file = new File("carriages.txt");
    /**
     * Log4j creation.
     */
    private final Logger logger = LogManager.getLogger(FileTransportDAL.class.getName());

    /**
     * ethod write to the file List of Strings.
     *
     * @param transportEntity entity.
     * @return true of successful.
     * @throws DALException cover IOException.
     */
    public boolean write(final List<String> transportEntity) throws DALException {

        try (BufferedOutputStream buff = new BufferedOutputStream(new FileOutputStream(file))) {
            for (String line : transportEntity) {
                buff.write((line + "\n").getBytes());
            }
        } catch (IOException e) {
            logger.error("File error" + e);
            throw new DALException(e);
        }

        return true;
    }


    /**
     * Method read data from file line by line.
     *
     * @return List of Strings.
     * @throws DALException caver IOException.
     */
    public List<String> read() throws DALException {
        List<String> list = new ArrayList<String>();

        try (Scanner scanFile = new Scanner(new FileReader(file))) {

            while (scanFile.hasNext()) {
                list.add(scanFile.nextLine());
            }
        } catch (IOException e) {
            throw new DALException(e);
        }

        return list;
    }
}
