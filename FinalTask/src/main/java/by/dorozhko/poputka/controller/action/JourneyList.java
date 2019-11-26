package by.dorozhko.poputka.controller.action;

import by.dorozhko.poputka.controller.Action;
import by.dorozhko.poputka.services.JourneyService;
import by.dorozhko.poputka.services.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JourneyList extends AllUsersAction {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        JourneyService journeyService
                = ServiceFactory.getInstance().getJoureyService();


        System.out.println(journeyService.findAllJourneyShort());
        request.setAttribute("journeyList",
                journeyService.findAllJourneyShort());
        return "/WEB-INF/jsp/listOfJourneys.jsp";
    }
}
