package by.dorozhko.matrix.repository.storage.specificationImpl;

import by.dorozhko.matrix.repository.storage.MatrixStorage;
import by.dorozhko.matrix.repository.storage.StorageSpecification;

public class SetWithSemaphore implements StorageSpecification {
    @Override
    public boolean setMatrixElement(int verticalPosition, int horizontalPosition, int value) {
        MatrixStorage matrixStorage = MatrixStorage.getInstance();
        matrixStorage.setElementBySemaphore(verticalPosition, horizontalPosition, value);
        return true;
    }
}
