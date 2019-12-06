package by.dorozhko.poputka.services;

import by.dorozhko.poputka.dao.Transaction;
import by.dorozhko.poputka.dao.TransactionFactory;

public class AbstractService {
    protected Transaction transaction
            = TransactionFactory.getInstance().getTransaction();
}
