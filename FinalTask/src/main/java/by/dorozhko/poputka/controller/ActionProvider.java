package by.dorozhko.poputka.controller;

import by.dorozhko.poputka.controller.action.*;
import by.dorozhko.poputka.controller.action.autorized_user.ChangePasswordAction;
import by.dorozhko.poputka.controller.action.autorized_user.administrator.DisplayAllUsers;
import by.dorozhko.poputka.controller.action.autorized_user.user.*;
import by.dorozhko.poputka.controller.action.autorized_user.ViewProfilePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class ActionProvider {

    private final Logger logger = LogManager.getLogger(getClass().getName());
    private static final ActionProvider INSTANCE = new ActionProvider();

    private ActionProvider() {

    }

    public static ActionProvider getInstance() {
        return INSTANCE;
    }

    public Action getAction(final String action) {
        Action actionByQuery = null;
        switch (action) {
            case "/listOfUsers":
                actionByQuery = new DisplayAllUsers();
                break;
            case "/listOfJourneys":
                actionByQuery = new JourneyList();
                break;
            case "/loginPage":
                actionByQuery = new LoginPage();
                break;
            case "/autorisation":
                actionByQuery = new AutorisationAction();
                break;
            case "/logout":
                actionByQuery = new LogOutAction();
                break;
            case "/registrationPage":
                actionByQuery = new RegistrationPage();
                break;
            case "/createAccount":
                actionByQuery = new CreateAccount();
                break;
            case "/editCar":
            case "/addCar":
                actionByQuery = new AddCarPage();
                break;
            case "/saveCar":
                actionByQuery = new SaveCar();
                break;
            case "/deleteCar":
                actionByQuery = new DeleteCarAction();
                break;
            case "/editProfile":
                actionByQuery = new EditProfilePage();
                break;
            case "/deleteUser":
                actionByQuery = new DeleteProfileAction();
                break;
            case "/viewUserProfile":
                actionByQuery = new ViewProfilePage();
                break;
            case "/changePassword":
                actionByQuery = new ChangePasswordAction();
                break;
            case "/updateProfile":
                actionByQuery = new UpdateProfileAction();
                break;
            default:
                actionByQuery = new MainPage();
                logger.warn("Default, show main page");
        }
        return actionByQuery;
    }
}
