package by.dorozhko.composite.services.extract_symbols_chain_of_resp_correct;

import by.dorozhko.composite.entity.Composite;

public interface Handler {
    /**
     * Method first parse text then make a composition.
     *
     * @param text      incoming text.
     * @param composite tree of text parts developing
     *                  from text branch by branch.
     * @return tree text composite.
     */
    Composite handlerRequest(String text, Composite composite);
}
