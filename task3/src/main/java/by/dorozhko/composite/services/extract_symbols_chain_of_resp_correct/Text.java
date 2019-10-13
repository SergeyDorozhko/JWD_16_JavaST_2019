package by.dorozhko.composite.services.extract_symbols_chain_of_resp_correct;

import by.dorozhko.composite.entity.Composite;
import by.dorozhko.composite.entity.CompositeParagraph;
import by.dorozhko.composite.services.parser.ParseTextByParagraph;

import java.util.List;

public class Text extends ChainHandler implements Handler {

    private Paragraph parent = null;

    public Text(Paragraph parent) {
        this.parent = parent;
    }



    @Override
    public Composite handlerRequest(String newText, Composite textPart) {
composite = textPart;
text = newText;
        ParseTextByParagraph parser = ParseTextByParagraph.getInstance();
        List<String> paragraphs = parser.parse(text);

        for (String paragraph : paragraphs) {

             Composite compositeParagraph = new CompositeParagraph();
             composite.add(compositeParagraph);
             parent.handlerRequest(paragraph, compositeParagraph);
        }
        return composite;
    }
}
