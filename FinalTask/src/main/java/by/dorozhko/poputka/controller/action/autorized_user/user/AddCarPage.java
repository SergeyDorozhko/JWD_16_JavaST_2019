package by.dorozhko.poputka.controller.action.autorized_user.user;

import by.dorozhko.poputka.services.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AddCarPage extends UserAction {
    private final Logger logger = LogManager.getLogger(getClass().getName());

    private static final String FORWARD_PAGE = "/WEB-INF/jsp/addCar.jsp";

    private static final String BRANDS_MAP_ATTRIBUTE = "brandsMap";
    private static final String MODELS_MAP_ATTRIBUTE = "modelsMap";
    private static final String CLIMATE_TYPE_MAP_ATTRIBUTE = "climateTypeMap";

    private static final String ERROR_BRAND_ATTRIBUTE = "errorBrand";
    private static final String ERROR_MODEL_ATTRIBUTE = "errorModel";
    private static final String ERROR_CLIMATE_ATTRIBUTE = "errorClimate";
    private static final String ERROR_YEAR_OF_PRODUCE_ATTRIBUTE = "errorProduced";
    private static final String USER_BRAND_ATTRIBUTE = "userBrand";
    private static final String USER_MODEL_ATTRIBUTE = "userModel";
    private static final String USER_CLIMATE_ATTRIBUTE = "userClimate";
    private static final String USER_YEAR_OF_PRODUCE_ATTRIBUTE = "userProduced";


    private String userBrand;

    private Set<String> listOfAttributes;

    public AddCarPage() {
        listOfAttributes = new HashSet<>();
        listOfAttributes.add(ERROR_BRAND_ATTRIBUTE);
        listOfAttributes.add(ERROR_MODEL_ATTRIBUTE);
        listOfAttributes.add(ERROR_CLIMATE_ATTRIBUTE);
        listOfAttributes.add(ERROR_YEAR_OF_PRODUCE_ATTRIBUTE);
        listOfAttributes.add(USER_BRAND_ATTRIBUTE);
        listOfAttributes.add(USER_MODEL_ATTRIBUTE);
        listOfAttributes.add(USER_CLIMATE_ATTRIBUTE);
        listOfAttributes.add(USER_YEAR_OF_PRODUCE_ATTRIBUTE);
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        setLocaleToCookie(request, response);
        Map<Integer, String> brands = ServiceFactory.getInstance().getCatalogService().getCarBrands();
        request.setAttribute(BRANDS_MAP_ATTRIBUTE, brands);
        Map<Integer, String> climate = ServiceFactory.getInstance().getCatalogService().getCarClimateTypes();
        request.setAttribute(CLIMATE_TYPE_MAP_ATTRIBUTE, climate);

        attributesData(request);
        if (userBrand != null) {
            Map<Integer, String> models = ServiceFactory.getInstance().getCatalogService().getCarModelsOfBrand(Integer.parseInt(userBrand));
            request.setAttribute(MODELS_MAP_ATTRIBUTE, models);
        }
        return FORWARD_PAGE;
    }


    private void attributesData(final HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        logger.debug("check attributes start");
        logger.debug(request.getAttributeNames());

        for (String attribute : listOfAttributes) {

            String attributeData =
                    (String) session.getAttribute(attribute);
            if (attributeData != null) {
                request.setAttribute(attribute, attributeData);
                session.removeAttribute(attribute);
                if (attribute.equals(USER_BRAND_ATTRIBUTE)) {
                    userBrand = attributeData;
                }
            }

        }
    }
}
