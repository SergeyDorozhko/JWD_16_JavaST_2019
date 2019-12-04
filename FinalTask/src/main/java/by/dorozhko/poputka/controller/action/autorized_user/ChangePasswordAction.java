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
            User actionUser = (User) session.getAttribute("authorizedUser");
            User user = new User();
            user.setId(actionUser.getId());
            user.setLogin(actionUser.getLogin());

            user.setPassword(oldPassword);
            try {
                logger.debug("start delete user");
                boolean result = userService.updateUserPassword(user, newPassword);
                logger.debug("take answer from service");
                if (result) {
                    session.setAttribute("unknownError", resourceBundle.getString("back.errors.passwordUpdateSuccess"));
                } else {
                    session.setAttribute("unknownError", resourceBundle.getString("back.errors.incorrectOldPassword"));
                }


            } catch (ExceptionService exceptionService) {
                logger.error(String.format("sqlmsg : %s",
                        exceptionService.getMessage()));
                session.setAttribute("unknownError", resourceBundle.getString("back.errors.unknownError"));

            }

        }
        return request.getContextPath() + "/viewUserProfile.html";

    }


    private void getAllAttributes(HttpServletRequest request) {
        oldPassword = request.getParameter("oldPassword");
        newPassword = request.getParameter("newPassword");
        comfirmNewPassword = request.getParameter("comfirmNewPassword");

        logger.debug("all params take ok");
    }

    private boolean checkData(ResourceBundle resourceBundle) {
        logger.debug("check data start");

        if (oldPassword == null || oldPassword.length() == 0) {
            session.setAttribute("unknownError", resourceBundle.getString("back.errors.oldPasswordIsEmpty"));
            return false;
        }

        if (newPassword == null || newPassword.length() == 0) {
            session.setAttribute("unknownError", resourceBundle.getString("back.errors.newPasswordIsEmpty"));
            return false;
        } else if (comfirmNewPassword == null || comfirmNewPassword.length() == 0) {
            session.setAttribute("unknownError", resourceBundle.getString("back.errors.comfirmNewPasswordIsEmpty"));
            return false;
        } else {
            if (!newPassword.equals(comfirmNewPassword)) {
                session.setAttribute("unknownError", resourceBundle.getString("back.errors.comfirmPasswordError"));
                return false;
            }
        }

        return true;
    }


}
