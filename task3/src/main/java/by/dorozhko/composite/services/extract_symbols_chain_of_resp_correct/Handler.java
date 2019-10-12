package by.dorozhko.composite.services.extract_symbols_chain_of_resp_correct;

import by.dorozhko.composite.entity.Composite;

public interface Handler {
    void setText(String text);
    Composite handlerRequest(Composite textPart);
}
