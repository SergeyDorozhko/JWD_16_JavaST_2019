package by.dorozhko.matrix.repository.storage;

public interface StorageSpecification {
    /**
     * interface for connecting to special synchronization method.
     * @param verticalPosition vertical position.
     * @param horizontalPosition horizontal position.
     * @param value value to set up for matrix element.
     * @return result of setup.
     */
    boolean setMatrixElement(int verticalPosition,
                             int horizontalPosition,
                             int value);
}
