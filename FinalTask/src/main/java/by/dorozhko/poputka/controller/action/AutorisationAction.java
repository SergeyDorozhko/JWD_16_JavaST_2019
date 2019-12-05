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
            }
        }
        if (user != null) {
            session.setAttribute("authorizedUser",
                    user);
            return request.getContextPath() + "/main.html";
        }
        setUserInputData();
        session.setAttribute("errorLogin", resourceBundle.getString("back.errors.incorrectLoginOrPassword"));

        return request.getContextPath() + "/loginPage.html";
    }

    private void getAllAttributes(HttpServletRequest request) {
        login = request.getParameter("login");
        password = request.getParameter("password");
        logger.debug("all params take ok");
    }

    private boolean checkData(ResourceBundle resourceBundle) {
        logger.debug("check data start");
        int countErrors = 0;
        if (login.length() == 0) {
            session.setAttribute("errorLogin", resourceBundle.getString("back.errors.fieldIsEmptyError"));
            countErrors++;
        }

        if (password.length() == 0) {
            session.setAttribute("errorPassword", resourceBundle.getString("back.errors.fieldIsEmptyError"));
            countErrors++;
        }

        logger.debug(String.format("find %d errors", countErrors));
        return countErrors == 0;
    }

    private void setUserInputData() {
        session.setAttribute("userLogin", login);
    }

}
