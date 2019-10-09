package by.dorozhko.composite.dal.exception;

public class ExceptionDAL extends Exception {
    public ExceptionDAL() {
        super();
    }

    public ExceptionDAL(Exception ex) {
        super(ex);
    }

    public ExceptionDAL(String msg){
        super(msg);
    }

    public ExceptionDAL(String msg, Exception ex){
        super(msg, ex);
    }
}
