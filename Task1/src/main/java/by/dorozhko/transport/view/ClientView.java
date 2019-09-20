package by.dorozhko.transport.view;

import by.dorozhko.transport.controller.Controller;
import by.dorozhko.transport.dal.exception.DALException;
import by.dorozhko.transport.services.Services;
import by.dorozhko.transport.services.impl.ImplServices;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sun.security.jgss.wrapper.GSSCredElement;

import java.util.Scanner;

public class ClientView {
    /**
     * Controller object.
     */
    Controller controller = new Controller();

    /**
     * Scanner for scanning user input.
     */
    private Scanner userInput = new Scanner(System.in);

    /**
     * Logger of the class ClientView.
     */
    private final Logger logger = LogManager.getLogger(ClientView.class.getName());

    /**
     * Start cLient veiw.
     */
    public void startView() {
        System.out.println("\n Choose You action:\n "
                + "press:\n"
                + "1) Create train (reading from file)\n"
                + "2) Add\n"
                + "3) Detele\n"
                + "4) Sort by\n"
                + "5) Find\n"
                + "6) Count Passengers\n"
                + "7) Count baggage\n"
                + "8) find carriages with number of passengers between.\n"
                + "0) Exit");
        logger.trace("waiting for user input.");

        String userAction = userInput.next();

        String request = null;
        switch (userAction) {
            case "1":
                logger.trace("User: read from file");

                System.out.println(controller.doAction("readFromFile|find"));

                break;
            case "2":

                logger.trace("User: add");
                request = add();
                System.out.println(controller.doAction(request));
                break;
            case "3":
                logger.trace("User: delete");

                System.out.println("Input number of line you wat to delete: ");

                while (!userInput.hasNextInt()) {
                    userInput.next();
                    System.out.println("incorrect input.  Input number of line you wat to delete: ");
                }

                request = "delete| " + userInput.nextInt();

                System.out.println(controller.doAction(request));
                break;
            case "4":
                break;
            case "5":
                logger.trace("User: specificationQuery");

                System.out.println(controller.doAction("specificationQuery|" + find()));
                break;
            case "6":
                logger.trace("User: countPassengers");

                System.out.println(controller.doAction("countPassengers|AllCarriages"));
                break;
            case "7":
                logger.trace("User: countBaggage");

                System.out.println(controller.doAction("countBaggage|AllCarriages"));
                break;
            case "8":
                logger.trace("User: number of passengers between");

                System.out.println(controller.doAction("numberOfPassengersBetween|AllCarriages-" + numberOfPassengersBetween()));
                break;
            case "0":
                System.out.println("Have a nice day!!! \n\tBye!!!");
                logger.info("Exit from program by user.");
                System.exit(0);
            default:
                System.out.println("incorrect value. \nTry again:");
        }
        startView();

    }


    private String add() {
        StringBuilder data = new StringBuilder("add|");
        boolean isCorrectInput = false;
        do {
            System.out.println("Press to add:\n1) Train\n2)Carriage");
            String input = userInput.next();
            switch (input) {
                case "1":
                    data.append("Train:" + addTrain());
                    isCorrectInput = true;
                    break;
                case "2":
                    data.append("Carriage:" + addCarriage());
                    isCorrectInput = true;
                    break;
                default:
                    System.out.println("Incorrect value, please, try again.");
            }
        } while (!isCorrectInput);

        logger.info("Try to add:" + data.toString());
        return data.toString();
    }


    private String addCarriage() {
        StringBuilder data = new StringBuilder();


        System.out.println("Input name:");
        String input = userInput.next();
        data.append("name = " + input + ",");


        System.out.println("Input weight:");
        input = userInput.next();
        data.append("weight = " + input + ",");

        System.out.println("Input length:");
        input = userInput.next();
        data.append("length = " + input + ",");

        boolean isCorrectInput = false;

        do {
            System.out.println("Select carriage type:\n1) COMPARTMENT\n2) ECONOM_CLASS_SITTING\n"
                    + "3) ECONOM_CLASS\n4) SV\n5) INTERNATIONAL_FIRST_CLASS\n6) INTERNATIOAL_SECOND_CLASS");
            input = userInput.next();
            switch (input) {
                case "1":
                    data.append("carriageType = COMPARTMENT,");
                    isCorrectInput = true;
                    break;
                case "2":
                    data.append("carriageType = ECONOM_CLASS_SITTING,");
                    isCorrectInput = true;
                    break;
                case "3":
                    data.append("carriageType = ECONOM_CLASS,");
                    isCorrectInput = true;
                    break;
                case "4":
                    data.append("carriageType = SV,");
                    isCorrectInput = true;
                    break;
                case "5":
                    data.append("carriageType = INTERNATIONAL_FIRST_CLASS,");
                    isCorrectInput = true;
                    break;
                case "6":
                    data.append("carriageType = INTERNATIOAL_SECOND_CLASS,");
                    isCorrectInput = true;
                    break;

                default:
                    System.out.println("Incorrect value, please, try again.");
            }
        } while (!isCorrectInput);


        System.out.println("Input max value of baggage:");
        input = userInput.next();
        data.append("maxValueOfBaggage = " + input + ",");

        System.out.println("Input max number of passengers:");
        input = userInput.next();
        data.append("numberOfPassengers = " + input);
        return data.toString();
    }


    private String addTrain() {
        StringBuilder data = new StringBuilder();


        System.out.println("Input name:");
        String input = userInput.next();
        data.append("name = " + input + ",");


        System.out.println("Input weight:");
        input = userInput.next();
        data.append("weight = " + input + ",");

        System.out.println("Input length:");
        input = userInput.next();
        data.append("length = " + input + ",");

        boolean isCorrectInput = false;

        do {
            System.out.println("Select engine type:\n1) DIESEL\n2) ELECTRICITY");
            input = userInput.next();
            switch (input) {
                case "1":
                    data.append("engineType = DIESEL,");
                    isCorrectInput = true;
                    break;
                case "2":
                    data.append("engineType = ELECTRICITY,");
                    isCorrectInput = true;
                    break;
                default:
                    System.out.println("Incorrect value, please, try again.");
            }
        } while (!isCorrectInput);


        System.out.println("Input power of engine:");
        input = userInput.next();
        data.append("enginePower = " + input + ",");

        System.out.println("Input max speed:");
        input = userInput.next();
        data.append("maxSpeed = " + input);
        return data.toString();
    }

    private String find() {
        StringBuilder data = new StringBuilder("");
        boolean isCorrectInput = false;
        do {
            System.out.println("Press to find:\n"
                    + "1)display all\n"
                    + "2)All carriages\n"
                    + "3) Count passengers");
            String input = userInput.next();
            switch (input) {
                case "1":
                    data.append("displayAll");
                    isCorrectInput = true;
                    break;
                case "2":
                    data.append("AllCarriages");
                    isCorrectInput = true;
                    break;
                case "3":
                    data.append("countPassengers");
                    isCorrectInput = true;
                    break;
                default:
                    System.out.println("Incorrect value, please, try again.");
            }
        } while (!isCorrectInput);

        logger.info("Try to find:" + data.toString());
        return data.toString();
    }


    private String numberOfPassengersBetween() {
        StringBuilder data = new StringBuilder("");
        boolean isCorrectInput = false;

        System.out.println("Input min number of passengers:");

        String input = userInput.next();

        data.append(input);

        System.out.println("Input max number of passengers:");

        input = userInput.next();

        data.append("to" + input);

        logger.info("Try to find number of passengers between:" + data.toString());
        return data.toString();
    }
}
