package by.dorozhko.matrix.controller;

import by.dorozhko.matrix.view.ClientView;


public final class Main {
    private Main() {
    }

    /**
     * main method, starting program.
     *
     * @param args arguments.
     */
    public static void main(final String[] args) {
        ClientView view = new ClientView();
        view.startView();


    }
}
