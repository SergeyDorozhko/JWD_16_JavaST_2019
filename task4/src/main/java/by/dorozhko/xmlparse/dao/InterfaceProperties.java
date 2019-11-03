package by.dorozhko.xmlparse.dao;


public interface InterfaceProperties {
    /**
     * get property from config file.
     * @param rootCatalog root catalog.
     * @return property.
     */
    String getProperty(String rootCatalog);
}
