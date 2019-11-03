package by.dorozhko.xmlparse.dao.property;

import by.dorozhko.xmlparse.dao.InterfaceProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class XMLPropertyImpl implements InterfaceProperties {

    /**
     * logger.
     */
    private final Logger logger = LogManager.getLogger(getClass().getName());

    /**
     * get path from property.
     *
     * @param rootCatalog root catalog.
     * @return path to xml.
     */
    @Override
    public String getProperty(final String rootCatalog) {

        String pathToXSD = null;
        try (FileInputStream fileInputStream = new FileInputStream(
                new File(rootCatalog
                        + "WEB-INF/property/config.properties"))) {

            Properties properties = new Properties();
            properties.load(fileInputStream);

            pathToXSD = properties.getProperty("db.XSDpath");

        } catch (IOException ex) {
            logger.error(ex);
            return "some problem with property file.";
        }

        return rootCatalog + pathToXSD;
    }
}
