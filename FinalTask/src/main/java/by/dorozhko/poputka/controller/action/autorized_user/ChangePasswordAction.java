package by.dorozhko.poputka.controller.action.autorized_user;

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

public class ChangePasswordAction extends AuthorizedUser {

    private final Logger logger = LogManager.getLogger(getClass().getName());

    private static final String REDIRECT_URL = "/viewUserProfile.html";

    private static final String OLD_PASSWORD_PARAMETER = "oldPassword";
    private static final String NEW_PASSWORD_PARAMETER = "newPassword";
    private static final String COMFIRM_NEW_PASSWORD_PARAMETER = "comfirmNewPassword";

    private static final String AUTHORIZED_USER_ATTRIBUTE = "authorizedUser";
    private static final String UNKNOWN_ERROR_ATTRIBUTE = "unknownError";

    private static final String UNKNOWN_ERROR_MESSAGE = "back.errors.unknownError";
    private static final String PASSWORD_UPDATE_SUCCESS_MESSAGE = "back.errors.passwordUpdateSuccess";
    private static final String INCORRECT_OLD_PASSWORD_ERROR_MESSAGE = "back.errors.incorrectOldPassword";
    private static final String EMPTY_OLD_PASSWORD_MESSAGE = "back.errors.oldPasswordIsEmpty";
    private static final String EMPTY_NEW_PASSWORD_MESSAGE = "back.errors.newPasswordIsEmpty";
    private static final String EMPTY_COMFIRM_NEW_PASSWORD_MESSAGE = "back.errors.comfirmNewPasswordIsEmpty";
    private static final String COMFIRM_NEW_PASSWORD_ERROR_MESSAGE = "back.errors.comfirmPasswordError";

    private static final String INVALID_FORMAT_IN_OLD_PASSWORD_MESSAGE = "back.errors.invalidFormatInOldPassword";
    private static final String INVALID_FORMAT_IN_NEW_PASSWORD_MESSAGE = "back.errors.invalidFormatInNewPassword";

    private static final String INVALID_LOGIN_OR_PASSWORD_FORMAT = "invalid password or login format";
    private static final String INVALID_PASSWORD_FORMAT = "invalid password format";


    private String oldPassword;
    private String newPassword;
    private String comfirmNewPassword;
    private HttpSession session;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        logger.debug("start delete Account ok");

        session = request.getSession(false);
        ResourceBundle resourceBundle = takeLocale(request);

        getAllAttributes(request);


        if (checkData(resourceBundle)) {
            UserService userService
                    = ServiceFactory.getInstance().getUserService();
            User actionUser = (User) session.getAttribute(AUTHORIZED_USER_ATTRIBUTE);
            User user = new User();
            user.setId(actionUser.getId());
            user.setLogin(actionUser.getLogin());

            user.setPassword(oldPassword);
            try {
                logger.debug("start delete user");
                boolean result = userService.updateUserPassword(user, newPassword);
                logger.debug("take answer from service");
                if (result) {
                    session.setAttribute(UNKNOWN_ERROR_ATTRIBUTE,
                            resourceBundle.getString(PASSWORD_UPDATE_SUCCESS_MESSAGE));
                } else {
                    session.setAttribute(UNKNOWN_ERROR_ATTRIBUTE,
                            resourceBundle.getString(INCORRECT_OLD_PASSWORD_ERROR_MESSAGE));
                }


            } catch (ExceptionService exceptionService) {
                logger.error(String.format("sqlmsg : %s",
                        exceptionService.getMessage()));
                getErrorMessage(exceptionService.getMessage(), resourceBundle);
            }

        }
        return request.getContextPath() + REDIRECT_URL;

    }


    private void getAllAttributes(HttpServletRequest request) {
        oldPassword = request.getParameter(OLD_PASSWORD_PARAMETER);
        newPassword = request.getParameter(NEW_PASSWORD_PARAMETER);
        comfirmNewPassword = request.getParameter(COMFIRM_NEW_PASSWORD_PARAMETER);

        logger.debug("all params take ok");
    }

    private boolean checkData(ResourceBundle resourceBundle) {
        logger.debug("check data start");

        if (oldPassword == null || oldPassword.length() == 0) {
            session.setAttribute(UNKNOWN_ERROR_ATTRIBUTE,
                    resourceBundle.getString(EMPTY_OLD_PASSWORD_MESSAGE));
            return false;
        }

        if (newPassword == null || newPassword.length() == 0) {
            session.setAttribute(UNKNOWN_ERROR_ATTRIBUTE,
                    resourceBundle.getString(EMPTY_NEW_PASSWORD_MESSAGE));
            return false;
        } else if (comfirmNewPassword == null || comfirmNewPassword.length() == 0) {
            session.setAttribute(UNKNOWN_ERROR_ATTRIBUTE,
                    resourceBundle.getString(EMPTY_COMFIRM_NEW_PASSWORD_MESSAGE));
            return false;
        } else {
            if (!newPassword.equals(comfirmNewPassword)) {
                session.setAttribute(UNKNOWN_ERROR_ATTRIBUTE,
                        resourceBundle.getString(COMFIRM_NEW_PASSWORD_ERROR_MESSAGE));
                return false;
            }
        }

        return true;
    }

    private void getErrorMessage(String msg, ResourceBundle resourceBundle) {

        if (msg.equals(INVALID_LOGIN_OR_PASSWORD_FORMAT)) {
            session.setAttribute(UNKNOWN_ERROR_ATTRIBUTE,
                    resourceBundle.getString(INVALID_FORMAT_IN_OLD_PASSWORD_MESSAGE));
        } else if (msg.equals(INVALID_PASSWORD_FORMAT)) {
            session.setAttribute(UNKNOWN_ERROR_ATTRIBUTE,
                    resourceBundle.getString(INVALID_FORMAT_IN_NEW_PASSWORD_MESSAGE));
        }  else {
            session.setAttribute(UNKNOWN_ERROR_ATTRIBUTE,
                    resourceBundle.getString(UNKNOWN_ERROR_MESSAGE));
        }

    }

}
