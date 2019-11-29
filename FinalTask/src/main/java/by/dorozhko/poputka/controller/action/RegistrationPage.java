package by.dorozhko.poputka.controller.action;

import by.dorozhko.poputka.dao.FactoryDao;
import by.dorozhko.poputka.services.DataFromCatalogService;
import by.dorozhko.poputka.services.ServiceFactory;
import by.dorozhko.poputka.services.UserService;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RegistrationPage extends AllUsersAction {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);

        String errorMessage =
                (String) session.getAttribute("passwordNotEqual");
        if (errorMessage != null) {
            request.setAttribute("passwordNotEqual", errorMessage);
            session.removeAttribute("SecurityMessage");
        }
        Map<Integer, String> gendors = ServiceFactory.getInstance().getCatalogService().getGendors();

        request.setAttribute("gendersMap", gendors);
        return "/WEB-INF/jsp/registration.jsp";
    }
}
