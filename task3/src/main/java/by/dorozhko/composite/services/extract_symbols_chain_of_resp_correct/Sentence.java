package by.dorozhko.composite.services.extract_symbols_chain_of_resp_correct;

import by.dorozhko.composite.entity.Composite;
import by.dorozhko.composite.entity.CompositeLexem;
import by.dorozhko.composite.services.parser.ParseSentenceByLexem;

import java.util.List;

public class Sentence extends ChainHandler implements Handler {
    private Lexem parent;

    public Sentence(Lexem parent) {
        this.parent = parent;
    }



    @Override
    public Composite handlerRequest(String newText, Composite textPart) {
        composite = textPart;
        text = newText;
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
