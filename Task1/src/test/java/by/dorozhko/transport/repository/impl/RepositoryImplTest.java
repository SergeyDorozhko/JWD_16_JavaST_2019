package by.dorozhko.transport.repository.impl;

import by.dorozhko.transport.entity.Carriage;
import by.dorozhko.transport.entity.Train;
import by.dorozhko.transport.entity.TransportEntity;
import by.dorozhko.transport.entity.params.CarriageType;
import by.dorozhko.transport.entity.params.EngineType;
import by.dorozhko.transport.repository.TransportSpecification;
import by.dorozhko.transport.repository.storage.Storage;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class RepositoryImplTest {
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

    }


    @Test(description = "Test addition", dataProvider = "dataForAddtest")
    public void addTest(TransportEntity transportEntity, boolean expectedResult) {

        boolean actualResult = repository.add(transportEntity);

        Assert.assertEquals(actualResult, expectedResult);
    }


    @DataProvider(name = "dataForAddtest")
    Object[][] createDataForAddTest() {
        return new Object[][]{
                {new Carriage("Petya", 12000, 30, CarriageType.ECONOM_CLASS_SITTING, 120, 154), true},
                {new Carriage("Petya", 12000, 30, CarriageType.ECONOM_CLASS_SITTING, 120, 154), true},
                {new Train("saf", 15045, 40, EngineType.DIESEL, 500, 100), true},
        };
    }

    @Test(description = "Test deletion", dataProvider = "dataForDeleteTest")
    public void deleteTest(int value, String expectedResult) {

        String actualResult = repository.delete(value);

        Assert.assertEquals(actualResult, expectedResult);
    }

    @DataProvider(name = "dataForDeleteTest")
    Object[][] createDataOfDel() {
        return new Object[][]{
                {new Integer(1), "Carriage"},
                {new Integer(2), "Carriage"},
                {new Integer(4), "Train"},
        };
    }


}