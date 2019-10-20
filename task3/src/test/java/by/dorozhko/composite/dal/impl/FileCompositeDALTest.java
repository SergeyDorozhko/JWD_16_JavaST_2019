package by.dorozhko.composite.dal.impl;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertThrows;

public class FileCompositeDALTest {

    FileCompositeDAL dal = new FileCompositeDAL();


    @Test(description = "Exception Test  for Read", dataProvider = "negotivePathRead")
    public void readExceptionTest(String path) {
        assertThrows(Exception.class, () -> dal.read(path));
    }


    @DataProvider(name = "negotivePathRead")
    public Object[] createNegotiveReadPathList() {
        return new Object[]{
                "data/wrongdata.txt",
                "wrongpath.txt",
                " data/text.txt"

        };
    }

    @Test(description = "Exception test for write", dataProvider = "negotivePathWrite")
    public void writeExeptionTest(String text, String path) {
        assertThrows(Exception.class, () -> dal.write(text, path));
    }

    @DataProvider(name = "negotivePathWrite")
    public Object[][] createNegotiveWritePathList(){
        return new Object[][] {
                {"wrong test", " data\\testWrongWrite.txt"},
                {"wrong test", "data\\ wrong test/testWrongWrite.txt"}

        };
    }

}
