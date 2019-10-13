package by.dorozhko.composite.services.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ParseParagraphBySentence {
    private static final ParseParagraphBySentence INSTANCE
            = new ParseParagraphBySentence();

    private ParseParagraphBySentence() {
    }

    private String sentenceTeamplate = "[\\w\\W]+?[.!?]{1,3}";

    private Pattern teamplate = Pattern.compile(sentenceTeamplate);

    public static ParseParagraphBySentence getInstance() {
        return INSTANCE;
    }

    public List<String> parse(final String paragraph) {
        List<String> result = new ArrayList<>();
        Matcher matcher = teamplate.matcher(paragraph);
        while (matcher.find()) {
            result.add(matcher.group());
        }

        return result;
    }

}
