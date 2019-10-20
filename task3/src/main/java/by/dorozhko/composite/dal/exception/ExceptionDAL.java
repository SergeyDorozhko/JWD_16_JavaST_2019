package by.dorozhko.composite.dal.exception;

public class ExceptionDAL extends Exception {
    /**
     * default constructor without params.
     */
    public ExceptionDAL() {
        super();
    }

    /**
     * Constructor with exception param.
     * @param ex exception param.
     */
    public ExceptionDAL(final Exception ex) {
        super(ex);
    }

    /**
     * Constructor with massage param.
     * @param msg massage of exception.
     */
    public ExceptionDAL(final String msg) {
        super(msg);
    }

    /**
     * Constructor with exception param and massage.
     * @param msg massage of exception.
     * @param ex exception params.
     */
    public ExceptionDAL(final String msg, final Exception ex) {
        super(msg, ex);
    }
}
