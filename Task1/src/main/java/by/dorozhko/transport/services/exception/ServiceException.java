package by.dorozhko.transport.services.exception;

public class ServiceException extends Exception {
    /**
     * public constructor without arguments.
     */
    public ServiceException() {
        super();
    }

    /**
     * public constructor with message argument.
     *
     * @param message Exception message.
     */
    public ServiceException(final String message) {
        super(message);
    }

    /**
     * public constructor with exception argument.
     *
     * @param e exeption.
     */
    public ServiceException(final Exception e) {
        super(e);
    }

    /**
     * public constructor with arguments.
     *
     * @param message Exception message.
     * @param e       Exception.
     */
    public ServiceException(final String message, final Exception e) {
        super(message, e);
    }
}
