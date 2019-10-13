package by.dorozhko.composite.services.extract_symbols_chain_of_resp_correct;

import by.dorozhko.composite.entity.Composite;
import by.dorozhko.composite.entity.CompositeWord;
import by.dorozhko.composite.services.parser.ParseLexemByWord;

import java.util.List;

public class Lexem extends ChainHandler implements Handler {
    private Word root;

    public Lexem(Word newRoot) {
        this.root = newRoot;
    }



    @Override
    public Composite handlerRequest(String newText, Composite textPart) {
        composite = textPart;
        text = newText;

        ParseLexemByWord parser = ParseLexemByWord.getInstance();
        List<String> words =  parser.parse(text);

        for(String word : words) {
            Composite wordComposite = new CompositeWord();

            composite.add(wordComposite);
            root.handlerRequest(word, wordComposite);
        }
        return composite;
    }
}
