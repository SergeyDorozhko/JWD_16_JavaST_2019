package by.dorozhko.transport.hello_world;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import org.testng.Assert;

public class HelloWorldTest {
    HelloWorld calc = new HelloWorld();


    @Test(description = "positiv scenario, cheack sum of two numbers", dataProvider = "Sergey")
    public void summTest(int[] ab, int c) {
        int actual = calc.summ(ab[0], ab[1]);
        int exp = c;
        Assert.assertEquals(actual, exp);
    }

    @DataProvider(name = "Sergey")
    public Object[][] createDateForSumm() {
        return new Object[][]{
                {new int[]{3, 5}, 8},
                {new int[]{-3, 3}, 0},
                {new int[]{3, 2}, 5},
                {new int[]{30, 50}, 80}
        };
    }
}