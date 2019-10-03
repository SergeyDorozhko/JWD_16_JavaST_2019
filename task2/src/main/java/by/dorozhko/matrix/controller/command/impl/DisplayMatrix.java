package by.dorozhko.matrix.controller.command.impl;

import by.dorozhko.matrix.controller.command.Command;
import by.dorozhko.matrix.service.MatrixService;
import by.dorozhko.matrix.service.ServiceFactory;


public class DisplayMatrix implements Command {
    /**
     * Connecting to view matrix from data service.
     * @param specification user request.
     * @return
     */
    @Override
    public String execute(final String specification) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        MatrixService matrixService = serviceFactory.getService();
        return matrixService.viewMatrix();
    }
}
