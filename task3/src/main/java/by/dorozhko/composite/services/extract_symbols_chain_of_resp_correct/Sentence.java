package by.dorozhko.composite.services.extract_symbols_chain_of_resp_correct;

import by.dorozhko.composite.entity.Composite;
import by.dorozhko.composite.entity.CompositeLexem;
import by.dorozhko.composite.services.parser.ParseSentenceByLexem;

import java.util.List;

public class Sentence implements Handler {
    /**
     * next handler.
     */
    private Handler parent;

    /**
     * constructor takes next handler.
     * @param nextParent next handler.
     */
    public Sentence(final Handler nextParent) {
        parent = nextParent;
    }

    /**
     * make action and give ask next handler.
     * @param text      incoming text.
     * @param composite tree of text parts developing
     *                  from text branch by branch.
     * @return
     */
    @Override
    public Composite handlerRequest(final String text,
                                    final Composite composite) {

        ParseSentenceByLexem parser = ParseSentenceByLexem.getInstance();

        List<String> lexems = parser.parse(text);

        for (String lexem : lexems) {
            Composite lexemComposite = new CompositeLexem();
            composite.add(lexemComposite);
            parent.handlerRequest(lexem, lexemComposite);
        }
        return composite;
    }
}
