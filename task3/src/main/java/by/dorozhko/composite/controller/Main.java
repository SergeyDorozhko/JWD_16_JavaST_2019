package by.dorozhko.composite.controller;


import by.dorozhko.composite.view.Client;

public final class Main {

    private Main() { }

    /**
     * main method.
     *
     * @param args command line params.
     */
    public static void main(final String[] args) {


        Client client = new Client();
        client.startProgram();


    }


}
