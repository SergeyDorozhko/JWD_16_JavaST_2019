package by.dorozhko.poputka.controller;

import by.dorozhko.poputka.entity.Role;
import by.dorozhko.poputka.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
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

    public abstract String execute(HttpServletRequest request, HttpServletResponse response);

}
