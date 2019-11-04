package by.dorozhko.xmlparse.dao.parsers.stax;

import by.dorozhko.xmlparse.dao.parsers.dom.TariffsDOMBuilder;
import by.dorozhko.xmlparse.entity.TariffType;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Set;

public class TariffsStAXBuilderTest {



    @Test(description = "StAX parser test", dataProvider = "pathToData")
    private void buildSetTariffsTest(String[] path, int expectedResult){

        TariffsStAXBuilder builder = new TariffsStAXBuilder();
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
