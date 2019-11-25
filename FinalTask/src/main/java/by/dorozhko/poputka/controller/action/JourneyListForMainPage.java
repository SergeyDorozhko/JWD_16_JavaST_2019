package by.dorozhko.poputka.controller.action;

import by.dorozhko.poputka.controller.Action;
import by.dorozhko.poputka.entity.User;
import by.dorozhko.poputka.services.JourneyService;
import by.dorozhko.poputka.services.ServiceFactory;
import by.dorozhko.poputka.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class JourneyListForMainPage implements Action {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        JourneyService journeyService
                = ServiceFactory.getInstance().getJoureyService();


        request.setAttribute("journeyList",
                journeyService.findAllJourneyShort());
        return "/WEB-INF/jsp/main.jsp";
    }
}
