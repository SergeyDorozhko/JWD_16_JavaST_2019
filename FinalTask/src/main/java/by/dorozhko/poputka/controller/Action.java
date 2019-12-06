package by.dorozhko.poputka.controller;

import by.dorozhko.poputka.entity.Role;
import by.dorozhko.poputka.entity.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

public abstract class Action {
    private Set<Role> allowRoles = new HashSet<>();
    private User userOfAction;


    public Set<Role> getAllowRoles() {
        return allowRoles;
    }

    protected void setAllowRoles(Role role) {
        allowRoles.add(role);
    }


    public User getUserOfAction() {
        return userOfAction;
    }

    public void setUserOfAction(User userOfAction) {
        this.userOfAction = userOfAction;
    }

    public abstract String execute(HttpServletRequest request,
                                   HttpServletResponse response);

    protected void setLocaleToCookie(HttpServletRequest request,
                                     HttpServletResponse response) {
        String value = request.getParameter("local");
        if (value != null) {
            Cookie localCookie = new Cookie("lang", value);
            localCookie.setHttpOnly(true);
            response.addCookie(localCookie);
            request.setAttribute("lang", value);
        }

    }

    protected ResourceBundle takeLocale(HttpServletRequest request) {
        String localeCountry = null;
        String localeLanguage = null;
        Cookie[] cookies = request.getCookies();
        String locale = null;
        for (int i = 0; i < cookies.length; i++) {
            if (cookies[i].getName().equals("lang")) {
                locale = cookies[i].getValue();
                localeLanguage = locale.split("-")[0];
                localeCountry = locale.split("-")[1];
            }
        }
        Locale current = new Locale(localeLanguage, localeCountry);
        return ResourceBundle.getBundle("pagecontent", current);
    }
}
