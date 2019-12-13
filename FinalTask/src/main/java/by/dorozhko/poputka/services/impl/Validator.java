package by.dorozhko.poputka.services.impl;

import by.dorozhko.poputka.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Validator {
    private final Logger logger = LogManager.getLogger(getClass().getName());
    private static final String LOGIN_PATTERN = "^[\\w-]{1,20}$";
    private static final String PASSWORD_PATTERN = "^[\\wа-яА-Я\\d-+%$@!]{1,50}$";
    private static final String USER_NAME_AND_SURNAME_PATTERN = "^[a-zA-Zа-яА-Я-]{1,255}$";
    private static final String PASSPORT_NUMBER_PATTERN = "^[\\w-]{1,10}$";
    private static final String PHONE_PATTERN = "^[0-9]{7,15}$";
    private static final String EMAIL_PATTERN = "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$";
    private Pattern pattern;
    private Matcher matcher;

    public boolean validateId(final int id) {
        return id > 0 ? true : false;
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

    public boolean validateBirthday(LocalDate birthday) {
        int minAgeValue = 18;
        int maxAgeValue = 100;
        return validateDate(birthday, minAgeValue, maxAgeValue);
    }

    private boolean validateDate(LocalDate date, int minValue, int maxValue) {
        LocalDate now = LocalDate.now();

        boolean overMinValue = date.compareTo(now.minusYears(minValue)) <= 0;
        boolean underMaxValue = date.compareTo(now.minusYears(maxValue)) >= 0;
        if (overMinValue && underMaxValue) {
            return true;
        }
        return false;
    }


    public boolean validatePassportIssueDate(LocalDate issueDate) {
        int minYearsOld = 0;
        int maxYearsOld = 10;
        return validateDate(issueDate, minYearsOld, maxYearsOld);
    }


    //        //TODO check and think about role value. Need or no need to check in query, or only when create or update.
//        if (user.getRole() != null) {
//
//        }
//
//        if (user.getName() != null) {
//            pattern = Pattern.compile(USER_NAME_AND_SURNAME_PATTERN);
//            matcher = pattern.matcher(user.getName());
//            if (!matcher.find()) {
//                return false;
//            }
//        }
//        if (user.getSurname() != null) {
//            pattern = Pattern.compile(USER_NAME_AND_SURNAME_PATTERN);
//            matcher = pattern.matcher(user.getSurname());
//            if (!matcher.find()) {
//                return false;
//            }
//        }
//
//        if (user.getGender() != null) {
//            pattern = Pattern.compile(GENDER_PATTERN);
//            matcher = pattern.matcher(user.getGender());
//            if (!matcher.find()) {
//                return false;
//            }
//        }
//        //TODO check localdate
//        if (user.getBirthday() != null) {
//
//        }
//        if (user.getCountry() != null) {
//            pattern = Pattern.compile(COUNTRY_PATTERN);
//            matcher = pattern.matcher(user.getCountry());
//            if (!matcher.find()) {
//                return false;
//            }
//        }
//
//        if (user.getPassportNumber() != null) {
//            pattern = Pattern.compile(PASSPORT_NUMBER_PATTERN);
//            matcher = pattern.matcher(user.getPassportNumber());
//            if (!matcher.find()) {
//                return false;
//            }
//        }
//
//        //TODO check localdate
//        if (user.getPassportDateOfIssue() != null) {
//
//        }
//
//        if (user.getPhoneNumber() != null) {
//            pattern = Pattern.compile(PHONE_PATTERN);
//            matcher = pattern.matcher(user.getPhoneNumber());
//            if (!matcher.find()) {
//                return false;
//            }
//        }
//
//        if (user.getEmail() != null) {
//            pattern = Pattern.compile(EMAIL_PATTERN);
//            matcher = pattern.matcher(user.getEmail());
//            if (!matcher.find()) {
//                return false;
//            }
//        }
//
//        //TODO think about check car field
//        if (user.getCar() != null) {
//
//        }
//
//        return true;

//    }
}
