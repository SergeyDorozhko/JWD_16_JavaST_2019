package by.dorozhko.composite.services.extract_symbols_chain_of_resp_correct;

import by.dorozhko.composite.entity.Composite;
import by.dorozhko.composite.entity.CompositeSentence;
import by.dorozhko.composite.services.parser.ParseParagraphBySentence;

import java.util.List;

public class Paragraph implements Handler {
    private String paragraph;
    private Sentence parent;
    private Composite composite;

    public Paragraph(Sentence parent) {
        this.parent = parent;
    }

    @Override
    public void setText(String text) {
        paragraph = text;
    }

    @Override
    public Composite handlerRequest(Composite textPart) {
        composite = textPart;
        ParseParagraphBySentence parser = ParseParagraphBySentence.getInstance();
        List<String> sentences = parser.parse(paragraph);

        for (String sentence : sentences) {

            Composite sentenceComposite = new CompositeSentence();

            parent.setText(sentence);
            composite.add(sentenceComposite);
            parent.handlerRequest(sentenceComposite);
        }
        return composite;
    }
}
