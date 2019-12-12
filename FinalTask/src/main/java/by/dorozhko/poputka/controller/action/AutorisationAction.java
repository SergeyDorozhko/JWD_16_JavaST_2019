package by.dorozhko.poputka.controller.action;

import by.dorozhko.poputka.entity.User;
import by.dorozhko.poputka.services.ServiceFactory;
import by.dorozhko.poputka.services.UserService;
import by.dorozhko.poputka.services.exception.ExceptionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ResourceBundle;

public class AutorisationAction extends AllUsersAction {
    private final Logger logger = LogManager.getLogger(getClass().getName());

    private static final String SUCCESSFUL_REDIRECT = "/main.html";
    private static final String ERROR_REDIRECT = "/loginPage.html";

    private static final String AUTHORIZED_USER_ATTRIBUTE = "authorizedUser";
    private static final String FIELD_IS_EMPTY_ERROR_MESSAGE = "back.errors.fieldIsEmptyError";
    private static final String INCORRECT_LOGIN_OR_PASSWORD_ERROR_MESSAGE = "back.errors.incorrectLoginOrPassword";

    private static final String INVALID_CHARACTERS_IN_LOGIN_OR_PASSWORD = "invalid characters in the password or login";
    private static final String INVALID_CHARACTERS_IN_LOGIN_OR_PASSWORD_MESSAGE = "back.errors.invalidCharactersInThePasswordOrLogin";


    private static final String LOGIN_ATTRIBUTE = "login";
    private static final String PASSWORD_ATTRIBUTE = "password";
    private static final String ERROR_LOGIN_ATTRIBUTE = "errorLogin";
    private static final String ERROR_PASSWORD_ATTRIBUTE = "errorPassword";
    private static final String USER_LOGIN_ATTRIBUTE = "userLogin";

    private String login;
    private String password;
    private HttpSession session;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        session = request.getSession(false);

        UserService userService
                = ServiceFactory.getInstance().getUserService();
        getAllAttributes(request);
        User user = null;
        ResourceBundle resourceBundle = takeLocale(request);
        if (checkData(resourceBundle)) {
            try {
                user = userService.singIn(login, password);
            } catch (ExceptionService exceptionService) {
                logger.error(exceptionService);
                if (exceptionService.getMessage().equals(INVALID_CHARACTERS_IN_LOGIN_OR_PASSWORD)) {
                    session.setAttribute(ERROR_LOGIN_ATTRIBUTE, resourceBundle.getString(INVALID_CHARACTERS_IN_LOGIN_OR_PASSWORD_MESSAGE));
                    return request.getContextPath() + ERROR_REDIRECT;
                }
            }
        }
        if (user != null) {
            session.setAttribute(AUTHORIZED_USER_ATTRIBUTE,
                    user);
            return request.getContextPath() + SUCCESSFUL_REDIRECT;
        }
        setUserInputData();
        session.setAttribute(ERROR_LOGIN_ATTRIBUTE, resourceBundle.getString(INCORRECT_LOGIN_OR_PASSWORD_ERROR_MESSAGE));

        return request.getContextPath() + ERROR_REDIRECT;
    }

    private void getAllAttributes(HttpServletRequest request) {
        login = request.getParameter(LOGIN_ATTRIBUTE);
        password = request.getParameter(PASSWORD_ATTRIBUTE);
        logger.debug("all params take ok");
    }

    private boolean checkData(ResourceBundle resourceBundle) {
        logger.debug("check data start");
        int countErrors = 0;
        if (login.length() == 0) {
            session.setAttribute(ERROR_LOGIN_ATTRIBUTE, resourceBundle.getString(FIELD_IS_EMPTY_ERROR_MESSAGE));
            countErrors++;
        }

        if (password.length() == 0) {
            session.setAttribute(ERROR_PASSWORD_ATTRIBUTE, resourceBundle.getString(FIELD_IS_EMPTY_ERROR_MESSAGE));
            countErrors++;
        }

        logger.debug(String.format("find %d errors", countErrors));
        return countErrors == 0;
    }

    private void setUserInputData() {
        session.setAttribute(USER_LOGIN_ATTRIBUTE, login);
    }

}
