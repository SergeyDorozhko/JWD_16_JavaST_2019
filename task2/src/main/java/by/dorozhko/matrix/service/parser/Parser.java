package by.dorozhko.matrix.service.parser;

public class Parser {
    public static String[] parseBySpace(String line) {
        String [] dataFromLine = line.split("[ ]+");
        return dataFromLine;
    }

    public static String[] parseByEqualSing(String line) {
        String [] dataFromLine = line.split("=");
        return dataFromLine;
    }
}
