package by.dorozhko.xmlparse.services.exception;

public class ServiceException extends Exception {
    /**
     * default constructor.
     */
    public ServiceException() {
        super();
    }

    /**
     * constructor with massage value.
     * @param message info of exception.
     */
    public ServiceException(final String message) {
        super(message);
    }

    /**
     * constructor with massage and cause params.
     * @param message value of exception.
     * @param cause cause of exception.
     */
    public ServiceException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * constructor with cause param.
     * @param cause cause of exception.
     */
    public ServiceException(final Throwable cause) {
        super(cause);
    }
}
