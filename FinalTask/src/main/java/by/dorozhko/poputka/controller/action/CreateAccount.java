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

public class CreateAccount extends AllUsersAction {
    private final Logger logger = LogManager.getLogger(getClass().getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("start CreateAccount ok");

        HttpSession session = request.getSession(false);
        logger.debug("take session ok");

        UserService userService
                = ServiceFactory.getInstance().getUserService();

        String login = request.getParameter("login");
        logger.debug(String.format("take login ok - %s", login));

        String firstName = request.getParameter("firstName");
        logger.debug(String.format("take firstName ok - %s", firstName));

        String lastName = request.getParameter("lastName");
        logger.debug(String.format("take lastName ok - %s", lastName));

        String email = request.getParameter("email");
        logger.debug(String.format("take email ok - %s", email));

        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        logger.debug(String.format("take password ok - %s", "XXXX"));

        String birthday = request.getParameter("birthDate");
        logger.debug(String.format("take birthday ok - %s", birthday));

        String phoneNumber = request.getParameter("phoneNumber");
        logger.debug(String.format("take phoneNumber ok - %s", phoneNumber));

        String country = request.getParameter("country");
        logger.debug(String.format("take country ok - %s", country));

        String passportNumber = request.getParameter("passportNumber");
        logger.debug(String.format("take passportNumber ok - %s", passportNumber));
        String passportDate = request.getParameter("passportDate");
        logger.debug(String.format("take passportDate ok - %s", passportDate));
        String sex2 = request.getParameter("sex");
        String sex = null;
        if (sex2 != null) {
            logger.debug("sex taken");
            logger.debug(sex2);

            logger.debug(sex2.getClass());
            sex = (String) sex2;
        } else {
            sex = "1";
            logger.debug(" sex taken default ");

        }
        logger.debug("all params take ok");

        if (!password.equals(confirmPassword)) {
            request.getSession().setAttribute("passwordNotEqual", "passwordsNotEqual");

            return request.getContextPath() + "/registrationPage.html";
        }
        logger.debug("passwords are equal");
        User user = new User(login, password, firstName, lastName, sex, birthday, country, passportNumber, passportDate, phoneNumber, email);
        User regestedUser = null;
        logger.debug("user created");
        try {
            regestedUser = userService.add(user);
        } catch (ExceptionService exceptionService) {
            exceptionService.printStackTrace();
        }

        logger.debug("get answer from service - OK");
        if (regestedUser != null) {
            session.setAttribute("authorizedUser",
                    regestedUser);
            logger.debug(String.format("USER NOT NULL : %s", regestedUser));

            return request.getContextPath() + "/main.html";
        }

  //      request.getSession().setAttribute("SecurityMessage", "Неверный логин или пароль");

        return request.getContextPath() + "/registrationPage.html";
    }
}
