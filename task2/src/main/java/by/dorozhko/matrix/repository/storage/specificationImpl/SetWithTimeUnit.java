package by.dorozhko.matrix.repository.storage.specificationImpl;

import by.dorozhko.matrix.repository.storage.MatrixStorage;
import by.dorozhko.matrix.repository.storage.StorageSpecification;

public class SetWithTimeUnit implements StorageSpecification {

    @Override
    public boolean setMatrixElement(int verticalPosition, int horizontalPosition, int value) {
        MatrixStorage storage = MatrixStorage.getInstance();
        storage.setElementByTimeUnit(verticalPosition,horizontalPosition,value);

        return true;
    }
}
