package by.dorozhko.poputka.controller.action.administrator;

import by.dorozhko.poputka.controller.Action;
import by.dorozhko.poputka.entity.Role;

public abstract class AdminAction extends Action {
    public AdminAction() {
        setAllowRoles(Role.ADMINISTRATOR);
    }

}
