package by.dorozhko.composite.services.extract_symbols_chain_of_resp;

import by.dorozhko.composite.services.parser.ParseWordBySymbol;

import java.util.List;

public class Word implements Handler {

    private String word;

    public Word() {
    }

    @Override
    public void setText(String text) {
        word = text;
    }

    @Override
    public List handlerRequest() {
        ParseWordBySymbol parser = ParseWordBySymbol.getInstance();
        return parser.parse(word);
    }
}
