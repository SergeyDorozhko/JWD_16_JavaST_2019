package by.dorozhko.composite.service.parser;

import by.dorozhko.composite.services.parser.ParseParagraphBySentence;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ParseParagrapphBySentenceTest {
    @Test(description = "testParagraphBySentensesTest", dataProvider = "dataForTest")
    public void parserTest(String text, int expectedResult) {
        ParseParagraphBySentence parser = ParseParagraphBySentence.getInstance();
        Assert.assertEquals(parser.parse(text).size(), expectedResult);
    }

    @DataProvider(name = "dataForTest")
    private Object[][] createDataForTest() {
        return new Object[][]{
                {"Some text. One more sentence! And more... ", 3},
                {"Some text  One more sentence  And more ", 0},
                {"Some text. One more sentence!? And more... ", 3},
                {"", 0},
                {" ", 0},
                {"?", 0},
                {"...", 0},
                {"!?", 0},


        };
    }
}
