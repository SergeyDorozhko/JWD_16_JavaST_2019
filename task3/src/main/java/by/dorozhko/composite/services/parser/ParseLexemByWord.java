package by.dorozhko.composite.services.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseLexemByWord {
    private static final ParseLexemByWord instance = new ParseLexemByWord();
    private ParseLexemByWord(){ }

    private String wordTeamplate = "(\\w)+|([\\W])";

    private Pattern teamplate = Pattern.compile(wordTeamplate);

    public static ParseLexemByWord getInstance(){
        return instance;
    }

    public List<String> parse (String lexem){
        List<String> result = new ArrayList<>();
        Matcher matcher = teamplate.matcher(lexem);
        while (matcher.find()) {
            result.add(matcher.group());
        }

        return result;
    }
}
