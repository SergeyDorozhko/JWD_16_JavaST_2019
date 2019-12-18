package by.dorozhko.poputka.controller.action.autorized_user.user;

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

public class DeleteProfileAction extends UserAction {
    private final Logger logger = LogManager.getLogger(getClass().getName());

    private static final String SUCCESSFUL_REDIRECT_URL = "/main.html";
    private static final String ERROR_REDIRECT_URL = "/viewUserProfile.html";

    private static final String AUTHORIZED_USER_ATTRIBUTE = "authorizedUser";

    private static final String PASSWORD_ATTRIBUTE = "password";
    private static final String UNKNOWN_ERROR_ATTRIBUTE = "unknownError";

    private static final String INCORRECT_PASSWORD_MESSAGE = "back.errors.incorrectPassword";


    private String password;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        logger.debug("start delete Account ok");

        HttpSession session = request.getSession(false);

        UserService userService
                = ServiceFactory.getInstance().getUserService();

        getAllAttributes(request);
        if (checkData()) {
            User actionUser = (User) session.getAttribute(AUTHORIZED_USER_ATTRIBUTE);
            User user = new User();
            user.setId(actionUser.getId());
            user.setLogin(actionUser.getLogin());
            user.setPassword(password);
            logger.debug(String.format("user data collected: %s", user));
            try {
                logger.debug("start delete user");
                boolean result = userService.deleteUser(user);
                logger.debug("take answer from service");
                if (result) {
                    session.invalidate();
                    return request.getContextPath() + SUCCESSFUL_REDIRECT_URL;
                }

            } catch (ExceptionService exceptionService) {
                logger.error(String.format("sqlmsg : %s",
                        exceptionService.getMessage()));

            }
            ResourceBundle resourceBundle = takeLocale(request);
            session.setAttribute(UNKNOWN_ERROR_ATTRIBUTE,
                    resourceBundle.getString(INCORRECT_PASSWORD_MESSAGE));
        }

        return request.getContextPath() + ERROR_REDIRECT_URL;

    }


    private void getAllAttributes(HttpServletRequest request) {
        password = request.getParameter(PASSWORD_ATTRIBUTE);

        logger.debug("all params take ok");
    }

    private boolean checkData() {
        logger.debug("check data start");
        return password != null && password.length() != 0;
    }


}
