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
        if (checkData()) {
            User user = new User(login, firstName, lastName, sex, birthday, country, passportNumber, passportDate, phoneNumber, email);
            User actionUser = (User) session.getAttribute("authorizedUser");

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

    private boolean checkData() {
        logger.debug("check data start");
        int countErrors = 0;


        if (login.length() == 0) {
            session.setAttribute("errorLogin", "поле не может быть пустым");
            countErrors++;
        }
        if (firstName.length() == 0) {
            session.setAttribute("errorFirstName", "поле не может быть пустым");
            countErrors++;
        }
        if (lastName.length() == 0) {
            session.setAttribute("errorLastName", "поле не может быть пустым");
            countErrors++;
        }
        if (email.length() == 0) {
            session.setAttribute("errorEmail", "поле не может быть пустым");
            countErrors++;
        }
        if (birthday.length() == 0) {
            session.setAttribute("errorBirthday", "поле не может быть пустым");
            countErrors++;
        }
        if (phoneNumber.length() == 0) {
            session.setAttribute("errorPhoneNumber", "поле не может быть пустым");
            countErrors++;
        }
        if (country.length() == 0) {
            session.setAttribute("errorCountry", "поле не может быть пустым");
            countErrors++;
        }
        if (passportNumber.length() == 0) {
            session.setAttribute("errorPassportNumber", "поле не может быть пустым");
            countErrors++;
        }
        if (passportDate.length() == 0) {
            session.setAttribute("errorPassportDate", "поле не может быть пустым");
            countErrors++;
        }
        if (sex.length() == 0) {
            session.setAttribute("errorSex", "поле не может быть пустым");
            countErrors++;
        }
        logger.debug(String.format("find %d errors", countErrors));
        return countErrors == 0;
    }

    private void getErrorMessage(String msg) {
        if (msg.contains("login")) {
            session.setAttribute("errorLogin", "пользователь с таким логином существует");
        } else if (msg.contains("passport_number")) {
            session.setAttribute("errorPassportNumber", "пользователь с таким паспортом существует");
        } else if (msg.contains("phone")) {
            session.setAttribute("errorPhoneNumber", "пользователь с таким номером телефона существует");
        } else if (msg.contains("email")) {
            session.setAttribute("errorEmail", "пользователь с такой почной существует");
        } else {
            session.setAttribute("unknownError", "что-то пошло не так, попробуйте снова");
        }

    }

}
