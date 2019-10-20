package by.dorozhko.composite.services.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ParseLexemByWord {
    /**
     * single tone.
     */
    private static final ParseLexemByWord INSTANCE = new ParseLexemByWord();

    private ParseLexemByWord() { }

    /**
     * Regex for parse lexem to words.
     */
    private String wordTemplate = "(\\w)+|([^ ])";

    /**
     * pattern for parse.
     */
    private Pattern template = Pattern.compile(wordTemplate);

    /**
     * get link for parser.
     * @return link.
     */
    public static ParseLexemByWord getInstance() {
        return INSTANCE;
    }

    /**
     * parser text to words.
     * @param lexem text to parse.
     * @return list of words.
     */
    public List<String> parse(final String lexem) {
        List<String> result = new ArrayList<>();
        Matcher matcher = template.matcher(lexem);
        while (matcher.find()) {
            result.add(matcher.group());
        }

        return result;
    }
}
