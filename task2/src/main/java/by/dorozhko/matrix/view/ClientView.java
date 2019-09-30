package by.dorozhko.matrix.view;

import by.dorozhko.matrix.controller.Controller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public final class ClientView {
    /**
     * logger.
     */
    private final Logger logger = LogManager.getLogger(getClass().getName());
    /**
     * interface tocontroller.
     */
    private Controller controller = new Controller();

    /**
     * method starting client view.
     */
    public void startView() {

        logger.debug("program started.");
        System.out.println("\tHello!!!");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Choose action:\n"
                    + "1)Initialise matrix\n"
                    + "2)Show Matrix\n"
                    + "3)Start initialisation of main"
                    + "diagonal(ReentrantLock).\n"
                    + "4)Start initialisation of main diagonal(Semaphore).\n"
                    + "5)Start initialisation of main diagonal(Phase).\n"
                    + "0)Exit");

            String action = scanner.next();

            switch (action) {
                case "1":
                    System.out.println(controller.doAction(
                            "CreateMatrix:creating"));
                    break;
                case "2":
                    System.out.println(controller.doAction(
                            "DisplayMatrix:view"));
                    break;
                case "3":
                    System.out.println(controller.doAction(
                            "InitialiseMainDiagonal:ReentrantLock"));
                    break;
                case "4":
                    System.out.println(controller.doAction(
                            "InitialiseMainDiagonal:Semaphore"));
                    break;
                case "5":
                    System.out.println(controller.doAction(
                            "InitialiseMainDiagonal:Phase"));
                    break;
                case "0":
                    System.out.println("End of a program");
                    logger.error("end by user request");
                    System.exit(0);
                default:
                    System.out.println("Incorrect input, please try again.");
            }
        }
    }

}
