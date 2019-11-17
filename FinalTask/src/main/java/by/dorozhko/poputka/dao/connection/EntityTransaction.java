package by.dorozhko.poputka.dao.connection;

import by.dorozhko.poputka.dao.InterfaceDAO;
import by.dorozhko.poputka.dao.Transaction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class EntityTransaction implements Transaction {
    /**
     * Logger of the class.
     */
    private final Logger logger
            = LogManager.getLogger(getClass().getSimpleName());
    /**
     * Connection to database.
     */
    private Connection connection;

    /**
     * Method to start transaction. Can take one or more DAO which need to make
     * transaction. This method set autocommit value to false.
     *
     * @param dao  dao which will use connection to database to do transaction.
     * @param daos more dao (if need) to do transaction.
     */
    public void begin(final InterfaceDAO dao, final InterfaceDAO... daos) {
        if (connection == null) {
            connection = ConnectionPool.getInstance().takeConnection();
        }

        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            logger.error(e);
        }
        dao.setConnection(connection);
        for (InterfaceDAO daoElement : daos) {
            daoElement.setConnection(connection);
        }
    }

    /**
     * Method ends transaction. Return value
     * of autocommit to true. And return connection
     * to pool of connections.
     */
    public void end() {
        if (connection != null) {
            try {
                if (!connection.getAutoCommit()) {
                    connection.setAutoCommit(true);
                }
                connection.close();

            } catch (SQLException e) {
                logger.error(e);
            }
            connection = null;
        }
    }

    /**
     * Makes all changes made since the previous
     * commit/rollback permanent and releases any
     * database locks currently held by this Connection
     * object.
     */
    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    /**
     * Undoes all changes made in the current transaction
     * and releases any database locks currently held by
     * this Connection object.
     */
    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            logger.error(e);
        }
    }

}
