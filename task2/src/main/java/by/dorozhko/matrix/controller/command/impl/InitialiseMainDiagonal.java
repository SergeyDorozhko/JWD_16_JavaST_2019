package by.dorozhko.matrix.controller.command.impl;

import by.dorozhko.matrix.controller.command.Command;
import by.dorozhko.matrix.service.MatrixService;
import by.dorozhko.matrix.service.ServiceFactory;

public class InitialiseMainDiagonal implements Command {
    /**
     * Connecting to initialise matrix main diagonal service.
     * @param specification user request.
     * @return
     */
    @Override
    public String execute(final String specification) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        MatrixService matrixService = serviceFactory.getService();
        return matrixService.initialiseMainDiagonal(specification);
    }
}
