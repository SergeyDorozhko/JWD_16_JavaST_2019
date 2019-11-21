package by.dorozhko.poputka.services;

public interface ConnectionService extends Service {
    void initConnectionPool();
    void closeConnectionPool();
}
