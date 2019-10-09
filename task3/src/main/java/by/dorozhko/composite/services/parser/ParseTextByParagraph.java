package by.dorozhko.composite.services.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseTextByParagraph {
    private static final ParseTextByParagraph instance = new ParseTextByParagraph();
    private ParseTextByParagraph(){ }

    private String paragraphTeamplate = "[\\t][\\w\\W]+?[\\n]+?|[\\s]{4}[\\w\\W]+?[\\n]+?";

    private Pattern teamplate = Pattern.compile(paragraphTeamplate);

    public static ParseTextByParagraph getInstance(){
        return instance;
    }

    public List<String> parse (String text){
        List<String> result = new ArrayList<>();
        Matcher matcher = teamplate.matcher(text);
        while (matcher.find()) {
            result.add(matcher.group());
        }

        return result;
    }

}
