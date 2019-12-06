package by.dorozhko.poputka.controller.action.autorized_user.administrator;

import by.dorozhko.poputka.entity.User;
import by.dorozhko.poputka.services.ServiceFactory;
import by.dorozhko.poputka.services.UserService;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class DisplayAllUsers extends AdminAction {

    @Override
    public String execute(final HttpServletRequest req,
                          final HttpServletResponse resp) {
        UserService userService = ServiceFactory.getInstance().getUserService();
        List<User> list = userService.findAll();

        req.setAttribute("usersList", list);
        return "/WEB-INF/jsp/listOfUsers.jsp";
    }
}
