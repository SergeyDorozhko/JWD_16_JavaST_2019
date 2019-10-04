package by.dorozhko.matrix.repository.storage.specification;

import by.dorozhko.matrix.repository.storage.MatrixStorage;
import by.dorozhko.matrix.repository.storage.StorageSpecification;

public class SetWithSemaphore implements StorageSpecification {
    /**
     * Connecting to the storage thread secured method of setting element.
     *
     * @param verticalPosition   vertical position of element.
     * @param horizontalPosition horizontal position of element.
     * @param value              setting value.
     * @return result of setting.
     */
    @Override
    public boolean setMatrixElement(final int verticalPosition,
                                    final int horizontalPosition,
                                    final int value) {
        MatrixStorage matrixStorage = MatrixStorage.getInstance();
        matrixStorage.setElementBySemaphore(verticalPosition,
                horizontalPosition, value);
        return true;
    }
}
