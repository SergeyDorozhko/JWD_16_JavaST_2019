package by.dorozhko.composite.services.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ParseSentenceByLexem {
    private static final ParseSentenceByLexem INSTANCE
            = new ParseSentenceByLexem();

    private ParseSentenceByLexem() {
    }

    private String lexemTeamplate = "[\\S]+";

    private Pattern teamplate = Pattern.compile(lexemTeamplate);

    public static ParseSentenceByLexem getInstance() {
        return INSTANCE;
    }

    public List<String> parse(final String sentence) {
        List<String> result = new ArrayList<>();
        Matcher matcher = teamplate.matcher(sentence);
        while (matcher.find()) {
            result.add(matcher.group());
        }

        return result;
    }

}
