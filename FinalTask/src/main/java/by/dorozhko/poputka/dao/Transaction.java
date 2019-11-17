package by.dorozhko.poputka.dao;

public interface Transaction {


    void begin(InterfaceDAO dao, InterfaceDAO... daos);

    void end();

    void commit();

    void rollback();


}
