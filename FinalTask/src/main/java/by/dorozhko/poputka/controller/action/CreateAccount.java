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

        UserService userService
                = ServiceFactory.getInstance().getUserService();

        String login = request.getParameter("login");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String birthday = request.getParameter("birthDate");
        String phoneNumber = request.getParameter("phoneNumber");
        String country = request.getParameter("country");
        String passportNumber = request.getParameter("passportNumber");
        String passportDate = request.getParameter("passportDate");
        String sex = request.getParameter("sex");

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
            logger.error(String.format("sqlmsg : %s",
                    exceptionService.getMessage()));
            String msg = exceptionService.getMessage();
            if (msg.contains("login")) {
                request.getSession().setAttribute("duplicateLogin", "пользователь с таким логином существует");
            } else if (msg.contains("passport_number")) {
                request.getSession().setAttribute("duplicatePassport", "пользователь с таким паспортом существует");
            } else if (msg.contains("phone")) {
                request.getSession().setAttribute("duplicatePhone", "пользователь с таким номером телефона существует");
            } else if (msg.contains("email")) {
                request.getSession().setAttribute("duplicateEmail", "пользователь с такой почной существует");
            } else {
                request.getSession().setAttribute("unknownError", "что-то пошло не так, попробуйте снова");
            }

            request.getSession().setAttribute("userLogin", login);
            request.getSession().setAttribute("userFirstName", firstName);
            request.getSession().setAttribute("userLastName", lastName);
            request.getSession().setAttribute("userEmail", email);
            request.getSession().setAttribute("userBirthday", birthday);
            request.getSession().setAttribute("userPhoneNumber", phoneNumber);
            request.getSession().setAttribute("userCountry", country);
            request.getSession().setAttribute("userPassportNumber", passportNumber);
            request.getSession().setAttribute("userPassportDate", passportDate);
            request.getSession().setAttribute("userSex", sex);

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
