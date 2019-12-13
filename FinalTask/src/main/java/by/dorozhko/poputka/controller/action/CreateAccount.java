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
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;

public class CreateAccount extends AllUsersAction {
    private final Logger logger = LogManager.getLogger(getClass().getName());

    private static final String SUCCESSFUL_REDIRECT = "/viewUserProfile.html";

    private static final String ERROR_REDIRECT = "/registrationPage.html";

    private static final String ERROR_LOGIN_ATTRIBUTE = "errorLogin";
    private static final String ERROR_FIRST_NAME_ATTRIBUTE = "errorFirstName";
    private static final String ERROR_LAST_NAME_ATTRIBUTE = "errorLastName";
    private static final String ERROR_EMAIL_ATTRIBUTE = "errorEmail";
    private static final String ERROR_PASSWORD_ATTRIBUTE = "errorPassword";
    private static final String ERROR_BIRTHDAY_ATTRIBUTE = "errorBirthday";
    private static final String ERROR_PHONE_ATTRIBUTE = "errorPhoneNumber";
    private static final String ERROR_COUNTRY_ATTRIBUTE = "errorCountry";
    private static final String ERROR_PASSPORT_NUMBER_ATTRIBUTE = "errorPassportNumber";
    private static final String ERROR_PASSPORT_DATE_ATTRIBUTE = "errorPassportDate";
    private static final String ERROR_SEX_ATTRIBUTE = "errorSex";
    private static final String UNKNOWN_ERROR_ATTRIBUTE = "unknownError";

    private static final String LOGIN_ATTRIBUTE = "login";
    private static final String FIRST_NAME_ATTRIBUTE = "firstName";
    private static final String LAST_NAME_ATTRIBUTE = "lastName";
    private static final String EMAIL_ATTRIBUTE = "email";
    private static final String PASSWORD_ATTRIBUTE = "password";
    private static final String COMFIRM_PASSWORD_ATTRIBUTE = "confirmPassword";
    private static final String BIRTHDAY_ATTRIBUTE = "birthDate";
    private static final String PHONE_NUMBER_ATTRIBUTE = "phoneNumber";
    private static final String COUNTRY_ATTRIBUTE = "country";
    private static final String PASSPORT_NUMBER_ATTRIBUTE = "passportNumber";
    private static final String PASSPORT_DATE_ATTRIBUTE = "passportDate";
    private static final String SEX_ATTRIBUTE = "sex";

    private static final String USER_LOGIN_ATTRIBUTE = "userLogin";
    private static final String USER_FIRST_NAME_ATTRIBUTE = "userFirstName";
    private static final String USER_LAST_NAME_ATTRIBUTE = "userLastName";
    private static final String USER_EMAIL_ATTRIBUTE = "userEmail";
    private static final String USER_BIRTHDAY_ATTRIBUTE = "userBirthday";
    private static final String USER_PHONE_ATTRIBUTE = "userPhoneNumber";
    private static final String USER_COUNTRY_ATTRIBUTE = "userCountry";
    private static final String USER_PASSPORT_NUMBER_ATTRIBUTE = "userPassportNumber";
    private static final String USER_PASSPORT_DATE_ATTRIBUTE = "userPassportDate";
    private static final String USER_SEX_ATTRIBUTE = "userSex";

    private static final String AUTHORIZED_USER_ATTRIBUTE = "authorizedUser";

    private static final String FIELD_IS_EMPTY_ERROR_MESSAGE = "back.errors.fieldIsEmptyError";
    private static final String FIELD_FORMAT_ERROR_MESSAGE = "back.errors.fieldFormatError";
    private static final String DUBLICATED_LOGIN_ERROR_MESSAGE = "back.errors.dublicatedLogin";
    private static final String DUBLICATED_PASSPORT_ERROR_MESSAGE = "back.errors.dublicatedPassport";
    private static final String DUBLICATED_PHONE_ERROR_MESSAGE = "back.errors.dublicatedPhone";
    private static final String DUBLICATED_EMAIL_ERROR_MESSAGE = "back.errors.dublicatedEmail";
    private static final String UNKNOWN_ERROR_MESSAGE = "back.errors.unknownError";


    private static final String INVALID_LOGIN_FORMAT = "invalid pseudonym format";
    private static final String INVALID_PASSWORD_FORMAT = "invalid password format";
    private static final String INVALID_NAME_FORMAT = "invalid name format";
    private static final String INVALID_SURNAME_FORMAT = "invalid surname format";
    private static final String INVALID_EMAIL_FORMAT = "invalid e-mail format";
    private static final String INVALID_BIRTHDAY_DATE_VALUE = "invalid birthday date value";
    private static final String INVALID_PHONE_FORMAT = "invalid tel. number format";
    private static final String INVALID_COUNTRY_ID_VALUE = "invalid country id";
    private static final String INVALID_PASSPORT_NUMBER_FORMAT = "invalid number of passport format";
    private static final String INVALID_PASSPORT_ISSUE_DATE_VALUE = "invalid passport date of issue value";
    private static final String INVALID_GENDER_ID_VALUE = "invalid gender id";


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
        logger.debug(String.format("login: %s", login));

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
            session.setAttribute(AUTHORIZED_USER_ATTRIBUTE,
                    regestedUser);
            logger.debug(String.format("USER NOT NULL : %s", regestedUser));

            return request.getContextPath() + SUCCESSFUL_REDIRECT;
        }
        setUserInputData();
        return request.getContextPath() + ERROR_REDIRECT;
    }

    private void getAllAttributes(HttpServletRequest request) {
        login = request.getParameter(LOGIN_ATTRIBUTE);
        firstName = request.getParameter(FIRST_NAME_ATTRIBUTE);
        lastName = request.getParameter(LAST_NAME_ATTRIBUTE);
        email = request.getParameter(EMAIL_ATTRIBUTE);
        password = request.getParameter(PASSWORD_ATTRIBUTE);
        confirmPassword = request.getParameter(COMFIRM_PASSWORD_ATTRIBUTE);
        birthday = request.getParameter(BIRTHDAY_ATTRIBUTE);
        phoneNumber = request.getParameter(PHONE_NUMBER_ATTRIBUTE);
        country = request.getParameter(COUNTRY_ATTRIBUTE);
        passportNumber = request.getParameter(PASSPORT_NUMBER_ATTRIBUTE);
        passportDate = request.getParameter(PASSPORT_DATE_ATTRIBUTE);
        sex = request.getParameter(SEX_ATTRIBUTE);

        logger.debug("all params take ok");
    }

    private boolean checkData(ResourceBundle resourceBundle) {
        logger.debug("check data start");
        int countErrors = 0;

        if (login == null || login.length() == 0) {
            session.setAttribute(ERROR_LOGIN_ATTRIBUTE, resourceBundle.getString(FIELD_IS_EMPTY_ERROR_MESSAGE));
            countErrors++;
        }
        if (firstName == null || firstName.length() == 0) {
            session.setAttribute(ERROR_FIRST_NAME_ATTRIBUTE, resourceBundle.getString(FIELD_IS_EMPTY_ERROR_MESSAGE));
            countErrors++;
        }
        if (lastName == null || lastName.length() == 0) {
            session.setAttribute(ERROR_LAST_NAME_ATTRIBUTE, resourceBundle.getString(FIELD_IS_EMPTY_ERROR_MESSAGE));
            countErrors++;
        }
        if (email == null || email.length() == 0) {
            session.setAttribute(ERROR_EMAIL_ATTRIBUTE, resourceBundle.getString(FIELD_IS_EMPTY_ERROR_MESSAGE));
            countErrors++;
        }

        if (password == null || password.length() == 0 || confirmPassword == null || confirmPassword.length() == 0) {
            session.setAttribute(ERROR_PASSWORD_ATTRIBUTE, resourceBundle.getString(FIELD_IS_EMPTY_ERROR_MESSAGE));
            countErrors++;
        } else {
            if (!password.equals(confirmPassword)) {
                session.setAttribute(ERROR_PASSWORD_ATTRIBUTE, resourceBundle.getString(FIELD_IS_EMPTY_ERROR_MESSAGE));
                countErrors++;
            }
        }
        if (birthday == null || birthday.length() == 0) {
            session.setAttribute(ERROR_BIRTHDAY_ATTRIBUTE, resourceBundle.getString(FIELD_IS_EMPTY_ERROR_MESSAGE));
            countErrors++;
        } else {
            try {
                LocalDate.parse(birthday);
            } catch (DateTimeParseException ex) {
                logger.error(ex);
                session.setAttribute(ERROR_BIRTHDAY_ATTRIBUTE, resourceBundle.getString(FIELD_FORMAT_ERROR_MESSAGE));
                countErrors++;
            }

        }
        if (phoneNumber == null || phoneNumber.length() == 0) {
            session.setAttribute(ERROR_PHONE_ATTRIBUTE, resourceBundle.getString(FIELD_IS_EMPTY_ERROR_MESSAGE));
            countErrors++;
        }
        if (country == null || country.length() == 0) {
            session.setAttribute(ERROR_COUNTRY_ATTRIBUTE, resourceBundle.getString(FIELD_IS_EMPTY_ERROR_MESSAGE));
            countErrors++;
        } else {
            try {
                Integer.parseInt(country);
            } catch (NumberFormatException ex) {
                logger.error(ex);
                session.setAttribute(ERROR_COUNTRY_ATTRIBUTE, resourceBundle.getString(FIELD_FORMAT_ERROR_MESSAGE));
                countErrors++;
            }
        }
        if (passportNumber == null || passportNumber.length() == 0) {
            session.setAttribute(ERROR_PASSPORT_NUMBER_ATTRIBUTE, resourceBundle.getString(FIELD_IS_EMPTY_ERROR_MESSAGE));
            countErrors++;
        }
        if (passportDate == null || passportDate.length() == 0) {
            session.setAttribute(ERROR_PASSPORT_DATE_ATTRIBUTE, resourceBundle.getString(FIELD_IS_EMPTY_ERROR_MESSAGE));
            countErrors++;
        } else {
            try {
                LocalDate.parse(passportDate);
            } catch (DateTimeParseException ex) {
                logger.error(ex);
                session.setAttribute(ERROR_PASSPORT_DATE_ATTRIBUTE, resourceBundle.getString(FIELD_FORMAT_ERROR_MESSAGE));
                countErrors++;
            }

        }
        if (sex == null || sex.length() == 0) {
            session.setAttribute(ERROR_SEX_ATTRIBUTE, resourceBundle.getString(FIELD_IS_EMPTY_ERROR_MESSAGE));
            countErrors++;
        } else {
            try {
                Integer.parseInt(sex);
            } catch (NumberFormatException ex) {
                logger.error(ex);
                session.setAttribute(ERROR_SEX_ATTRIBUTE, resourceBundle.getString(FIELD_FORMAT_ERROR_MESSAGE));
                countErrors++;
            }

        }
        logger.debug(String.format("find %d errors", countErrors));
        return countErrors == 0;
    }

    private void getErrorMessage(String msg, ResourceBundle resourceBundle) {

        if (msg.contains("login")) {
            session.setAttribute(ERROR_LOGIN_ATTRIBUTE, resourceBundle
                    .getString(DUBLICATED_LOGIN_ERROR_MESSAGE));
        } else if (msg.contains("passport_number")) {
            session.setAttribute(ERROR_PASSPORT_NUMBER_ATTRIBUTE,
                    resourceBundle.getString(DUBLICATED_PASSPORT_ERROR_MESSAGE));
        } else if (msg.contains("phone")) {
            session.setAttribute(ERROR_PHONE_ATTRIBUTE,
                    resourceBundle.getString(DUBLICATED_PHONE_ERROR_MESSAGE));
        } else if (msg.contains("email")) {
            session.setAttribute(ERROR_EMAIL_ATTRIBUTE,
                    resourceBundle.getString(DUBLICATED_EMAIL_ERROR_MESSAGE));
        } else if (msg.equals(INVALID_LOGIN_FORMAT)) {
            session.setAttribute(ERROR_LOGIN_ATTRIBUTE,
                    resourceBundle.getString(FIELD_FORMAT_ERROR_MESSAGE));
        } else if (msg.equals(INVALID_PASSWORD_FORMAT)) {
            session.setAttribute(ERROR_PASSWORD_ATTRIBUTE,
                    resourceBundle.getString(FIELD_FORMAT_ERROR_MESSAGE));
        } else if (msg.equals(INVALID_NAME_FORMAT)) {
            session.setAttribute(ERROR_FIRST_NAME_ATTRIBUTE,
                    resourceBundle.getString(FIELD_FORMAT_ERROR_MESSAGE));
        } else if (msg.equals(INVALID_SURNAME_FORMAT)) {
            session.setAttribute(ERROR_LAST_NAME_ATTRIBUTE,
                    resourceBundle.getString(FIELD_FORMAT_ERROR_MESSAGE));
        } else if (msg.equals(INVALID_EMAIL_FORMAT)) {
            session.setAttribute(ERROR_EMAIL_ATTRIBUTE,
                    resourceBundle.getString(FIELD_FORMAT_ERROR_MESSAGE));
        } else if (msg.equals(INVALID_BIRTHDAY_DATE_VALUE)) {
            session.setAttribute(ERROR_BIRTHDAY_ATTRIBUTE,
                    resourceBundle.getString(FIELD_FORMAT_ERROR_MESSAGE));
        } else if (msg.equals(INVALID_PHONE_FORMAT)) {
            session.setAttribute(ERROR_PHONE_ATTRIBUTE,
                    resourceBundle.getString(FIELD_FORMAT_ERROR_MESSAGE));
        } else if (msg.equals(INVALID_COUNTRY_ID_VALUE)) {
            session.setAttribute(ERROR_COUNTRY_ATTRIBUTE,
                    resourceBundle.getString(FIELD_FORMAT_ERROR_MESSAGE));
        } else if (msg.equals(INVALID_PASSPORT_NUMBER_FORMAT)) {
            session.setAttribute(ERROR_PASSPORT_NUMBER_ATTRIBUTE,
                    resourceBundle.getString(FIELD_FORMAT_ERROR_MESSAGE));
        } else if (msg.equals(INVALID_PASSPORT_ISSUE_DATE_VALUE)) {
            session.setAttribute(ERROR_PASSPORT_DATE_ATTRIBUTE,
                    resourceBundle.getString(FIELD_FORMAT_ERROR_MESSAGE));
        } else if (msg.equals(INVALID_GENDER_ID_VALUE)) {
            session.setAttribute(ERROR_SEX_ATTRIBUTE,
                    resourceBundle.getString(FIELD_FORMAT_ERROR_MESSAGE));
        } else {
            session.setAttribute(UNKNOWN_ERROR_ATTRIBUTE,
                    resourceBundle.getString(UNKNOWN_ERROR_MESSAGE));
        }

    }

    private void setUserInputData() {
        session.setAttribute(USER_LOGIN_ATTRIBUTE, login);
        session.setAttribute(USER_FIRST_NAME_ATTRIBUTE, firstName);
        session.setAttribute(USER_LAST_NAME_ATTRIBUTE, lastName);
        session.setAttribute(USER_EMAIL_ATTRIBUTE, email);
        session.setAttribute(USER_BIRTHDAY_ATTRIBUTE, birthday);
        session.setAttribute(USER_PHONE_ATTRIBUTE, phoneNumber);
        session.setAttribute(USER_COUNTRY_ATTRIBUTE, country);
        session.setAttribute(USER_PASSPORT_NUMBER_ATTRIBUTE, passportNumber);
        session.setAttribute(USER_PASSPORT_DATE_ATTRIBUTE, passportDate);
        session.setAttribute(USER_SEX_ATTRIBUTE, sex);
    }
}
