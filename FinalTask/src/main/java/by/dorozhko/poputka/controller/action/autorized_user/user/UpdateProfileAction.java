package by.dorozhko.poputka.controller.action.autorized_user.user;

import by.dorozhko.poputka.entity.ErrorMessageConficurator;
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
import java.util.HashSet;
import java.util.Set;

public class UpdateProfileAction extends UserAction {
    public static final String DUBLICATED_LOGIN = "login";
    public static final String DUBLICATED_PASSPORT_NUMBER = "passport_number";
    public static final String DUBLICATED_PHONE = "phone";
    public static final String DUBLICATED_EMAIL = "email";
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

    private static final String INVALID_LOGIN_FORMAT = "invalid pseudonym format";
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
        resourceBundle = takeLocale(request);
        if (checkData()) {
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
                getErrorMessage(exceptionService.getMessage());
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

    private boolean checkData() {
        logger.debug("check data start");
        int countErrors = 0;

        countErrors += checkValueIsExist(login, ERROR_LOGIN_ATTRIBUTE);
        countErrors += checkValueIsExist(firstName, ERROR_FIRST_NAME_ATTRIBUTE);
        countErrors += checkValueIsExist(lastName, ERROR_LAST_NAME_ATTRIBUTE);
        countErrors += checkValueIsExist(email, ERROR_EMAIL_ATTRIBUTE);
        countErrors += checkForDateValue(birthday, ERROR_BIRTHDAY_ATTRIBUTE);
        countErrors += checkValueIsExist(phoneNumber, ERROR_PHONE_ATTRIBUTE);
        countErrors += checkForIntegerValue(country, ERROR_COUNTRY_ATTRIBUTE);
        countErrors += checkValueIsExist(passportNumber, ERROR_PASSPORT_NUMBER_ATTRIBUTE);
        countErrors += checkForDateValue(passportDate, ERROR_PASSPORT_DATE_ATTRIBUTE);
        countErrors += checkForIntegerValue(sex, ERROR_SEX_ATTRIBUTE);

        logger.debug(String.format("find %d errors", countErrors));
        return countErrors == 0;
    }


    private int checkValueIsExist(String value, String attributeForErrorMessage) {
        int countErrors = 0;
        if (value == null || value.length() == 0) {
            session.setAttribute(attributeForErrorMessage,
                    resourceBundle.getString(FIELD_IS_EMPTY_ERROR_MESSAGE));
            countErrors++;
        }
        return countErrors;
    }


    private int checkForDateValue(String data, String attribute) {
        int countErrors = 0;
        if (data == null || data.length() == 0) {
            session.setAttribute(attribute,
                    resourceBundle.getString(FIELD_IS_EMPTY_ERROR_MESSAGE));
            countErrors++;
        } else {
            try {
                LocalDate.parse(data);
            } catch (DateTimeParseException ex) {
                logger.error(ex);
                session.setAttribute(attribute,
                        resourceBundle.getString(FIELD_FORMAT_ERROR_MESSAGE));
                countErrors++;
            }
        }
        return countErrors;
    }

    private int checkForIntegerValue(String data, String attribute) {
        int countErrors = 0;
        if (data == null || data.length() == 0) {
            session.setAttribute(attribute,
                    resourceBundle.getString(FIELD_IS_EMPTY_ERROR_MESSAGE));
            countErrors++;
        } else {
            try {
                Integer.parseInt(data);
            } catch (NumberFormatException ex) {
                logger.error(ex);
                session.setAttribute(attribute,
                        resourceBundle.getString(FIELD_FORMAT_ERROR_MESSAGE));
                countErrors++;
            }
        }
        return countErrors;
    }

    private void getErrorMessage(String msg) {
        Set<ErrorMessageConficurator> errorList = expectedMessages();

        boolean isFind = false;

        for (ErrorMessageConficurator errorMessageConficurator : errorList) {
            if (msg.contains(errorMessageConficurator.getExceptionMessage())) {
                session.setAttribute(errorMessageConficurator.getAttribute(),
                        resourceBundle.getString(errorMessageConficurator
                                .getMessageForUser()));
                isFind = true;
            }
        }
        if (!isFind) {
            session.setAttribute(UNKNOWN_ERROR_ATTRIBUTE,
                    resourceBundle.getString(UNKNOWN_ERROR_MESSAGE));
        }
    }

    private Set<ErrorMessageConficurator> expectedMessages() {

        Set<ErrorMessageConficurator> errorList = new HashSet<>();
        errorList.add(new ErrorMessageConficurator(DUBLICATED_LOGIN, ERROR_LOGIN_ATTRIBUTE, DUBLICATED_LOGIN_ERROR_MESSAGE));
        errorList.add(new ErrorMessageConficurator(DUBLICATED_PASSPORT_NUMBER, ERROR_PASSPORT_NUMBER_ATTRIBUTE, DUBLICATED_PASSPORT_ERROR_MESSAGE));
        errorList.add(new ErrorMessageConficurator(DUBLICATED_PHONE, ERROR_PHONE_ATTRIBUTE, DUBLICATED_PHONE_ERROR_MESSAGE));
        errorList.add(new ErrorMessageConficurator(DUBLICATED_EMAIL, ERROR_EMAIL_ATTRIBUTE, DUBLICATED_EMAIL_ERROR_MESSAGE));
        errorList.add(new ErrorMessageConficurator(INVALID_LOGIN_FORMAT, ERROR_LOGIN_ATTRIBUTE, FIELD_FORMAT_ERROR_MESSAGE));
        errorList.add(new ErrorMessageConficurator(INVALID_NAME_FORMAT, ERROR_FIRST_NAME_ATTRIBUTE, FIELD_FORMAT_ERROR_MESSAGE));
        errorList.add(new ErrorMessageConficurator(INVALID_SURNAME_FORMAT, ERROR_LAST_NAME_ATTRIBUTE, FIELD_FORMAT_ERROR_MESSAGE));
        errorList.add(new ErrorMessageConficurator(INVALID_EMAIL_FORMAT, ERROR_EMAIL_ATTRIBUTE, FIELD_FORMAT_ERROR_MESSAGE));
        errorList.add(new ErrorMessageConficurator(INVALID_BIRTHDAY_DATE_VALUE, ERROR_BIRTHDAY_ATTRIBUTE, FIELD_FORMAT_ERROR_MESSAGE));
        errorList.add(new ErrorMessageConficurator(INVALID_PHONE_FORMAT, ERROR_PHONE_ATTRIBUTE, FIELD_FORMAT_ERROR_MESSAGE));
        errorList.add(new ErrorMessageConficurator(INVALID_COUNTRY_ID_VALUE, ERROR_COUNTRY_ATTRIBUTE, FIELD_FORMAT_ERROR_MESSAGE));
        errorList.add(new ErrorMessageConficurator(INVALID_PASSPORT_NUMBER_FORMAT, ERROR_PASSPORT_NUMBER_ATTRIBUTE, FIELD_FORMAT_ERROR_MESSAGE));
        errorList.add(new ErrorMessageConficurator(INVALID_PASSPORT_ISSUE_DATE_VALUE, ERROR_PASSPORT_DATE_ATTRIBUTE, FIELD_FORMAT_ERROR_MESSAGE));
        errorList.add(new ErrorMessageConficurator(INVALID_GENDER_ID_VALUE, ERROR_SEX_ATTRIBUTE, FIELD_FORMAT_ERROR_MESSAGE));

        return errorList;
    }

}
