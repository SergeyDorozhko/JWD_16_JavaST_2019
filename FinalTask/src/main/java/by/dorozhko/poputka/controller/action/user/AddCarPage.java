package by.dorozhko.poputka.controller.action.user;

import by.dorozhko.poputka.services.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class AddCarPage extends UserAction {
    private final Logger logger = LogManager.getLogger(getClass().getName());
    private String userBrand;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        getCookieAction().setCookie(request, response);
        Map<Integer, String> brands = ServiceFactory.getInstance().getCatalogService().getCarBrands();
        request.setAttribute("brandsMap", brands);
        Map<Integer, String> climate = ServiceFactory.getInstance().getCatalogService().getCarClimateTypes();
        request.setAttribute("climateTypeMap", climate);

        attributesData(request);
        if (userBrand != null) {
            Map<Integer, String> models = ServiceFactory.getInstance().getCatalogService().getCarModelsOfBrand(Integer.parseInt(userBrand));
            request.setAttribute("modelsMap", models);
        }
        return "/WEB-INF/jsp/addCar.jsp";
    }


    private void attributesData(final HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        logger.debug("check attributes start");
        logger.debug(request.getAttributeNames());
        String errorBrand =
                (String) session.getAttribute("errorBrand");
        if (errorBrand != null) {
            request.setAttribute("errorBrand", errorBrand);
            session.removeAttribute("errorBrand");
        }
        String errorModel =
                (String) session.getAttribute("errorModel");
        if (errorModel != null) {
            request.setAttribute("errorModel", errorModel);
            session.removeAttribute("errorModel");
        }

        String errorClimate =
                (String) session.getAttribute("errorClimate");
        if (errorClimate != null) {
            request.setAttribute("errorClimate", errorClimate);
            session.removeAttribute("errorClimate");
        }
        String errorProduced =
                (String) session.getAttribute("errorProduced");
        if (errorProduced != null) {
            request.setAttribute("errorProduced", errorProduced);
            session.removeAttribute("errorProduced");
        }

        userBrand =
                (String) session.getAttribute("userBrand");
        if (userBrand != null) {
            request.setAttribute("userBrand", userBrand);
            session.removeAttribute("userBrand");
        }
        logger.debug(String.format("Brand: %s", userBrand));
        String userModel =
                (String) session.getAttribute("userModel");
        if (userModel != null) {
            request.setAttribute("userModel", userModel);
            session.removeAttribute("userModel");
        }
        String userClimate =
                (String) session.getAttribute("userClimate");
        if (userClimate != null) {
            request.setAttribute("userClimate", userClimate);
            session.removeAttribute("userClimate");
        }
        String userProduced =
                (String) session.getAttribute("userProduced");
        if (userProduced != null) {
            request.setAttribute("userProduced", userProduced);
            session.removeAttribute("userProduced");
        }
    }
}
