package by.dorozhko.composite.service.parser;

import by.dorozhko.composite.services.parser.ParseSentenceByLexem;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ParseSentenceByLexemTest {
    @Test(description = "test parser sentence by lexem", dataProvider = "createSentencesForTest")
    public void testParset(String text, int expectedResult) {
        ParseSentenceByLexem parser = ParseSentenceByLexem.getInstance();
        Assert.assertEquals(parser.parse(text).size(), expectedResult);
    }


    @DataProvider(name = "createSentencesForTest")
    public Object[][] createDataForTest(){
        return new Object[][]{
                {"\"So, if you score in the 95th minute with a penalty everybody would say that's lucky but over the full game we had more chances, we were the better team, so I'm really happy about that\".", 36},
                {"dsfsdf.sdfd.dsff.,sdfdsf,sdf\"sdf", 1},
                {"",0},
                {" ", 0},
                {"\t",0},
                {"\r", 0},
                {"\rdsf", 1}

        };
    }

}
