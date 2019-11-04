package by.dorozhko.xmlparse.dao.validator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class XMLSchemaValidatorTest {

    @Test(description = "test validation", dataProvider = "createPathToValidating")
    public void validationTest(String [] path, boolean expectedResult){
        XMLSchemaValidator xmlSchemaValidator = new XMLSchemaValidator();
        boolean actualResult = xmlSchemaValidator.validate(path[0], path[1]);
        Assert.assertEquals(actualResult, expectedResult);

    }

    @DataProvider(name = "createPathToValidating")
    public Object[][] createData(){
        return new Object[][] {
                {new String[] {"src/test/resources/tariffs.xml", "src/test/resources/tariffs.xsd"}, true},
                {new String[] {"src/test/resources/invalidxml/noOperator.xml", "src/test/resources/tariffs.xsd"}, false},
                {new String[] {"src/test/resources/invalidxml/lessThen16.xml", "src/test/resources/tariffs.xsd"}, false},
                {new String[] {"src/test/resources/invalidxml/fileNotFound.xml", "src/test/resources/tariffs.xsd"}, false}

        };

    }


}
