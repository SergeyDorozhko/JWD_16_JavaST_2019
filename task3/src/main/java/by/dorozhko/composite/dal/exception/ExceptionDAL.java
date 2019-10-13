package by.dorozhko.composite.dal.exception;

public class ExceptionDAL extends Exception {
    public ExceptionDAL() {
        super();
    }

    public ExceptionDAL(final Exception ex) {
        super(ex);
    }

    public ExceptionDAL(final String msg) {
        super(msg);
    }

    public ExceptionDAL(final String msg, final Exception ex) {
        super(msg, ex);
    }
}
