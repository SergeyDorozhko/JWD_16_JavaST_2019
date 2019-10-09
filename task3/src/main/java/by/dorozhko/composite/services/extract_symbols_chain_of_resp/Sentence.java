package by.dorozhko.composite.services.extract_symbols_chain_of_resp;

import by.dorozhko.composite.services.parser.ParseSentenceByLexem;

import java.util.List;

public class Sentence implements Handler {
    private String sentence;
    private Lexem parent;

    public Sentence(Lexem parent) {
        this.parent = parent;
    }

    @Override
    public void setText(String text) {
        sentence = text;
    }

    @Override
    public List handlerRequest() {
        ParseSentenceByLexem parser = ParseSentenceByLexem.getInstance();
        return parser.parse(sentence);
    }
}
