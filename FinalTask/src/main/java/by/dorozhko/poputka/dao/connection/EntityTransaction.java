package by.dorozhko.poputka.dao.connection;

import by.dorozhko.poputka.dao.InterfaceDAO;
import by.dorozhko.poputka.dao.Transaction;

import java.sql.Connection;
import java.sql.SQLException;

public class EntityTransaction implements Transaction {
    private Connection connection;

    public void begin(InterfaceDAO dao, InterfaceDAO... daos) {
        if (connection == null) {
            connection = ConnectionPool.getInstance().takeConnection();
        }

        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println(e);
        }
        dao.setConnection(connection);
        for (InterfaceDAO daoElement : daos) {
            daoElement.setConnection(connection);
        }
    }

    public void end() {
        if (connection != null) {
            try {
                if (!connection.getAutoCommit()) {
                    connection.setAutoCommit(true);
                }
                connection.close();

            } catch (SQLException e) {
                System.out.println(e);
            }
            connection = null;
        }
    }

    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
