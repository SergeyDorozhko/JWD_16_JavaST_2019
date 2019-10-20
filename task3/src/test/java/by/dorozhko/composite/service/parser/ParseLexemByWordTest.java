package by.dorozhko.composite.service.parser;

import by.dorozhko.composite.services.parser.ParseLexemByWord;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class ParseLexemByWordTest {
    @Test(description = "parser test", dataProvider = "createLexemForParse")
    public void parserTest(String text, int expectedResult){
        ParseLexemByWord parser = ParseLexemByWord.getInstance();
        List<String> actualList = parser.parse(text);
        Assert.assertEquals(actualList.size(), expectedResult);
    }

    @DataProvider(name ="createLexemForParse")
    public Object[][] createDataForParseTest(){
        return new Object[][]{
                {"Lexem!", 2},
                {"test'2", 3},
                {"'word-word'", 5},
                {"lexem", 1},
                {"?@$%^#", 6}
        };
    }
}
