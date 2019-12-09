package by.dorozhko.poputka.controller.action.autorized_user.administrator;

import by.dorozhko.poputka.entity.User;
import by.dorozhko.poputka.services.ServiceFactory;
import by.dorozhko.poputka.services.UserService;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class DisplayAllUsers extends AdminAction {

    private static final String FORWARD_PAGE = "/WEB-INF/jsp/listOfUsers.jsp";

    private static final String LIST_OF_USERS_ATTRIBUTE = "usersList";


    @Override
    public String execute(final HttpServletRequest req,
                          final HttpServletResponse resp) {
        UserService userService = ServiceFactory.getInstance().getUserService();
        List<User> list = userService.findAll();

        req.setAttribute(LIST_OF_USERS_ATTRIBUTE, list);
        return FORWARD_PAGE;
    }
}
