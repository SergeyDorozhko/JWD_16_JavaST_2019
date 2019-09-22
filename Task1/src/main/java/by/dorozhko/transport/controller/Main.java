package by.dorozhko.transport.controller;

import by.dorozhko.transport.view.ClientView;


public final class Main {

    private Main() {
    }

    /**
     * main method.
     *
     * @param args command line.
     */
    public static void main(final String[] args) {
        ClientView view = new ClientView();

        view.startView();
    }



}


