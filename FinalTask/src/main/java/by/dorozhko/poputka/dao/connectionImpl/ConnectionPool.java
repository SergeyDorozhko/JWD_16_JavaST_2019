package by.dorozhko.poputka.dao.connectionImpl;

import by.dorozhko.poputka.dao.ConnectionDAO;
import by.dorozhko.poputka.dao.QuerySpecification;
import by.dorozhko.poputka.dao.exception.ExceptionDao;
import by.dorozhko.poputka.entity.Entity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private final Logger logger = LogManager.getLogger(getClass().getSimpleName());
    private static ConnectionPool instance;

    private BlockingQueue<ProxyConnection> availableConnections;

    private BlockingQueue<ProxyConnection> usedConnections;

    private static ReentrantLock locker = new ReentrantLock();

    private static AtomicBoolean isCreated = new AtomicBoolean(false);

    private String jdbcUrl;
    private String userLogin;
    private String pwd;
    private int poolSize;
    private String driver;
    private ConnectionPool() {
        System.out.println("pool--------------------");
        getProperties();

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            logger.error(e);
        }
        //TODO driver register and check creation of connections.

        init();
//        if(availableConnections.){
//            logger.error("no connection to DB");
//            throw new ExceptionInInitializerError("no connection to DB");
//        }

        usedConnections = new LinkedBlockingQueue<>();
    }


    private void init() {

        availableConnections = new LinkedBlockingQueue<>();




        for(int i = 0; i < poolSize; i++) {
            try {

                ProxyConnection proxyConnection = new ProxyConnection(DriverManager.getConnection(jdbcUrl, userLogin, pwd));
                //Connection connection = DriverManager.getConnection(jdbcUrl, userLogin, pwd);;
                //ProxyConnection proxyConnection = new ProxyConnection(connection);
                availableConnections.add(proxyConnection);
            }catch (SQLException ex){
            logger.error(ex);
            }
        }
        //TODO
    }

    private void getProperties(){
        ResourceBundle resourceBundle = ResourceBundle.getBundle("dbresource.database");
        jdbcUrl = resourceBundle.getString("db.url");
        userLogin = resourceBundle.getString("db.login");
        pwd = resourceBundle.getString("db.password");
        poolSize = Integer.parseInt(resourceBundle.getString("db.poolsize").trim());
        driver = resourceBundle.getString("db.driver");
    }

    public static ConnectionPool getInstance() {

        if(!isCreated.get()) {
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


    public Connection takeConnection(){
        ProxyConnection connection = null;
                try {
                  connection =  availableConnections.take();
                  usedConnections.put(connection);
                } catch (InterruptedException ex) {
                    logger.error(ex);
                    Thread.currentThread().interrupt();
                }
        return connection;
    }

    public void releaseConnection(Connection connection){
        ProxyConnection checkType = new ProxyConnection(connection);
        if(connection.getClass() == checkType.getClass()){
            ProxyConnection proxyConnection = (ProxyConnection) connection;
            usedConnections.remove(proxyConnection);
            availableConnections.offer(proxyConnection);
        }
    }

    public void closePool() {
        for (int i = 0; i < poolSize; i++) {
            try {
                ProxyConnection connection = availableConnections.take();
                try {
                    connection.realClose();
                } catch (SQLException e) {
                    logger.error(e);
                }
            } catch (InterruptedException e) {
                logger.error(e);
            }
        }

        //TODO deregistratio driver


    }
}
