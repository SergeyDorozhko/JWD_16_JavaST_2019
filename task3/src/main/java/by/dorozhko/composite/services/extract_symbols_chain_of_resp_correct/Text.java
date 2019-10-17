package by.dorozhko.composite.services.extract_symbols_chain_of_resp_correct;

import by.dorozhko.composite.entity.Composite;
import by.dorozhko.composite.entity.CompositeParagraph;
import by.dorozhko.composite.services.parser.ParseTextByParagraph;

import java.util.List;

public class Text implements Handler {

    private Handler parent = null;

    public Text(final Handler parent) {
        this.parent = parent;
    }


    @Override
    public Composite handlerRequest(final String text,
                                    final Composite composite) {

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
