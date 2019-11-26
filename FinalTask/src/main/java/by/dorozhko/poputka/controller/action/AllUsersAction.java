package by.dorozhko.poputka.controller.action;

import by.dorozhko.poputka.controller.Action;
import by.dorozhko.poputka.entity.Role;

import java.util.Arrays;

public abstract class AllUsersAction extends Action {
    public AllUsersAction() {
        for (Role role : Arrays.asList(Role.values())) {
            setAllowRoles(role);
        }
    }

}
