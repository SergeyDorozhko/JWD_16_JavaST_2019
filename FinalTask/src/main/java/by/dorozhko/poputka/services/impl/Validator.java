package by.dorozhko.poputka.services.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Validator {
    private final Logger logger = LogManager.getLogger(getClass().getName());
    private static final String LOGIN_PATTERN = "^[\\w-]{1,20}$";
    private static final String PASSWORD_PATTERN = "^[\\w\\dа-яА-Я-+%$@!]{1,50}$";
    private static final String USER_NAME_AND_SURNAME_PATTERN = "^[a-zA-Zа-яА-Я-]{1,255}$";
    private static final String PASSPORT_NUMBER_PATTERN = "^[\\w-]{1,10}$";
    private static final String PHONE_PATTERN = "^[0-9]{7,15}$";
    private static final String EMAIL_PATTERN = "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$";
    private static final String COMMENTARY_PATTERN = "^[^*/\\;'|<>]{0,1000}$";
    private Pattern pattern;
    private Matcher matcher;

    public boolean validateForPositiveInteger(final int id) {
        return id > 0;
    }


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


    public boolean validateLogin(final String login) {
        return validate(login, LOGIN_PATTERN);
    }


    private boolean validate(final String value, final String template) {
        if (value != null) {
            pattern = Pattern.compile(template);
            matcher = pattern.matcher(value);
            return matcher.find();
        }
        return false;
    }

    public boolean validatePassword(final String password) {
        return validate(password, PASSWORD_PATTERN);
    }

    public boolean validateNameOrSurname(final String value) {
        return validate(value, USER_NAME_AND_SURNAME_PATTERN);
    }

    public boolean validateEmail(final String email) {
        return validate(email, EMAIL_PATTERN);
    }

    public boolean validatePhone(final String phone) {
        return validate(phone, PHONE_PATTERN);
    }

    public boolean validatePassportNumber(final String passportNumber) {
        return validate(passportNumber, PASSPORT_NUMBER_PATTERN);
    }

    public boolean validateCommentary(final String commentary) {
        return validate(commentary, COMMENTARY_PATTERN);
    }


    public boolean validateDate(final LocalDate date,
                                final int minValue, final int maxValue) {
        logger.debug(date);
        LocalDate now = LocalDate.now();
        logger.debug(now);
        boolean overMinValue = date.compareTo(now.minusYears(minValue)) <= 0;
        logger.debug(now.minusYears(minValue));
        boolean underMaxValue = date.compareTo(now.minusYears(maxValue)) >= 0;
        logger.debug(now.minusYears(maxValue));
        return overMinValue && underMaxValue;
    }

    public boolean validateFutureTimeByDate(final LocalDate date,
                                            final LocalTime time) {
        logger.debug(date);
        logger.debug(time);
        LocalDate today = LocalDate.now();
        logger.debug(today);
        LocalTime timeNow = LocalTime.now();
        logger.debug(timeNow);

        if (date.compareTo(today) > 0) {
            return true;
        }

        return date.compareTo(today) == 0
                && time.compareTo(timeNow) > 0;
    }

    public boolean validateCost(final double cost) {
        return cost >= 0;
    }

}
