package by.dorozhko.composite.services.extract_symbols_chain_of_resp_correct;

import by.dorozhko.composite.entity.Composite;
import by.dorozhko.composite.entity.CompositeLexem;
import by.dorozhko.composite.services.parser.ParseSentenceByLexem;

import java.util.List;

public class Sentence implements Handler {
    private Handler parent;

    public Sentence(final Handler parent) {
        this.parent = parent;
    }


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
