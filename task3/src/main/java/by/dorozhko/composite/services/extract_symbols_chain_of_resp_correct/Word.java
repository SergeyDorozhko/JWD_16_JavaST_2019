package by.dorozhko.composite.services.extract_symbols_chain_of_resp_correct;

import by.dorozhko.composite.entity.Component;
import by.dorozhko.composite.entity.Composite;
import by.dorozhko.composite.entity.LeafSymbol;
import by.dorozhko.composite.services.parser.ParseWordBySymbol;

import java.util.List;

public class Word extends ChainHandler implements Handler {



    @Override
    public Composite handlerRequest(String newText, Composite textPart) {
        composite = textPart;
        text = newText;

        ParseWordBySymbol parser = ParseWordBySymbol.getInstance();
        List<Character> symbols = parser.parse(text);
        for (Character symbol : symbols) {
            Component symbolComponent = new LeafSymbol(symbol);
            composite.add(symbolComponent);
        }
        return composite;
    }
}
