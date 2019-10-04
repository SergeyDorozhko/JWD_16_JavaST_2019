package by.dorozhko.matrix.service.parser;

public final class Parser {

    private Parser() { }

    /**
     * parse line by space.
     * @param line line with params.
     * @return array of string.
     */
    public static String[] parseBySpace(final String line) {
                return line.split("[ ]+");
    }

    /**
     * parse line by equal sing.
     * @param line line with params.
     * @return array of data.
     */
    public static String[] parseByEqualSing(final String line) {
                return line.split("=");
    }
}
