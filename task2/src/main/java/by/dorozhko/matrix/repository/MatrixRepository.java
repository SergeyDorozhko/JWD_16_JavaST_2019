package by.dorozhko.matrix.repository;

import by.dorozhko.matrix.repository.exception.RepositoryException;

public interface MatrixRepository {
    /**
     * Method creating matrix NxN with inserted size.
     *
     * @param size matrix size NxN.
     */
    void setMatrixSize(int size);

    /**
     * take matrix element.
     *
     * @param verticalPosition   vertical position of element.
     * @param horizontalPosition horizontal position of element.
     * @return value of matrix element.
     * @throws RepositoryException throws when matrix not creating.
     */
    int getMatrixElement(int verticalPosition,
                         int horizontalPosition) throws RepositoryException;

    /**
     * Get matrix size.
     * @return matrix NxN size.
     */
    int getMatrixSize();

    /**
     * Get matrix from storage.
     * @return matrix.
     * @throws RepositoryException throws when matrix not creating.
     */
    int[][] getMatrix() throws RepositoryException;

    /**
     * set value of matrix element.
     * @param verticalPosition vertical position of element.
     * @param horizontalPosition horizontal position of element.
     * @param value value of matrix element to set up.
     * @return result of setting.
     */
    boolean setMatrixElement(int verticalPosition,
                             int horizontalPosition,
                             int value);

    /**
     * set value of matrix element by specification (thread secured).
     * @param specification choose type of synchronization.
     * @param verticalPosition vertical position of element.
     * @param horizontalPosition horizontal position of element.
     * @param value value of matrix element to set up.
     * @return result of setting.
     */
    boolean setMatrixElementByThreads(String specification,
                                      int verticalPosition,
                                      int horizontalPosition,
                                      int value);
}
