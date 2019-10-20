package by.dorozhko.composite.services.extract_symbols_chain_of_resp_correct;

import by.dorozhko.composite.entity.Composite;
import by.dorozhko.composite.entity.CompositeSentence;
import by.dorozhko.composite.services.parser.ParseParagraphBySentence;

import java.util.List;

public class Paragraph implements Handler {
    /**
     * next take action.
     */
    private Handler parent;

    /**
     * constructor with take next handler as param.
     * @param nextParent next handler.
     */
    public Paragraph(final Handler nextParent) {
        this.parent = nextParent;
    }

    /**
     * make action and then give to next.
     * @param text      incoming text.
     * @param composite tree of text parts developing
     *                  from text branch by branch.
     * @return
     */
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
