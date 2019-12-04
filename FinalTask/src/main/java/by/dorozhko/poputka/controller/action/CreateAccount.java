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

public class CreateAccount extends AllUsersAction {
    private final Logger logger = LogManager.getLogger(getClass().getName());

    private String login;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String confirmPassword;
    private String birthday;
    private String phoneNumber;
    private String country;
    private String passportNumber;
    private String passportDate;
    private String sex;
    private HttpSession session;


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("start CreateAccount ok");

        session = request.getSession(false);

        UserService userService
                = ServiceFactory.getInstance().getUserService();

        getAllAttributes(request);
        User regestedUser = null;
        ResourceBundle resourceBundle = takeLocale(request);
        if (checkData(resourceBundle)) {
            logger.debug("passwords are equal");
            User user = new User(login, password, firstName, lastName, sex, birthday, country, passportNumber, passportDate, phoneNumber, email);

            logger.debug("user created");
            try {
                regestedUser = userService.add(user);
            } catch (ExceptionService exceptionService) {
                logger.error(String.format("sqlmsg : %s",
                        exceptionService.getMessage()));
                getErrorMessage(exceptionService.getMessage(), resourceBundle);
            }

            logger.debug("get answer from service");
        }

        if (regestedUser != null) {
            session.setAttribute("authorizedUser",
                    regestedUser);
            logger.debug(String.format("USER NOT NULL : %s", regestedUser));

            return request.getContextPath() + "/viewUserProfile.html";
        }
        setUserInputData();
        return request.getContextPath() + "/registrationPage.html";
    }

    private void getAllAttributes(HttpServletRequest request) {
        login = request.getParameter("login");
        firstName = request.getParameter("firstName");
        lastName = request.getParameter("lastName");
        email = request.getParameter("email");
        password = request.getParameter("password");
        confirmPassword = request.getParameter("confirmPassword");
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
        logger.debug(String.format("login: %s", login));
        logger.debug(String.format("data: %s", passportDate));
        logger.debug(String.format("sex: %s", sex));

        if (login.length() == 0) {
            session.setAttribute("errorLogin",  resourceBundle.getString("back.errors.fieldIsEmptyError"));
            countErrors++;
        }
        if (firstName.length() == 0) {
            session.setAttribute("errorFirstName",  resourceBundle.getString("back.errors.fieldIsEmptyError"));
            countErrors++;
        }
        if (lastName.length() == 0) {
            session.setAttribute("errorLastName",  resourceBundle.getString("back.errors.fieldIsEmptyError"));
            countErrors++;
        }
        if (email.length() == 0) {
            session.setAttribute("errorEmail",  resourceBundle.getString("back.errors.fieldIsEmptyError"));
            countErrors++;
        }
        if (password.length() == 0 || confirmPassword.length() == 0) {
            session.setAttribute("errorPassword",  resourceBundle.getString("back.errors.fieldIsEmptyError"));
            countErrors++;
        } else {
            if (!password.equals(confirmPassword)) {
                session.setAttribute("errorPassword",  resourceBundle.getString("back.errors.fieldIsEmptyError"));
                countErrors++;
            }
        }
        if (birthday.length() == 0) {
            session.setAttribute("errorBirthday",  resourceBundle.getString("back.errors.fieldIsEmptyError"));
            countErrors++;
        }
        if (phoneNumber.length() == 0) {
            session.setAttribute("errorPhoneNumber",  resourceBundle.getString("back.errors.fieldIsEmptyError"));
            countErrors++;
        }
        if (country.length() == 0) {
            session.setAttribute("errorCountry",  resourceBundle.getString("back.errors.fieldIsEmptyError"));
            countErrors++;
        }
        if (passportNumber.length() == 0) {
            session.setAttribute("errorPassportNumber",  resourceBundle.getString("back.errors.fieldIsEmptyError"));
            countErrors++;
        }
        if (passportDate.length() == 0) {
            session.setAttribute("errorPassportDate",  resourceBundle.getString("back.errors.fieldIsEmptyError"));
            countErrors++;
        }
        if (sex.length() == 0) {
            session.setAttribute("errorSex",  resourceBundle.getString("back.errors.fieldIsEmptyError"));
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

    private void setUserInputData() {
        session.setAttribute("userLogin", login);
        session.setAttribute("userFirstName", firstName);
        session.setAttribute("userLastName", lastName);
        session.setAttribute("userEmail", email);
        session.setAttribute("userBirthday", birthday);
        session.setAttribute("userPhoneNumber", phoneNumber);
        session.setAttribute("userCountry", country);
        session.setAttribute("userPassportNumber", passportNumber);
        session.setAttribute("userPassportDate", passportDate);
        session.setAttribute("userSex", sex);
    }
}
