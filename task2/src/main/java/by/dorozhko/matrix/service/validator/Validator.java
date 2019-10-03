package by.dorozhko.matrix.service.validator;

import by.dorozhko.matrix.service.parser.Parser;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Validator {

    /**
     * regex which check line is a number.
     */
    private static Pattern numberTeamplate = Pattern.compile("^\\-?\\d+$");

    private Validator() { }

    /**
     * Method check has list of lines a matrix NxN or not.
     * @param line List of lines with data.
     * @return has list of lines a matrix NxN or not.
     */
    public static boolean isValidMatrix(final List<String> line) {
        if (!isValidMatrixLine(line.get(0))) {
            return false;
        }

        int matrixSize = Parser.parseBySpace(line.get(0)).length;

        for (int i = 0; i < matrixSize; i++) {
            if (!isValidMatrixLine(line.get(i), matrixSize)
                    || !(Parser.parseBySpace(line.get(i))[i]).equals("0")) {
                return false;
            }
        }

        return !isValidMatrixLine(line.get(matrixSize), matrixSize);
    }

    private static boolean isValidMatrixLine(final String line) {
        String[] lineElements = Parser.parseBySpace(line);
        for (int i = 0; i < lineElements.length; i++) {
            Matcher matcher = numberTeamplate.matcher(lineElements[i]);
            if (!matcher.find()) {
                return false;
            }
        }
        return true;
    }

    private static boolean isValidMatrixLine(final String line,
                                             final int length) {
        String[] lineElements = Parser.parseBySpace(line);
        if (lineElements.length != length) {
            return false;
        }

        for (int i = 0; i < lineElements.length; i++) {
            Matcher matcher = numberTeamplate.matcher(lineElements[i]);
            if (!matcher.find()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method check data for initialize thread.
     * @param data data for thread.
     * @return is valid.
     */
    public static boolean isValidThreadData(final String data) {
        String[] threadData = Parser.parseByEqualSing(data);

        boolean isValidData =
                threadData.length == 2 && threadData[0].equals("ThreadValue");

        if (!isValidData) {
            return false;
        }
        Matcher matcher = numberTeamplate.matcher(threadData[1]);

        return matcher.find();
    }

}
