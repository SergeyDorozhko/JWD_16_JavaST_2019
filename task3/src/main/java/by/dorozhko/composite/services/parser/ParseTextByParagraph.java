package by.dorozhko.composite.services.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ParseTextByParagraph {
    /**
     * Single tone.
     */
    private static final ParseTextByParagraph INSTANCE
            = new ParseTextByParagraph();

    private ParseTextByParagraph() {
    }

    /**
     * Teamplate for parsing text for paragraph.
     */
    private String paragraphTeamplate =
            "[\\t][\\w\\W]+?[\\n]+?|[\\s]{4}[\\w\\W]+?[\\n]+?";

    /**
     *
     */
    private Pattern teamplate = Pattern.compile(paragraphTeamplate);

    public static ParseTextByParagraph getInstance() {
        return INSTANCE;
    }

    public List<String> parse(final String text) {
        List<String> result = new ArrayList<>();
        Matcher matcher = teamplate.matcher(text);
        while (matcher.find()) {
            result.add(matcher.group());
        }

        return result;
    }

}
