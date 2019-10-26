package by.dorozhko.xmlparse.controller;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller();
        System.out.println(controller.doAction("DOM-src/main/resources/data/tariffs.xml"));
        System.out.println(controller.doAction("SAX-src/main/resources/data/tariffs.xml"));
     //   controller.doAction("StAX-src/main/resources/data/tariffs.xml");
    }
}
