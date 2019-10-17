package by.dorozhko.composite.repository.exception;

public class ExceptionRepository extends Exception{


    public ExceptionRepository() {
    }

    public ExceptionRepository(String message) {
        super(message);
    }

    public ExceptionRepository(String message, Throwable cause) {
        super(message, cause);
    }

    public ExceptionRepository(Throwable cause) {
        super(cause);
    }


}
