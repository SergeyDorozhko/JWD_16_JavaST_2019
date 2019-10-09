package by.dorozhko.composite.services.extract_symbols_chain_of_resp;

import by.dorozhko.composite.services.parser.ParseTextByParagraph;

import java.util.List;

public class Text implements Handler {
    private String text;
    private Paragraph parent = null;

    public Text(Paragraph parent) {
        this.parent = parent;
    }

    @Override
    public void setText(String newText) {
        text = newText;
    }

    @Override
    public List handlerRequest() {
        ParseTextByParagraph parser = ParseTextByParagraph.getInstance();
        return parser.parse(text);
    }
}
