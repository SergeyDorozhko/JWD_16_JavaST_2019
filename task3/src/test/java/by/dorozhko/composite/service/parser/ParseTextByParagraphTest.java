package by.dorozhko.composite.service.parser;

import by.dorozhko.composite.services.parser.ParseTextByParagraph;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ParseTextByParagraphTest {
    @Test(description = "test text to paragraphs parser", dataProvider = "dataForTestParser")
    public void testParser(String text, int expectedResult) {
        ParseTextByParagraph parser = ParseTextByParagraph.getInstance();
        Assert.assertEquals(parser.parse(text).size(), expectedResult);
    }

    @DataProvider(name = "dataForTestParser")
    private Object[][] createDataForTest() {
        return new Object[][]{
                {"    New text. For test.", 0},
                {"    New text. For test.\n", 1},
                {"\tNew text. For test.\n", 1},
                {"   New text. For test.\n", 0},
                {"    New text. For test.\n    New text. For test.\n    New text. For test.\n    New text. For test.\n", 4},
                {"    New text. For test.\n      New text.    For test.\n    New   text. For test.\n    New text. For test.\n", 4},
                {"    New text. For test.\n\tNew text. For test.\n    New text. For test.\n    New text. For test.\n", 4},


        };
    }
}
