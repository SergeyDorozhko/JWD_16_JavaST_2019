package by.dorozhko.transport.dal.exception;


public class DALException extends Exception {

    /**
     * public constructor without arguments.
     */
    public DALException() {
        super();
    }

    /**
     * public constructor with message argument.
     *
     * @param message Exception message.
     */
    public DALException(final String message) {
        super(message);
    }

    /**
     * public constructor with exception argument.
     *
     * @param e exeption.
     */
    public DALException(final Exception e) {
        super(e);
    }

    /**
     * public constructor with arguments.
     *
     * @param message Exception message.
     * @param e       Exception.
     */
    public DALException(final String message, final Exception e) {
        super(message, e);
    }
}
