package by.dorozhko.poputka.controller.action.autorized_user.user;

import by.dorozhko.poputka.controller.Action;
import by.dorozhko.poputka.entity.Role;


public abstract class UserAction extends Action {
    public UserAction() {
        setAllowRoles(Role.USER);
    }
}
