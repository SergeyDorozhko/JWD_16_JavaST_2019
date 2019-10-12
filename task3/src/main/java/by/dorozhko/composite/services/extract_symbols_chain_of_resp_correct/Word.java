package by.dorozhko.composite.services.extract_symbols_chain_of_resp_correct;

import by.dorozhko.composite.entity.Component;
import by.dorozhko.composite.entity.Composite;
import by.dorozhko.composite.entity.LeafSymbol;
import by.dorozhko.composite.services.parser.ParseWordBySymbol;

import java.util.List;

public class Word implements Handler {

    private String word;
    private Composite composite;

    @Override
    public void setText(String text) {
        word = text;
    }

    @Override
    public Composite handlerRequest(Composite textPart) {
        composite = textPart;

        ParseWordBySymbol parser = ParseWordBySymbol.getInstance();
        List<Character> symbols = parser.parse(word);
        for (Character symbol : symbols) {
            Component symbolComponent = new LeafSymbol(symbol);
            composite.add(symbolComponent);
        }
        return composite;
    }
}
