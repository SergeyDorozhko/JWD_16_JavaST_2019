package by.dorozhko.matrix.repository;

import by.dorozhko.matrix.repository.exception.RepositoryException;

public interface MatrixRepository {
    void setMatrixSize(int size);
    int getMatrixElement(int verticalPosition, int horizontalPosition) throws RepositoryException;
    int getMatrixSize();
    int[][] getMatrix() throws RepositoryException;

    boolean setMatrixElement(int verticalPosition, int horizontalPosition, int value);

    boolean setMatrixElementByThreads(String specification, int verticalPosition, int horizontalPosition, int value);
}
