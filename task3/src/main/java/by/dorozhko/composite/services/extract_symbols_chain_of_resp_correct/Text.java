package by.dorozhko.composite.services.extract_symbols_chain_of_resp_correct;

import by.dorozhko.composite.entity.Composite;
import by.dorozhko.composite.entity.CompositeParagraph;
import by.dorozhko.composite.services.parser.ParseTextByParagraph;

import java.util.List;

public class Text implements Handler {
    private String text;
    private Composite composite;
    private Paragraph parent = null;

    public Text(Paragraph parent) {
        this.parent = parent;
    }

    @Override
    public void setText(String newText) {
        text = newText;
    }

    @Override
    public Composite handlerRequest(Composite textPart) {
composite = textPart;
        ParseTextByParagraph parser = ParseTextByParagraph.getInstance();
        List<String> paragraphs = parser.parse(text);

        for (String paragraph : paragraphs) {

             Composite compositeParagraph = new CompositeParagraph();
             composite.add(compositeParagraph);
             parent.setText(paragraph);
             parent.handlerRequest(compositeParagraph);
        }
        return composite;
    }
}
