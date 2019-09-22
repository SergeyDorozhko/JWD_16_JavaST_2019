package by.dorozhko.transport.services.validator;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ValidatorTest {
    @Test(description = "different scenario of input", dataProvider = "ArrayOfLines")
    public static void isValidLine(final String line, boolean expectedResult) {
        boolean actualResult = Validator.isValidLine(line);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @DataProvider(name = "ArrayOfLines")
    Object[][] createDataForValidatorTest() {
        return new Object[][]{
                {"Carriage:  name = Anton, weight = 18000, length = 35, carriageType = ECONOM_CLASS, maxValueOfBaggage = 40, numberOfPassengers = 85\n", true},
                {"Carriage:  name = , weight = 18000, length = 35, carriageType = ECONOM_CLASS, maxValueOfBaggage = 40, numberOfPassengers = 85\n", false},
                {"Carriage:  name = Anton, weight = , length = 35, carriageType = ECONOM_CLASS, maxValueOfBaggage = 40, numberOfPassengers = 85\n", false},
                {"Carriage:  name = Anton, weight = 18000, length = , carriageType = ECONOM_CLASS, maxValueOfBaggage = 40, numberOfPassengers = 85\n", false},
                {"Carriage:  name = Anton, weight = 18000, length = 35, carriageType = , maxValueOfBaggage = 40, numberOfPassengers = 85\n", false},
                {"Carriage:  name = Anton, weight = 18000, length = 35, carriageType = ECONOM_CLASS, maxValueOfBaggage = , numberOfPassengers = \n", false},
                {"Train:  name = Anton, weight = 18000, length = 35, carriageType = ECONOM_CLASS, maxValueOfBaggage = 40, numberOfPassengers = 85\n", false},
                {"Carriage:  name = Anton, weight = -18000, length = 35, carriageType = ECONOM_CLASS, maxValueOfBaggage = 40, numberOfPassengers = 85\n", false},
                {"jghjfgdf hvggh  gh g  fc vbncf x  cbbh  85\n", false},
                {"Carriage:  name = Anton, weight = 1835134681511756121321000, length = 35, carriageType = ECONOM_CLASS, maxValueOfBaggage = 40, numberOfPassengers = 85\n", false},
                {"Carriage:  name = Anton, weight = 1835, length = 35, carriageType = DIESEL, maxValueOfBaggage = 40, numberOfPassengers = 85\n", false},
                {"Carriage:  name  Anton, weight = 1835, length = 35, carriageType = DIESEL, maxValueOfBaggage = 40, numberOfPassengers = 85\n", false},
                {"Carriage:  name = Anton, length = 35, carriageType = DIESEL, maxValueOfBaggage = 40, numberOfPassengers = 85\n", false},

                {"Train: name = Vasia, weight = 2000, length = 35, engineType = DIESEL, enginePower = 800, maxSpeed = 90", true},
                {"Train: name = , weight = 2000, length = 35, engineType = DIESEL, enginePower = 800, maxSpeed = 90", false},
                {"Train: name = Vasia, weight = , length = 35, engineType = DIESEL, enginePower = 800, maxSpeed = 90", false},
                {"Train: name = Vasia, weight = 2000, length = , engineType = DIESEL, enginePower = 800, maxSpeed = 90", false},
                {"Train: name = Vasia, weight = 2000, length = 35, engineType = , enginePower = 800, maxSpeed = 90", false},
                {"Train: name = Vasia, weight = 2000, length = 35, engineType = DIESEL, enginePower = , maxSpeed = 90", false},
                {"Train: name = Vasia, weight = 2000, length = 35, engineType = DIESEL, enginePower = 800, maxSpeed = ", false},
                {"Carriage: name = Vasia, weight = 2000, length = 35, engineType = DIESEL, enginePower = 800, maxSpeed = 90", false},
                {"Train: name = Vasia, weight = 2000, length = 35, engineType = DIESEL, enginePower = -800, maxSpeed = 90", false},
                {"Train: name = Vasia, weight = 2000, length = 35, engineType = ECONOM_CLASS, enginePower = 800, maxSpeed = 90", false},
                {"Train:", false},
                {"Train:weight = 2000, length = 35, engineType = ECONOM_CLASS, enginePower = 800, maxSpeed = 90", false},

        };
    }

    @Test(description = "check input line is a number", dataProvider = "dataCheckNumber")
    public void isNumberTest(String number, boolean expectedResult) {
        boolean actualResult = Validator.isNumber(number);
        Assert.assertEquals(actualResult, expectedResult);
    }

    @DataProvider(name = "dataCheckNumber")
    Object[][] createNumbers() {
        return new Object[][]{
                {"10", true},
                {"dsf", false},
                {"0", true},
                {"null", false},
                {"one", false},
                {"-10", true},


        };
    }

}
