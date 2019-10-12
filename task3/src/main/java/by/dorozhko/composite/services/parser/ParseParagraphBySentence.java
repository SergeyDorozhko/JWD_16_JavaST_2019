package by.dorozhko.composite.services.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseParagraphBySentence {
    private static final ParseParagraphBySentence instance = new ParseParagraphBySentence();

    private ParseParagraphBySentence() {
    }

    private String sentenceTeamplate = "[\\w\\W]+?[.!?]{1,3}";

    private Pattern teamplate = Pattern.compile(sentenceTeamplate);

    public static ParseParagraphBySentence getInstance() {
        return instance;
    }

    public List<String> parse(String paragraph) {
        List<String> result = new ArrayList<>();
        Matcher matcher = teamplate.matcher(paragraph);
        while (matcher.find()) {
            result.add(matcher.group());
        }

        return result;
    }

}
