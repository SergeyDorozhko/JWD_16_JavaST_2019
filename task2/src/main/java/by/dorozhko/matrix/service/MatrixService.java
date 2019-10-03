package by.dorozhko.matrix.service;

public interface MatrixService {
    /**
     * create matrix.
     * @return result.
     */
    String createMatrix();

    /**
     * display matrix.
     * @return matrix view in string.
     */
    String viewMatrix();

    /**
     * method change "0" value in main diagonal.
     * @param specification specification.
     * @return result.
     */
    String initialiseMainDiagonal(String specification);
}
