package by.dorozhko.composite.services.parser;

import java.util.ArrayList;
import java.util.List;

public final class ParseWordBySymbol {
    /**
     * single tone.
     */
    private static final ParseWordBySymbol INSTANCE
            = new ParseWordBySymbol();

    private ParseWordBySymbol() { }

    /**
     * link to object of class.
     * @return link.
     */
    public static ParseWordBySymbol getInstance() {
        return INSTANCE;
    }

    /**
     * parser text to symbols.
     * @param word text to parse.
     * @return list of symbols.
     */
    public List<Character> parse(final String word) {
        List<Character> result = new ArrayList<>();
        char[] arrayOfchar = word.toCharArray();
        for (int i = 0; i < arrayOfchar.length; i++) {
            result.add(arrayOfchar[i]);
        }
        return result;
    }
}
