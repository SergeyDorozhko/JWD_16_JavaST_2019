package by.dorozhko.composite.services.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ParseSentenceByLexem {
    /**
     * single tone.
     */
    private static final ParseSentenceByLexem INSTANCE
            = new ParseSentenceByLexem();

    private ParseSentenceByLexem() { }

    /**
     * regex to parse to lexems.
     */
    private String lexemTeamplate = "[\\S]+";

    /**
     * pattern to parse.
     */
    private Pattern teamplate = Pattern.compile(lexemTeamplate);

    /**
     * link to parser.
     * @return link.
     */
    public static ParseSentenceByLexem getInstance() {
        return INSTANCE;
    }

    /**
     * parse text to lexems.
     * @param sentence text to parse.
     * @return list of lexems.
     */
    public List<String> parse(final String sentence) {
        List<String> result = new ArrayList<>();
        Matcher matcher = teamplate.matcher(sentence);
        while (matcher.find()) {
            result.add(matcher.group());
        }

        return result;
    }

}
