package by.dorozhko.xmlparse.controller;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();
        System.out.println("-----------DOM------------\n");
        System.out.println(controller.doAction("BUILD-DOM-src/main/resources/data/tariffs.xml-src/main/resources/data/tariffs.xsd"));
        System.out.println("\n-----------------SAX--------------------\n");
        System.out.println(controller.doAction("BUILD-SAX-src/main/resources/data/tariffs.xml-src/main/resources/data/tariffs.xsd"));
        System.out.println("\n---------------------StAX----------------\n");
        System.out.println(controller.doAction("BUILD-StAX-src/main/resources/data/tariffs.xml-src/main/resources/data/tariffs.xsd"));
    }
}
