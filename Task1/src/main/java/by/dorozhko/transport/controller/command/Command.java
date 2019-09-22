package by.dorozhko.transport.controller.command;

public interface Command {
    /**
     * This method execute command inputed by User and send in to controller.
     *
     * @param params user action.
     * @return result.
     */
    String execute(String params);
}
