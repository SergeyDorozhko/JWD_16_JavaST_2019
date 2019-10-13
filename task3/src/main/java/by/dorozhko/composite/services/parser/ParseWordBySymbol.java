package by.dorozhko.composite.services.parser;

import java.util.ArrayList;
import java.util.List;

public final class ParseWordBySymbol {
    private static final ParseWordBySymbol INSTANCE
            = new ParseWordBySymbol();

    private ParseWordBySymbol() {
    }

    public static ParseWordBySymbol getInstance() {
        return INSTANCE;
    }

    public List<Character> parse(final String word) {
        List<Character> result = new ArrayList<>();
        char[] arrayOfchar = word.toCharArray();
        for (int i = 0; i < arrayOfchar.length; i++) {
            result.add(arrayOfchar[i]);
        }
        return result;
    }
}
