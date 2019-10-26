package by.dorozhko.xmlparse.services;

public interface Service {
    String parseDOM(String pathToXMLandXSD);

    String parseSAX(String pathToXMLandXSD);

    String parseStAX(String pathToXMLandXSD);
}
