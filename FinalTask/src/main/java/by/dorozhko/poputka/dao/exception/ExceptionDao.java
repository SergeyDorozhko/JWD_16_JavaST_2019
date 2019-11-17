package by.dorozhko.poputka.dao.exception;

public class ExceptionDao extends Exception {
    /**
     * Constructs an ExceptionDao object.
     */
    public ExceptionDao() {
    }

    /**
     * Constructs an ExceptionDao object with a given reason.
     *
     * @param message - a description of the exception.
     */
    public ExceptionDao(final String message) {
        super(message);
    }

    /**
     * Constructs an ExceptionDao object with a given reason and cause.
     *
     * @param message - a description of the exception.
     * @param cause   - the underlying reason for this ExceptionDao;
     *                may be null indicating the cause is
     *                non-existent or unknown.
     */
    public ExceptionDao(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs an ExceptionDao object with a given cause.
     *
     * @param cause - the underlying reason for this ExceptionDao;
     *               may be null indicating the cause is
     *               non-existent or unknown.
     */
    public ExceptionDao(final Throwable cause) {
        super(cause);
    }
}
