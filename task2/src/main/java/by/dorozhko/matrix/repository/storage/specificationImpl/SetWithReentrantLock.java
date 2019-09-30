package by.dorozhko.matrix.repository.storage.specificationImpl;


import by.dorozhko.matrix.repository.storage.MatrixStorage;
import by.dorozhko.matrix.repository.storage.StorageSpecification;
import com.sun.org.apache.xml.internal.security.keys.storage.StorageResolver;

public class SetWithReentrantLock implements StorageSpecification {

    @Override
    public boolean setMatrixElement(int verticalPosition, int horizontalPosition, int value) {
        MatrixStorage storage = MatrixStorage.getInstance();
        storage.setElementByReentrantLock(verticalPosition, horizontalPosition, value);
        return true;
    }
}
