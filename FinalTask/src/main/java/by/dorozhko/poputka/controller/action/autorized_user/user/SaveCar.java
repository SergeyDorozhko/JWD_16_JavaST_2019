package by.dorozhko.poputka.controller.action.autorized_user.user;

import by.dorozhko.poputka.entity.Car;
import by.dorozhko.poputka.entity.User;
import by.dorozhko.poputka.services.ServiceFactory;
import by.dorozhko.poputka.services.UserService;
import by.dorozhko.poputka.services.exception.ExceptionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.ResourceBundle;

public class SaveCar extends UserAction {
    private final Logger logger = LogManager.getLogger(getClass().getName());

    private String brand;
    private String model;
    private String climate;
    private String produced;

    private String localeCountry;
    private String localeLanguage;
    private HttpSession session;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("start");
        session = request.getSession(false);
        getAllAttributes(request);
        logger.debug(String.format("check: %s", checkData(request)));

        if (checkData(request)) {
            User user = (User) session.getAttribute("authorizedUser");
            logger.debug(String.format("user: %s", user));
            Car car = new Car(brand, model, Integer.parseInt(produced), climate);
            user.setCar(car);
            logger.debug(String.format("user: %s", user));

            UserService userService = ServiceFactory.getInstance().getUserService();
            try {
                userService.addCar(user);
                logger.debug(user);
                return request.getContextPath() + "/viewUserProfile.html";
            } catch (ExceptionService exceptionService) {
                logger.error(exceptionService);
                //TODO exception parsing to show answer.
            }
        }

        logger.debug(String.format("button.menu %s", request.getAttribute("main")));
        setUserInputData();
        //todo GO to account if success.
        return request.getContextPath() + "/addCar.html";
    }


    private void getAllAttributes(HttpServletRequest request) {
        brand = request.getParameter("brand");
        model = request.getParameter("model");
        climate = request.getParameter("climate");
        produced = request.getParameter("produced");
    }


    private boolean checkData(HttpServletRequest request) {
        logger.debug("check data start");
        int countErrors = 0;
        takeLocal(request);
        Locale current = new Locale(localeLanguage, localeCountry);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("pagecontent", current);
        if (brand.length() == 0) {
            countErrors++;
        } else {
            try {
                int i = Integer.parseInt(brand);
            } catch (NumberFormatException ex) {
                session.setAttribute("errorBrand",
                        resourceBundle.getString("back.addCar.errorBrand"));
                countErrors++;
            }
        }
        if (model.length() == 0) {
            countErrors++;
        } else {
            try {
                int i = Integer.parseInt(model);
            } catch (NumberFormatException ex) {
                session.setAttribute("errorModel", "back.addCar.errorModel");
                countErrors++;
            }
        }
        if (climate.length() == 0) {
            countErrors++;
        } else {
            try {
                int i = Integer.parseInt(climate);
            } catch (NumberFormatException ex) {
                session.setAttribute("errorClimate", "back.addCar.errorClimate");
                countErrors++;
            }
        }
        if (produced.length() == 0) {
            countErrors++;
        } else {
            try {
                int i = Integer.parseInt(climate);
            } catch (NumberFormatException ex) {
                session.setAttribute("errorProduced", "back.addCar.errorProduced");
                countErrors++;
            }
        }

        logger.debug(String.format("find %d errors", countErrors));
        return countErrors == 0;
    }

    private void takeLocal(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String locale = null;
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals("lang")) {
                locale = cookies[i].getValue();
                localeLanguage = locale.split("-")[0];
                localeCountry = locale.split("-")[1];
            }
        }
    }

    private void setUserInputData() {
        logger.debug(String.format("set user attributes: %s, %s, %s, %s",
                brand, model, climate, produced));
        session.setAttribute("userBrand", brand);
        session.setAttribute("userModel", model);
        session.setAttribute("userClimate", climate);
        session.setAttribute("userProduced", produced);

    }

}