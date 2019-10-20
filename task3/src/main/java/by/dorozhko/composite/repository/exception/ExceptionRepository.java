package by.dorozhko.composite.repository.exception;

public class ExceptionRepository extends Exception {

    /**
     * default constructor without params.
     */
    public ExceptionRepository() { }

    /**
     * Constructor with massage param.
     * @param message massage of exception.
     */
    public ExceptionRepository(final String message) {
        super(message);
    }

    /**
     * Constructor with exception param and message.
     * @param message massage of exception.
     * @param cause exception cause.
     */
    public ExceptionRepository(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor with exception param.
     * @param cause exception cause.
     */
    public ExceptionRepository(final Throwable cause) {
        super(cause);
    }


}
