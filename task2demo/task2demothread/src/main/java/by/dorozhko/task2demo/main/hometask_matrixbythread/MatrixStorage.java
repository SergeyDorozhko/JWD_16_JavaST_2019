package by.dorozhko.task2demo.main.hometask_matrixbythread;

import java.util.Arrays;

public class MatrixStorage {
    private final static MatrixStorage instance = new MatrixStorage();
    private int [][] matrix;

    private MatrixStorage() {
    }

    public void setMatrixSize (int size) {
        matrix = new int[size][size];
    }

    public static MatrixStorage getInstance() {
        return instance;
    }

    public void setMatrixIndex(int verticalIndex, int horizontalIndex, int value) {
        matrix[verticalIndex][horizontalIndex] = value;
    }

    public int getMatrixIndex(int verticalIndex, int horizontalIndex) {
        return matrix[verticalIndex][horizontalIndex];
    }

    public int getMatrixSize () {
        return matrix.length;
    }

    public String toString(){
        StringBuilder result = new StringBuilder();
        for(int[] el: matrix) {
            result.append(Arrays.toString(el) + "\n");
        }

        return result.toString();
    }
}
