package by.dorozhko.composite.dal.impl;

import by.dorozhko.composite.dal.CompositeDAL;
import by.dorozhko.composite.dal.exception.ExceptionDAL;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;

public class FileCompositeDAL implements CompositeDAL {

    private final Logger logger = LogManager.getLogger(getClass().getName());

    @Override
    public String read(final String pathToData) throws ExceptionDAL {
        StringBuilder readFromData = new StringBuilder();
        try (BufferedInputStream inputStream =
                     new BufferedInputStream(
                             new FileInputStream(new File(pathToData)))) {
            while (inputStream.available() > 0) {
                readFromData.append((char) inputStream.read());
            }
        } catch (IOException ex) {
            logger.error(ex);
            throw new ExceptionDAL(ex);
        }
        return readFromData.toString();
    }

    @Override
    public String write(final String text,
                        final String pathToData) throws ExceptionDAL {
        logger.info("Start wright to file.");
        logger.debug(pathToData);
        File file = new File(pathToData);

        try (BufferedOutputStream outputStream =
                     new BufferedOutputStream(new FileOutputStream(file))) {
            outputStream.write(text.getBytes());
        } catch (IOException ex) {
            logger.error(ex);
            throw new ExceptionDAL(ex);
        }
        return "Successfully written.";
    }
}
