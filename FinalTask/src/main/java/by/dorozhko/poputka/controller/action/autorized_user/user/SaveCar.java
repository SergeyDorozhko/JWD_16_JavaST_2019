package by.dorozhko.poputka.controller.action.autorized_user.user;

import by.dorozhko.poputka.entity.Car;
import by.dorozhko.poputka.entity.User;
import by.dorozhko.poputka.services.ServiceFactory;
import by.dorozhko.poputka.services.UserService;
import by.dorozhko.poputka.services.exception.ExceptionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.StringMapMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ResourceBundle;

public class SaveCar extends UserAction {
    private final Logger logger = LogManager.getLogger(getClass().getName());

    private static final String SUCCESSFUL_REDIRECT_URL = "/viewUserProfile.html";
    private static final String ERROR_REDIRECT_URL = "/addCar.html";

    private static final String AUTHORIZED_USER_ATTRIBUTE = "authorizedUser";

    private static final String BRAND_ATTRIBUTE = "brand";
    private static final String MODEL_ATTRIBUTE = "model";
    private static final String CLIMATE_ATTRIBUTE = "climate";
    private static final String YEAR_OF_PRODUCE_ATTRIBUTE = "produced";

    private static final String ERROR_BRAND_ATTRIBUTE = "errorBrand";
    private static final String ERROR_MODEL_ATTRIBUTE = "errorModel";
    private static final String ERROR_CLIMATE_ATTRIBUTE = "errorClimate";
    private static final String ERROR_YEAR_OF_PRODUCE_ATTRIBUTE = "errorProduced";
    private static final String USER_BRAND_ATTRIBUTE = "userBrand";
    private static final String USER_MODEL_ATTRIBUTE = "userModel";
    private static final String USER_CLIMATE_ATTRIBUTE = "userClimate";
    private static final String USER_YEAR_OF_PRODUCE_ATTRIBUTE = "userProduced";

    private static final String FIELD_IS_EMPTY_ERROR_MESSAGE = "back.errors.fieldIsEmptyError";
    private static final String ERROR_BRAND_MESSAGE = "back.addCar.errorBrand";
    private static final String ERROR_MODEL_MESSAGE = "back.addCar.errorModel";
    private static final String ERROR_CLIMATE_MESSAGE = "back.addCar.errorClimate";
    private static final String ERROR_YEAR_OF_PRODUCE_MESSAGE = "back.addCar.errorProduced";

    private static final String BUTTON = "button";
    private static final String SAVE_CAR_BUTTON = "saveCar";


    private String brand;
    private String model;
    private String climate;
    private String produced;


    private HttpSession session;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("start");
        session = request.getSession(false);

        getAllAttributes(request);

        String button = request.getParameter(BUTTON);
        logger.debug(String.format("button - %s", button));
        boolean isSaveActionAndValid =  button != null && button.equals(SAVE_CAR_BUTTON) && checkData(request);
        if (isSaveActionAndValid) {
            User actionUser = (User) session.getAttribute(AUTHORIZED_USER_ATTRIBUTE);
            User user = new User();
            user.setId(actionUser.getId());
            user.setLogin(actionUser.getLogin());
            logger.debug(String.format("user: %s", user));
            Car car = new Car(brand, model, Integer.parseInt(produced), climate);
            user.setCar(car);
            logger.debug(String.format("user: %s", user));

            UserService userService = ServiceFactory.getInstance().getUserService();
            try {
                userService.addCar(user);
                logger.debug(user);
                return request.getContextPath() + SUCCESSFUL_REDIRECT_URL;
            } catch (ExceptionService exceptionService) {
                logger.error(exceptionService);
                //TODO exception parsing to show answer.
            }
        }

        setUserInputData();
        return request.getContextPath() + ERROR_REDIRECT_URL;


    }


    private void getAllAttributes(HttpServletRequest request) {
        brand = request.getParameter(BRAND_ATTRIBUTE);
        model = request.getParameter(MODEL_ATTRIBUTE);
        climate = request.getParameter(CLIMATE_ATTRIBUTE);
        produced = request.getParameter(YEAR_OF_PRODUCE_ATTRIBUTE);
    }


    private boolean checkData(HttpServletRequest request) {
        logger.debug("check data start");
        int countErrors = 0;
        ResourceBundle resourceBundle = takeLocale(request);

        if (brand == null || brand.length() == 0) {
            session.setAttribute(ERROR_BRAND_ATTRIBUTE,
                    resourceBundle.getString(FIELD_IS_EMPTY_ERROR_MESSAGE));
            countErrors++;
        } else {
            try {
                Integer.parseInt(brand);
            } catch (NumberFormatException ex) {
                session.setAttribute(ERROR_BRAND_ATTRIBUTE,
                        resourceBundle.getString(ERROR_BRAND_MESSAGE));
                countErrors++;
            }
        }
        if (model == null || model.length() == 0) {
            session.setAttribute(ERROR_MODEL_ATTRIBUTE,
                    resourceBundle.getString(FIELD_IS_EMPTY_ERROR_MESSAGE));
            countErrors++;
        } else {
            try {
                Integer.parseInt(model);
            } catch (NumberFormatException ex) {
                session.setAttribute(ERROR_MODEL_ATTRIBUTE,
                        resourceBundle.getString(ERROR_MODEL_MESSAGE));
                countErrors++;
            }
        }
        if (climate == null || climate.length() == 0) {
            session.setAttribute(ERROR_CLIMATE_ATTRIBUTE,
                    resourceBundle.getString(FIELD_IS_EMPTY_ERROR_MESSAGE));
            countErrors++;
        } else {
            try {
                Integer.parseInt(climate);
            } catch (NumberFormatException ex) {
                session.setAttribute(ERROR_CLIMATE_ATTRIBUTE,
                        resourceBundle.getString(ERROR_CLIMATE_MESSAGE));
                countErrors++;
            }
        }
        if (produced == null || produced.length() == 0) {
            session.setAttribute(ERROR_YEAR_OF_PRODUCE_ATTRIBUTE,
                    resourceBundle.getString(FIELD_IS_EMPTY_ERROR_MESSAGE));
            countErrors++;
        } else {
            try {
                Integer.parseInt(produced);
            } catch (NumberFormatException ex) {
                session.setAttribute(ERROR_YEAR_OF_PRODUCE_ATTRIBUTE,
                        resourceBundle.getString(ERROR_YEAR_OF_PRODUCE_MESSAGE));
                countErrors++;
            }
        }

        logger.debug(String.format("find %d errors", countErrors));
        return countErrors == 0;
    }


    private void setUserInputData() {
        logger.debug(String.format("set user attributes: %s, %s, %s, %s",
                brand, model, climate, produced));
        session.setAttribute(USER_BRAND_ATTRIBUTE, brand);
        session.setAttribute(USER_MODEL_ATTRIBUTE, model);
        session.setAttribute(USER_CLIMATE_ATTRIBUTE, climate);
        session.setAttribute(USER_YEAR_OF_PRODUCE_ATTRIBUTE, produced);

    }

}