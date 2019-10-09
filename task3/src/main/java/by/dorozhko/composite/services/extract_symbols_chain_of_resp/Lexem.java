package by.dorozhko.composite.services.extract_symbols_chain_of_resp;

import by.dorozhko.composite.services.parser.ParseLexemByWord;

import java.util.List;

public class Lexem implements Handler {
    private String lexem;
    private Word root;

    public Lexem(Word newRoot) {
        this.root = newRoot;
    }

    @Override
    public void setText(String text) {
        lexem = text;
    }

    @Override
    public List handlerRequest() {
        ParseLexemByWord parser = ParseLexemByWord.getInstance();
        return parser.parse(lexem);
    }
}
