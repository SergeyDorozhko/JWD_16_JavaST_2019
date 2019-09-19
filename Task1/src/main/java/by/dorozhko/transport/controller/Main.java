package by.dorozhko.transport.controller;


import by.dorozhko.transport.entity.Carriage;
import by.dorozhko.transport.entity.Train;
import by.dorozhko.transport.entity.TransportEntity;
import by.dorozhko.transport.entity.params.CarriageType;
import by.dorozhko.transport.entity.params.EngineType;
import by.dorozhko.transport.view.ClientView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sun.plugin2.message.transport.Transport;

import java.util.ArrayList;
import java.util.List;


public class Main {
    private final static Logger logger = LogManager.getLogger(Main.class.getName());

    public static void main(final String[] args) {
        ClientView view = new ClientView();
        logger.info("Program is started.");

        view.startView();
        //test();
    }


    private static void test() {

        Carriage carriage = new Carriage("Vasia", 15000, 30, CarriageType.ECONOM_CLASS, 50, 30);
        Carriage carriageTwo = new Carriage("Petya", 16000, 30, CarriageType.ECONOM_CLASS, 50, 30);

        Train train = new Train("Pavel", 20000, 35, EngineType.DIESEL, 800, 80);

        List<TransportEntity> transport = new ArrayList<>();

        transport.add(carriage);
        transport.add(carriageTwo);
        transport.add(train);

        for (TransportEntity el : transport) {
            System.out.println(el);
        }

        TransportEntity el = transport.get(1);
        System.out.println(el.getClass().isInstance(carriageTwo));
    }


}


