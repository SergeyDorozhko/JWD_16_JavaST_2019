package by.dorozhko.composite.view;


import by.dorozhko.composite.controller.Controller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public final class Client {
    private Logger logger = LogManager.getLogger(getClass().getName());

    private Scanner userInput = new Scanner(System.in);

    private Controller controller = new Controller();

    public void startProgram() {
        System.out.println("\n Choose You action:\n "
                + "press:\n"
                + "1) Create composite (reading from file).\n"
                + "2) View text from composite.\n"
                + "3) Save text to data.\n"
                + "0) Exit.");
        logger.trace("waiting for user input.");

        String userAction = userInput.next();

        String request = null;
        switch (userAction) {
            case "1":
                logger.trace("User: CreateCompositeFromData");
                request = "CreateCompositeFromData|";
                System.out.println("Input path to data:");
                request += userInput.next();
                System.out.println(controller.doAction(request));

                break;
            case "2":

                logger.trace("User: view text from composite");

                request = "ViewCompositeTextFromRepository| ";
                System.out.println(controller.doAction(request));
                break;
            case "3":
                logger.trace("User: save text to data");

                request = "SaveTextToData|";
                System.out.println("Input path to data:");
                request += userInput.next();
                System.out.println(controller.doAction(request));
                break;
            case "0":
                System.out.println("Have a nice day!!! \n\tBye!!!");
                logger.info("Exit from program by user.");
                System.exit(0);
            default:
                System.out.println("incorrect value. \nTry again:");
        }
        startProgram();


    }
}
