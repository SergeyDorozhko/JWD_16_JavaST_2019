package by.dorozhko.transport.repository.impl.transport_specification.find_impl;

import by.dorozhko.transport.entity.Carriage;
import by.dorozhko.transport.entity.Train;
import by.dorozhko.transport.entity.TransportEntity;
import by.dorozhko.transport.entity.params.CarriageType;
import by.dorozhko.transport.entity.params.EngineType;
import by.dorozhko.transport.repository.TransportSpecification;
import by.dorozhko.transport.repository.impl.RepositoryImpl;
import by.dorozhko.transport.repository.storage.Storage;
import by.dorozhko.transport.repository.transport_specification.find_impl.GetAllCarriages;
import by.dorozhko.transport.repository.transport_specification.find_impl.GetAllEntity;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class GetAllCarriagesTest {
    RepositoryImpl repository = new RepositoryImpl();

    @BeforeTest
    private void addEntityToStorage() {
        Storage storage = Storage.getInstanse();

        storage.add(new Carriage("Petya", 15000, 30, CarriageType.ECONOM_CLASS_SITTING, 120, 154));
        storage.add(new Carriage("Vasia", 19000, 30, CarriageType.ECONOM_CLASS, 120, 154));
        storage.add(new Carriage("Fedua", 16000, 30, CarriageType.COMPARTMENT, 120, 154));
        storage.add(new Carriage("Petya", 17000, 30, CarriageType.INTERNATIOAL_SECOND_CLASS, 120, 154));
        storage.add(new Carriage("Petya", 3000, 30, CarriageType.ECONOM_CLASS_SITTING, 120, 154));
        storage.add(new Carriage("Petya", 12000, 30, CarriageType.ECONOM_CLASS_SITTING, 120, 154));
        storage.add(new Train("Petya", 12000, 30, EngineType.DIESEL, 120, 154));
        storage.add(new Carriage("Petya", 12000, 30, CarriageType.ECONOM_CLASS_SITTING, 120, 154));

    }

    @Test(description = "testQuery", dataProvider = "specification data")
    public void queryTest(TransportSpecification specification) {
        Storage storage = Storage.getInstanse();

        List<TransportEntity> expectedList = storage.getAll();

        List<TransportEntity> actualList = repository.query(specification);

        Assert.assertEquals(actualList.size(), expectedList.size() - 1);
    }


    @DataProvider(name = "specification data")
    Object[] createSpecification() {
        return new Object[]{
                new GetAllCarriages(),
        };
    }

}
