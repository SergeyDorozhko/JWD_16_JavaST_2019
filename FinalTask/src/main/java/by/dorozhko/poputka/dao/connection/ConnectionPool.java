package by.dorozhko.poputka.dao.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public final class ConnectionPool {
    /**
     * Logger of class.
     */
    private final Logger logger = LogManager.getLogger(getClass().getName());

    /**
     * static variable single_instance of type ConnectionPool.
     */
    private static ConnectionPool instance;
    /**
     * Pool of available compounds with database.
     */
    private BlockingQueue<ProxyConnection> availableConnections;
    /**
     * Pool of using compounds with database.
     */
    private BlockingQueue<ProxyConnection> usedConnections;

    /**
     * A reentrant mutual exclusion Lock with the same basic
     * behavior and semantics as the implicit monitor lock
     * accessed using synchronized methods and statements,
     * but with extended capabilities.
     */
    private static ReentrantLock locker = new ReentrantLock();

    /**
     * Identifier which shows was this class created before or no.
     * If it was initialised identifier has value true, otherwise
     * false.
     */
    private static AtomicBoolean isCreated = new AtomicBoolean(false);
    /**
     * Value of database location.
     */
    private String jdbcUrl;
    /**
     * Login to database.
     */
    private String userLogin;
    /**
     * Password to database.
     */
    private String pwd;
    /**
     * default size of pool of connections to database.
     */
    private int poolSize;
    /**
     * driver for using database.
     */
    private String driver;

    private ConnectionPool() {
        getProperties();

        try {
            //TODO if web dont work check registration driver.
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            logger.error(e);
        }

        init();

        checkPoolSize();
        usedConnections = new LinkedBlockingQueue<>();
    }

    /**
     * Initializing pool of connections to dataBase.
     */
    private void init() {

        availableConnections = new LinkedBlockingQueue<>();

        for (int i = 0; i < poolSize; i++) {
            try {

                ProxyConnection proxyConnection = new ProxyConnection(
                        DriverManager.getConnection(jdbcUrl, userLogin, pwd));
                availableConnections.add(proxyConnection);
            } catch (SQLException ex) {
                logger.error(ex);
            }
        }
    }

    /**
     * Method get properties to pool of connections from property file.
     */
    private void getProperties() {
        ResourceBundle resourceBundle
                = ResourceBundle.getBundle("dbresource.database");
        jdbcUrl = resourceBundle.getString("db.url");
        userLogin = resourceBundle.getString("db.login");
        pwd = resourceBundle.getString("db.password");
        poolSize = Integer.parseInt(
                resourceBundle.getString("db.poolsize").trim());
        driver = resourceBundle.getString("db.driver");
    }

    private void checkPoolSize() {
        int poolRealSize = ((LinkedBlockingQueue) availableConnections).size();
        if (poolRealSize == poolSize) {
            logger.debug("pool is correct");
        } else if (poolRealSize > 0) {
            logger.warn("Connection leak, created pool size: ", poolRealSize);
        } else {
            logger.error("no connection to DB");
            throw new ExceptionInInitializerError("no connection to DB");
        }
    }

    /**
     * Access to single instance of type ConnectionPool.
     * @return link to instance of class.
     */
    public static ConnectionPool getInstance() {

        if (!isCreated.get()) {
            try {
                locker.lock();
                if (instance == null) {
                    instance = new ConnectionPool();
                    isCreated.set(true);
                }
            } finally {
                locker.unlock();
            }
        }
        return instance;
    }

    /**
     * Take connection to database method.
     * @return connection to database.
     */
    public Connection takeConnection() {
        ProxyConnection connection = null;
        try {
            connection = availableConnections.take();
            usedConnections.put(connection);
        } catch (InterruptedException ex) {
            logger.error(ex);
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    /**
     * Return connection to pool after use.
     * @param connection using connection of pool.
     */
    public void releaseConnection(final Connection connection) {
        if (connection instanceof ProxyConnection) {
            ProxyConnection proxyConnection = (ProxyConnection) connection;

            usedConnections.remove(proxyConnection);
            availableConnections.offer(proxyConnection);
        }
    }

    /**
     * Clothing pool method which clothe all connections
     * and then deregister drivers.
     */
    public void closePool() {
        for (int i = 0; i < poolSize; i++) {
            try {
                ProxyConnection connection = availableConnections.take();

                connection.realClose();

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.error(e);
            } catch (SQLException e) {
                logger.error(e);
            }
        }

        deregisterDriver();


    }

    private void deregisterDriver() {
        Enumeration<Driver> drivers = DriverManager.getDrivers();

        while (drivers.hasMoreElements()) {

            try {
                DriverManager.deregisterDriver(drivers.nextElement());
            } catch (SQLException e) {
                logger.error(e);
            }
        }
    }
}
