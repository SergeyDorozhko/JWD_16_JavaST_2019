package by.dorozhko.matrix.repository.storage.specification;

import by.dorozhko.matrix.repository.storage.MatrixStorage;
import by.dorozhko.matrix.repository.storage.StorageSpecification;

public class SetWithTimeUnit implements StorageSpecification {
    /**
     * connecting to storage method which set matrix element using TimeUnit.
     * @param verticalPosition vertical position of element.
     * @param horizontalPosition horizontal position of element.
     * @param value value to set up.
     * @return result of setting.
     */
    @Override
    public boolean setMatrixElement(final int verticalPosition,
                                    final int horizontalPosition,
                                    final int value) {
        MatrixStorage storage = MatrixStorage.getInstance();
        storage.setElementByTimeUnit(verticalPosition,
                horizontalPosition, value);

        return true;
    }
}
