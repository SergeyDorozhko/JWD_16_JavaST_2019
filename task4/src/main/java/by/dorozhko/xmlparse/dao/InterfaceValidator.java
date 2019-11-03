package by.dorozhko.xmlparse.dao;

public interface InterfaceValidator {
    /**
     * method to validate xml file by schema.
     * @param pathToXML path to xml file on server.
     * @param pathToXSD path to xsd file on server.
     * @return is valid.
     */
    boolean validate(String pathToXML, String pathToXSD);
}
