package by.dorozhko.composite.services.extract_symbols_chain_of_resp_correct;

import by.dorozhko.composite.entity.Composite;
import by.dorozhko.composite.entity.CompositeWord;
import by.dorozhko.composite.services.parser.ParseLexemByWord;

import java.util.List;

public class Lexem implements Handler {
    private String lexem;
    private Word root;
    private Composite composite;

    public Lexem(Word newRoot) {
        this.root = newRoot;
    }

    @Override
    public void setText(String text) {
        lexem = text;
    }

    @Override
    public Composite handlerRequest(Composite textPart) {
        composite = textPart;

        ParseLexemByWord parser = ParseLexemByWord.getInstance();
        List<String> words =  parser.parse(lexem);

        for(String word : words) {
            Composite wordComposite = new CompositeWord();

            composite.add(wordComposite);
            root.setText(word);
            root.handlerRequest(wordComposite);
        }
        return composite;
    }
}
