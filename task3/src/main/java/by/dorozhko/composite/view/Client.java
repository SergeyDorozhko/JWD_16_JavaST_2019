package by.dorozhko.composite.view;


import by.dorozhko.composite.controller.Controller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public final class Client {
    /**
     * logger.
     */
    private Logger logger = LogManager.getLogger(getClass().getName());
    /**
     * Scan user actions.
     */
    private Scanner userInput = new Scanner(System.in);
    /**
     * Link to controller.
     */
    private Controller controller = new Controller();
    /**
     * Local language chooser.
     */
    private ResourceBundle resourceBundle;

    /**
     * Starting program method.
     */
    public void startProgram() {

        System.out.println("Please choose language:"
                + "\n 1 — English\n 2 — русский ");

        char i = 0;
        try {
            i = (char) System.in.read();
        } catch (IOException e) {
            logger.error(e);
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
            default:
                System.out.println("Incorrect input.\nSet default English.\n");
        }

        Locale current = new Locale(language, country);
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
                logger.trace("User: View Sorted"
                        + "Composite Text From Repository");

                request = "ViewSortedCompositeTextFromRepository|";

                request += sortMenu();
                System.out.println(controller.doAction(request));
                break;
            case "5":
                logger.trace("User: Save Sorted"
                        + "Composite Text From Repository");

                request = "SaveSortedCompositeTextFromRepository|";

                request += sortMenu();

                System.out.println(resourceBundle.getString("path"));
                request += "|" + userInput.next();
                System.out.println(controller.doAction(request));
                break;
            case "0":
                System.out.println(resourceBundle.getString("exitMsg"));
                logger.info("Exit from program by user.");
                System.exit(0);
                break;
            default:
                System.out.println(resourceBundle.getString("errorMsg"));
        }
        userMenu();
    }

    private String sortMenu() {
        System.out.println(resourceBundle.getString("sortMenu"));
        String userRequest = userInput.next();


        String result = "";
        switch (userRequest) {
            case "1":
                result = "Text ";
                break;
            case "2":
                result = "WordsIsSentence ";
                break;
            case "3":
                result = "LexemsBySymbolsOfAlfabet ";
                System.out.println(resourceBundle.getString("sortMenu2"));
                userRequest = userInput.next();
                if (userRequest.length() == 1) {
                    result += userRequest;
                } else {
                    System.out.println("errorMsg");
                    startProgram();
                }
                break;
            default:
                System.out.println(
                        resourceBundle.getString("errorMsg"));
                startProgram();

        }

        return result;
    }
}
