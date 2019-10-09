package by.dorozhko.composite.services.extract_symbols_chain_of_resp;

import by.dorozhko.composite.services.parser.ParseParagraphBySentence;

import java.util.List;

public class Paragraph implements Handler {
    private String paragraph;
    private Sentence parent;

    public Paragraph (Sentence parent){
        this.parent = parent;
    }

    @Override
    public void setText(String text) {
        paragraph = text;
    }

    @Override
    public List handlerRequest() {
        ParseParagraphBySentence parser = ParseParagraphBySentence.getInstance();
        return parser.parse(paragraph);
    }
}
