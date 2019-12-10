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
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;

public class UpdateProfileAction extends UserAction {
    private final Logger logger = LogManager.getLogger(getClass().getName());

    private static final String SUCCESSFUL_REDIRECT = "/viewUserProfile.html";

    private static final String ERROR_REDIRECT = "/editProfile.html";

    private static final String ERROR_LOGIN_ATTRIBUTE = "errorLogin";
    private static final String ERROR_FIRST_NAME_ATTRIBUTE = "errorFirstName";
    private static final String ERROR_LAST_NAME_ATTRIBUTE = "errorLastName";
    private static final String ERROR_EMAIL_ATTRIBUTE = "errorEmail";
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
    private static final String BIRTHDAY_ATTRIBUTE = "birthDate";
    private static final String PHONE_NUMBER_ATTRIBUTE = "phoneNumber";
    private static final String COUNTRY_ATTRIBUTE = "country";
    private static final String PASSPORT_NUMBER_ATTRIBUTE = "passportNumber";
    private static final String PASSPORT_DATE_ATTRIBUTE = "passportDate";
    private static final String SEX_ATTRIBUTE = "sex";

    private static final String AUTHORIZED_USER_ATTRIBUTE = "authorizedUser";

    private static final String FIELD_IS_EMPTY_ERROR_MESSAGE = "back.errors.fieldIsEmptyError";
    private static final String FIELD_FORMAT_ERROR_MESSAGE = "back.errors.fieldFormatError";
    private static final String DUBLICATED_LOGIN_ERROR_MESSAGE = "back.errors.dublicatedLogin";
    private static final String DUBLICATED_PASSPORT_ERROR_MESSAGE = "back.errors.dublicatedPassport";
    private static final String DUBLICATED_PHONE_ERROR_MESSAGE = "back.errors.dublicatedPhone";
    private static final String DUBLICATED_EMAIL_ERROR_MESSAGE = "back.errors.dublicatedEmail";
    private static final String UNKNOWN_ERROR_MESSAGE = "back.errors.unknownError";




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
            User user = new User(login, firstName, lastName, sex, birthday,
                    country, passportNumber, passportDate, phoneNumber, email);
            User actionUser = (User) session.getAttribute(AUTHORIZED_USER_ATTRIBUTE);

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

            return request.getContextPath() + SUCCESSFUL_REDIRECT;
        }
        return request.getContextPath() + ERROR_REDIRECT;
    }

    private void getAllAttributes(HttpServletRequest request) {
        login = request.getParameter(LOGIN_ATTRIBUTE);
        firstName = request.getParameter(FIRST_NAME_ATTRIBUTE);
        lastName = request.getParameter(LAST_NAME_ATTRIBUTE);
        email = request.getParameter(EMAIL_ATTRIBUTE);
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
        if (birthday == null || birthday.length() == 0) {
            session.setAttribute(ERROR_BIRTHDAY_ATTRIBUTE,  resourceBundle.getString(FIELD_IS_EMPTY_ERROR_MESSAGE));
            countErrors++;
        }  else {
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
            session.setAttribute(ERROR_COUNTRY_ATTRIBUTE,  resourceBundle.getString(FIELD_IS_EMPTY_ERROR_MESSAGE));
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
            session.setAttribute(ERROR_PASSPORT_DATE_ATTRIBUTE,  resourceBundle.getString(FIELD_IS_EMPTY_ERROR_MESSAGE));
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
        } else {
            session.setAttribute(UNKNOWN_ERROR_ATTRIBUTE,
                    resourceBundle.getString(UNKNOWN_ERROR_MESSAGE));
        }

    }

}
