package by.dorozhko.task0;

import org.apache.log4j.Logger;

public final class HelloWorld {
    /** private default constructor.
     */
    private HelloWorld() { }


    /** Method main.
     * @param args Arguments
     */
    public static void main(final String[] args) {
        final Logger logger = Logger.getLogger(HelloWorld.class);
        String hello = "Hello World";

        logger.info(hello);

        logger.error("end of the program. Bye");
    }
}
