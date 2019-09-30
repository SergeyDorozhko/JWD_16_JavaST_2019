package by.dorozhko.matrix.repository.exception;

public class RepositoryException extends Exception {
    public RepositoryException(){
        super();
    }

    public RepositoryException(String message){
        super(message);
    }

    public RepositoryException(Exception ex) {
        super(ex);
    }

    public RepositoryException (String message, Exception ex) {
        super(message, ex);
    }
}
