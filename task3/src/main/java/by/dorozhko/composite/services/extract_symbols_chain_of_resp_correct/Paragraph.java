package by.dorozhko.composite.services.extract_symbols_chain_of_resp_correct;

import by.dorozhko.composite.entity.Composite;
import by.dorozhko.composite.entity.CompositeSentence;
import by.dorozhko.composite.services.parser.ParseParagraphBySentence;

import java.util.List;

public class Paragraph extends ChainHandler implements Handler {
    private Sentence parent;

    public Paragraph(Sentence parent) {
        this.parent = parent;
    }


    @Override
    public Composite handlerRequest(String newText, Composite textPart) {
        composite = textPart;
        text = newText;

        ParseParagraphBySentence parser = ParseParagraphBySentence.getInstance();
        List<String> sentences = parser.parse(text);

        for (String sentence : sentences) {

            Composite sentenceComposite = new CompositeSentence();

            composite.add(sentenceComposite);
            parent.handlerRequest(sentence, sentenceComposite);
        }
        return composite;
    }
}
