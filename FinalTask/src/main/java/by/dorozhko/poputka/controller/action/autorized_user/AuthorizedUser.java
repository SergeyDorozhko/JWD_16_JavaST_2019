package by.dorozhko.poputka.controller.action.autorized_user;

import by.dorozhko.poputka.controller.Action;
import by.dorozhko.poputka.entity.Role;



public abstract  class AuthorizedUser extends Action {
    public AuthorizedUser() {
        setAllowRoles(Role.USER);
        setAllowRoles(Role.ADMINISTRATOR);

    }
}
