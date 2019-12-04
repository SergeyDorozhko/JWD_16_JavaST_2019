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

public class UpdateProfileAction extends UserAction {
    private final Logger logger = LogManager.getLogger(getClass().getName());

    private String login;
    private String firstName;
    private String lastName;
    private String email;
    private String birthday;
    private String phoneNumber;
    private String country;
    private String passportNumber;
    private String passportDate;
    private String sex;
    private HttpSession session;


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("start update Account ok");

        session = request.getSession(false);

        UserService userService
                = ServiceFactory.getInstance().getUserService();

        getAllAttributes(request);
        User updatedUser = null;
        ResourceBundle resourceBundle = takeLocale(request);
        if (checkData(resourceBundle)) {
            User user = new User(login, firstName, lastName, sex, birthday, country, passportNumber, passportDate, phoneNumber, email);
            User actionUser = (User) session.getAttribute("authorizedUser");

            user.setId(actionUser.getId());
            logger.debug(String.format("user created: %s", user));
            try {
                updatedUser = userService.update(user);
            } catch (ExceptionService exceptionService) {
                logger.error(String.format("sqlmsg : %s",
                        exceptionService.getMessage()));
                getErrorMessage(exceptionService.getMessage(), resourceBundle);
            }

            logger.debug("get answer from service");
        }

        if (updatedUser != null) {

            logger.debug(String.format("USER NOT NULL : %s", updatedUser));

            return request.getContextPath() + "/viewUserProfile.html";
        }
        return request.getContextPath() + "/editProfile.html";
    }

    private void getAllAttributes(HttpServletRequest request) {
        login = request.getParameter("login");
        firstName = request.getParameter("firstName");
        lastName = request.getParameter("lastName");
        email = request.getParameter("email");
        birthday = request.getParameter("birthDate");
        phoneNumber = request.getParameter("phoneNumber");
        country = request.getParameter("country");
        passportNumber = request.getParameter("passportNumber");
        passportDate = request.getParameter("passportDate");
        sex = request.getParameter("sex");

        logger.debug("all params take ok");
    }

    private boolean checkData(ResourceBundle resourceBundle) {
        logger.debug("check data start");
        int countErrors = 0;


        if (login.length() == 0) {
            session.setAttribute("errorLogin", resourceBundle.getString("back.errors.fieldIsEmptyError"));
            countErrors++;
        }
        if (firstName.length() == 0) {
            session.setAttribute("errorFirstName", resourceBundle.getString("back.errors.fieldIsEmptyError"));
            countErrors++;
        }
        if (lastName.length() == 0) {
            session.setAttribute("errorLastName", resourceBundle.getString("back.errors.fieldIsEmptyError"));
            countErrors++;
        }
        if (email.length() == 0) {
            session.setAttribute("errorEmail", resourceBundle.getString("back.errors.fieldIsEmptyError"));
            countErrors++;
        }
        if (birthday.length() == 0) {
            session.setAttribute("errorBirthday", resourceBundle.getString("back.errors.fieldIsEmptyError"));
            countErrors++;
        }
        if (phoneNumber.length() == 0) {
            session.setAttribute("errorPhoneNumber", resourceBundle.getString("back.errors.fieldIsEmptyError"));
            countErrors++;
        }
        if (country.length() == 0) {
            session.setAttribute("errorCountry", resourceBundle.getString("back.errors.fieldIsEmptyError"));
            countErrors++;
        }
        if (passportNumber.length() == 0) {
            session.setAttribute("errorPassportNumber", resourceBundle.getString("back.errors.fieldIsEmptyError"));
            countErrors++;
        }
        if (passportDate.length() == 0) {
            session.setAttribute("errorPassportDate", resourceBundle.getString("back.errors.fieldIsEmptyError"));
            countErrors++;
        }
        if (sex.length() == 0) {
            session.setAttribute("errorSex", resourceBundle.getString("back.errors.fieldIsEmptyError"));
            countErrors++;
        }
        logger.debug(String.format("find %d errors", countErrors));
        return countErrors == 0;
    }

    private void getErrorMessage(String msg, ResourceBundle resourceBundle) {

        if (msg.contains("login")) {
            session.setAttribute("errorLogin", resourceBundle
                    .getString("back.errors.dublicatedLogin"));
        } else if (msg.contains("passport_number")) {
            session.setAttribute("errorPassportNumber",
                    resourceBundle.getString("back.errors.dublicatedPassport"));
        } else if (msg.contains("phone")) {
            session.setAttribute("errorPhoneNumber",
                    resourceBundle.getString("back.errors.dublicatedPhone"));
        } else if (msg.contains("email")) {
            session.setAttribute("errorEmail",
                    resourceBundle.getString("back.errors.dublicatedEmail"));
        } else {
            session.setAttribute("unknownError",
                    resourceBundle.getString("back.errors.unknownError"));
        }

    }

}
