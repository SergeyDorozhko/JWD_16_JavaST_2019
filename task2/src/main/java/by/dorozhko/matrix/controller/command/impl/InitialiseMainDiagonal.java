package by.dorozhko.matrix.controller.command.impl;

import by.dorozhko.matrix.controller.command.Command;
import by.dorozhko.matrix.service.MatrixService;
import by.dorozhko.matrix.service.ServiceFactory;

public class InitialiseMainDiagonal implements Command {
    @Override
    public String execute(String specification) {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        MatrixService matrixService = serviceFactory.getService();
        return matrixService.initialiseMainDiagonal(specification);
    }
}
