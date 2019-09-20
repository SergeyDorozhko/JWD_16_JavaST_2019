package by.dorozhko.transport.entity;

import by.dorozhko.transport.entity.params.CarriageType;

public class TransportEntityFactory {
    private static final TransportEntityFactory instance = new TransportEntityFactory();

    private TransportEntityFactory() {
    }

    public static TransportEntityFactory getInstance() {
        return instance;
    }

    public Carriage createCarriage(String params) {
        String[] classThenParams = params.split(":");
        String[] paramsArray = classThenParams[1].split(",");

        String[] param = new String[paramsArray.length];

        for (int i = 0; i < paramsArray.length; i++) {
            String[] splitParam = paramsArray[i].split("=");
            param[i] = splitParam[1];
        }

        int id = Integer.parseInt(param[0].trim());
        String name = param[1].trim();
        int weight = Integer.parseInt(param[2].trim());
        int length = Integer.parseInt(param[3].trim());
        CarriageType type = CarriageType.valueOf(param[4].trim().toUpperCase());
        int numberOfPassengers = Integer.parseInt(param[5].trim());
        int maxValueOfBaggage = Integer.parseInt(param[6].trim());


        return new Carriage(name, weight, length, type, numberOfPassengers, maxValueOfBaggage);

    }


}
