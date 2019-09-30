package by.dorozhko.matrix.dal.exeption;

public class ExceptionDAL extends Exception {
    /**
     * default exception constructor.
     */
    public ExceptionDAL() {
        super();
    }

    /**
     * Constructor with message.
     * @param message exception message.
     */
    public ExceptionDAL(final String message) {
        super(message);
    }

    /**
     * Constructor take exception.
     * @param e exception.
     */
    public ExceptionDAL(final Exception e) {
        super(e);
    }

    /**
     * Constructor take message and exception.
     * @param message exception message.
     * @param ex exception.
     */
    public ExceptionDAL(final String message, final Exception ex) {
        super(message, ex);
    }
}
