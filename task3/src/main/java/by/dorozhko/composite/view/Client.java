package by.dorozhko.composite.view;


import by.dorozhko.composite.controller.Controller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public final class Client {
    private Logger logger = LogManager.getLogger(getClass().getName());

    private Scanner userInput = new Scanner(System.in);

    private Controller controller = new Controller();

    private Locale current;

    private ResourceBundle resourceBundle;

    public void startProgram() {

        System.out.println("Please choose language:\n 1 — English\n 2 — русский ");

        char i = 0;
        try {
            i = (char) System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String country = "";
        String language = "";
        switch (i) {
            case '1':
                country = "";
                language = "";
                break;
            case '2':
                country = "RU";
                language = "ru";
                break;
        }

        current = new Locale(language, country);
        resourceBundle = ResourceBundle.getBundle("local.text", current);


        userMenu();


    }

    private void userMenu() {
        System.out.println(resourceBundle.getString("userMenu1"));

        logger.trace("waiting for user input.");

        String userAction = userInput.next();

        String request = null;
        switch (userAction) {
            case "1":
                logger.trace("User: CreateCompositeFromData");
                request = "CreateCompositeFromData|";
                System.out.println(resourceBundle.getString("path"));
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
                System.out.println(resourceBundle.getString("path"));
                request += userInput.next();
                System.out.println(controller.doAction(request));
                break;
            case "4":
                logger.trace("User: View Sorted Composite Text From Repository");

                request = "ViewSortedCompositeTextFromRepository|";
                System.out.println(resourceBundle.getString("sortMenu"));
                String userRequest = userInput.next();
                switch (userRequest) {
                    case "1":
                        request += "Text";
                        break;
                    default:
                        System.out.println(resourceBundle.getString("errorMsg"));
                        startProgram();

                }

                System.out.println(controller.doAction(request));
                break;
            case "0":
                System.out.println(resourceBundle.getString("exitMsg"));
                logger.info("Exit from program by user.");
                System.exit(0);
            default:
                System.out.println(resourceBundle.getString("errorMsg"));
        }
        userMenu();
    }
}
