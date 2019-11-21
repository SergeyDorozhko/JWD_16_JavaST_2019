package by.dorozhko.poputka.dao;

public interface Transaction {

    /**
     * Method to start transaction. Can take one or more DAO which need to make
     * transaction. This method set autocommit value to false.
     *
     * @param dao  dao which will use connection to database to do transaction.
     * @param daos more dao (if need) to do transaction.
     */
    void begin(InterfaceDAO dao, InterfaceDAO... daos);

    /**
     * Method ends transaction. Return value
     * of autocommit to true. And return connection
     * to pool of connections.
     */
    void end();

    /**
     * Makes all changes made since the previous
     * commit/rollback permanent and releases any
     * database locks currently held by this Connection
     * object.
     */
    void commit();

    /**
     * Undoes all changes made in the current transaction
     * and releases any database locks currently held by
     * this Connection object.
     */
    void rollback();


}
