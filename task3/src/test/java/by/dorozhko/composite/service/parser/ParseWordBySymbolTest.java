package by.dorozhko.composite.service.parser;

import by.dorozhko.composite.services.parser.ParseWordBySymbol;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ParseWordBySymbolTest {
    @Test(description = "test parse data to Symbols", dataProvider = "createDataForParserTest")
    public void testParser(String text, int expectedResult){
        ParseWordBySymbol parser = ParseWordBySymbol.getInstance();
        Assert.assertEquals(parser.parse(text).size(), expectedResult);

    }

    @DataProvider(name = "createDataForParserTest")
    private Object[][] createDataForTest(){
        return new Object[][]{
                {"qwe1234", 7},
                {" dsw ", 5},
                {"", 0},
                {"!?A", 3}

        };
    }
}
