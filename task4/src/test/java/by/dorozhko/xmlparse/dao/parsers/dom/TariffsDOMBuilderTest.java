package by.dorozhko.xmlparse.dao.parsers.dom;

import by.dorozhko.xmlparse.dao.parsers.TariffsBuilder;
import by.dorozhko.xmlparse.dao.parsers.stax.TariffsStAXBuilder;
import by.dorozhko.xmlparse.entity.TariffType;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Set;

public class TariffsDOMBuilderTest {



    @Test(description = "DOM parser test, positive", dataProvider = "pathToData")
    private void buildSetTariffsTest(String[] path, int expectedResult){

        TariffsDOMBuilder builder = new TariffsDOMBuilder();
        builder.buildSetTariffs(path[0], path[1]);
        Set<TariffType> tariffTypeSet = builder.getTariffs();
        int actualResult = tariffTypeSet.size();
        Assert.assertEquals(actualResult, expectedResult);
    }

    @DataProvider(name = "pathToData")
    public Object[][] createData(){
        return new Object[][] {
                {new String[] {"src/test/resources/tariffs.xml", "src/test/resources/tariffs.xsd"}, 16},

        };
    }



}
