package by.dorozhko.composite.services.extract_symbols_chain_of_resp_correct;

import by.dorozhko.composite.entity.Composite;
import by.dorozhko.composite.entity.CompositeLexem;
import by.dorozhko.composite.services.parser.ParseSentenceByLexem;

import java.util.List;

public class Sentence implements Handler {
    private String sentence;
    private Lexem parent;
    private Composite composite;

    public Sentence(Lexem parent) {
        this.parent = parent;
    }

    @Override
    public void setText(String text) {
        sentence = text;
    }

    @Override
    public Composite handlerRequest(Composite textPart) {
        composite = textPart;
        ParseSentenceByLexem parser = ParseSentenceByLexem.getInstance();

        List<String> lexems = parser.parse(sentence);

        for (String lexem : lexems) {
            Composite lexemComposite = new CompositeLexem();
            composite.add(lexemComposite);
            parent.setText(lexem);
            parent.handlerRequest(lexemComposite);
        }
        return composite;
    }
}
