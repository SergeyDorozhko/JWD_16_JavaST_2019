package by.dorozhko.poputka.services.impl;

import by.dorozhko.poputka.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Validator {
    private final Logger logger = LogManager.getLogger(getClass().getName());
    private static final String LOGIN_PATTERN = "^[\\w\\d-]+$";
    private static final String PASSWORD_PATTERN = "^[\\wа-яА-Я\\d-+%$@!]+$";
    private static final String USER_NAME_AND_SURNAME_PATTERN = "^[a-zA-Zа-яА-Я-]+$";
    private static final String GENDER_PATTERN = "^[0-9]{1,2}$";
    private static final String COUNTRY_PATTERN = "^[0-9]{1,3}$";
    private static final String PASSPORT_NUMBER_PATTERN = "^[A-Z0-9]{1,10}$";
    private static final String PHONE_PATTERN = "^[0-9]{7-15}$";
    private static final String EMAIL_PATTERN = "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$";
    private Pattern pattern;
    private Matcher matcher;

    public boolean validateAutorisationData(final String login,
                                            final String password) {
        logger.debug("start autorisation validation");
        pattern = Pattern.compile(LOGIN_PATTERN);
        matcher = pattern.matcher(login);
        if (!matcher.find()) {
            return false;
        }
        logger.debug("login ok");

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.find();
    }

    public boolean validateUser(final User user) {
        if (user == null) {
            return false;
        }
        if (user.getLogin() != null) {
            pattern = Pattern.compile(LOGIN_PATTERN);
            matcher = pattern.matcher(user.getLogin());
            if (!matcher.find()) {
                return false;
            }
        }
        if (user.getPassword() != null) {
            pattern = Pattern.compile(PASSWORD_PATTERN);
            matcher = pattern.matcher(user.getPassword());
            if (!matcher.find()) {
                return false;
            }
        }
        //TODO check, salt never come from HTML. From DB no need to check.
        if (user.getSalt() != null) {

        }
        //TODO check and think about role value. Need or no need to check in query, or only when create or update.
        if (user.getRole() != null) {

        }

        if (user.getName() != null) {
            pattern = Pattern.compile(USER_NAME_AND_SURNAME_PATTERN);
            matcher = pattern.matcher(user.getName());
            if (!matcher.find()) {
                return false;
            }
        }
        if (user.getSurname() != null) {
            pattern = Pattern.compile(USER_NAME_AND_SURNAME_PATTERN);
            matcher = pattern.matcher(user.getSurname());
            if (!matcher.find()) {
                return false;
            }
        }

        if (user.getGender() != null) {
            pattern = Pattern.compile(GENDER_PATTERN);
            matcher = pattern.matcher(user.getGender());
            if (!matcher.find()) {
                return false;
            }
        }
        //TODO check localdate
        if (user.getBirthday() != null) {

        }
        if (user.getCountry() != null) {
            pattern = Pattern.compile(COUNTRY_PATTERN);
            matcher = pattern.matcher(user.getCountry());
            if (!matcher.find()) {
                return false;
            }
        }

        if (user.getPassportNumber() != null) {
            pattern = Pattern.compile(PASSPORT_NUMBER_PATTERN);
            matcher = pattern.matcher(user.getPassportNumber());
            if (!matcher.find()) {
                return false;
            }
        }

        //TODO check localdate
        if (user.getPassportDateOfIssue() != null) {

        }

        if (user.getPhoneNumber() != null) {
            pattern = Pattern.compile(PHONE_PATTERN);
            matcher = pattern.matcher(user.getPhoneNumber());
            if (!matcher.find()) {
                return false;
            }
        }

        if (user.getEmail() != null) {
            pattern = Pattern.compile(EMAIL_PATTERN);
            matcher = pattern.matcher(user.getEmail());
            if (!matcher.find()) {
                return false;
            }
        }

        //TODO think about check car field
        if (user.getCar() != null) {

        }

        return true;

    }
}
