package by.dorozhko.poputka.controller.action;

import by.dorozhko.poputka.controller.Action;
import by.dorozhko.poputka.entity.User;
import by.dorozhko.poputka.services.ServiceFactory;
import by.dorozhko.poputka.services.UserService;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class DisplayAllUsers implements Action {
    public String execute(final HttpServletRequest req,
                          final HttpServletResponse resp) {
        UserService userService = ServiceFactory.getInstance().getUserService();
        List<User> list = userService.findAll();
        System.out.println(list);

        req.setAttribute("usersList", list);
        return "/WEB-INF/jsp/listOfUsers.jsp";
    }
}
