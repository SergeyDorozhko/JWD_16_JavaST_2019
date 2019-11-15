package by.dorozhko.poputka.dao.exception;

public class ExceptionDao extends Exception {
    public ExceptionDao() {
    }

    public ExceptionDao(String message) {
        super(message);
    }

    public ExceptionDao(String message, Throwable cause) {
        super(message, cause);
    }

    public ExceptionDao(Throwable cause) {
        super(cause);
    }
}
