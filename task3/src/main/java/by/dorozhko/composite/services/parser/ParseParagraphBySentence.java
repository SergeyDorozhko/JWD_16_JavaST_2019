package by.dorozhko.composite.services.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ParseParagraphBySentence {
    /**
     * single tone.
     */
    private static final ParseParagraphBySentence INSTANCE
            = new ParseParagraphBySentence();

    private ParseParagraphBySentence() { }

    /**
     * regex to parse text to sentences.
     */
    private String sentenceTeamplate = "[\\w\\W&&[^.!?]]+?[.!?]{1,3}";

    /**
     * pattern fo parse.
     */
    private Pattern teamplate = Pattern.compile(sentenceTeamplate);

    /**
     * Link for object of class.
     * @return link.
     */
    public static ParseParagraphBySentence getInstance() {
        return INSTANCE;
    }

    /**
     * parser text to sentences.
     * @param paragraph text to parse.
     * @return list of sentences.
     */
    public List<String> parse(final String paragraph) {
        List<String> result = new ArrayList<>();
        Matcher matcher = teamplate.matcher(paragraph);
        while (matcher.find()) {
            result.add(matcher.group());
        }

        return result;
    }

}
