package by.dorozhko.matrix.repository.exception;

public class RepositoryException extends Exception {
    /**
     * public default constructor.
     */
    public RepositoryException() {
        super();
    }

    /**
     * Public constructor with massage param.
     * @param message information about exception.
     */
    public RepositoryException(final String message) {
        super(message);
    }

    /**
     * Public constructor with massage param.
     * @param ex exception.
     */
    public RepositoryException(final Exception ex) {
        super(ex);
    }

    /**
     * Public constructor with massage and exception param.
     * @param message information about exception.
     * @param ex exception
     */
    public RepositoryException(final String message, final Exception ex) {
        super(message, ex);
    }
}
