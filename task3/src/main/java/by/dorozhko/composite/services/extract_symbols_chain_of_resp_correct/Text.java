package by.dorozhko.composite.services.extract_symbols_chain_of_resp_correct;

import by.dorozhko.composite.entity.Composite;
import by.dorozhko.composite.entity.CompositeParagraph;
import by.dorozhko.composite.services.parser.ParseTextByParagraph;

import java.util.List;

public class Text implements Handler {

    /**
     * next handler.
     */
    private Handler parent = null;

    /**
     * constructor take next handler as param.
     * @param nextParent next handler.
     */
    public Text(final Handler nextParent) {
        parent = nextParent;
    }

    /**
     * make actio and ask next handler.
     * @param text      incoming text.
     * @param composite tree of text parts developing
     *                  from text branch by branch.
     * @return composite.
     */
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
