package by.dorozhko.composite.services.extract_symbols_chain_of_resp_correct;

import by.dorozhko.composite.entity.Composite;
import by.dorozhko.composite.entity.CompositeWord;
import by.dorozhko.composite.services.parser.ParseLexemByWord;

import java.util.List;

public class Lexem implements Handler {
    /**
     * Have knowledge about root handler.
     */
    private Word root;

    /**
     * public constructor.
     *
     * @param newRoot take root handler.
     */
    public Lexem(final Word newRoot) {
        this.root = newRoot;
    }

    /**
     * Method first parse text then make a composition.
     *
     * @param text      incoming text.
     * @param composite tree of text parts developing
     *                  from text branch by branch.
     * @return tree text composite.
     */
    @Override
    public Composite handlerRequest(final String text,
                                    final Composite composite) {


        ParseLexemByWord parser = ParseLexemByWord.getInstance();
        List<String> words = parser.parse(text);

        for (String word : words) {
            Composite wordComposite = new CompositeWord();

            composite.add(wordComposite);
            root.handlerRequest(word, wordComposite);
        }
        return composite;
    }
}
