package by.dorozhko.composite.services.extract_symbols_chain_of_resp_correct;

import by.dorozhko.composite.entity.Composite;
import by.dorozhko.composite.entity.CompositeSentence;
import by.dorozhko.composite.services.parser.ParseParagraphBySentence;

import java.util.List;

public class Paragraph implements Handler {
    private Handler parent;

    public Paragraph(final Handler parent) {
        this.parent = parent;
    }


    @Override
    public Composite handlerRequest(final String text,
                                    final Composite composite) {


        ParseParagraphBySentence parser =
                ParseParagraphBySentence.getInstance();
        List<String> sentences = parser.parse(text);

        for (String sentence : sentences) {

            Composite sentenceComposite = new CompositeSentence();

            composite.add(sentenceComposite);
            parent.handlerRequest(sentence, sentenceComposite);
        }
        return composite;
    }
}