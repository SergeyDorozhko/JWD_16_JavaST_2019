package by.dorozhko.matrix.controller.command;

public interface Command {
    /**
     * interface used by controller to connect user request with right service.
     * @param specification user request.
     * @return result of action.
     */
    String execute(String specification);
}
