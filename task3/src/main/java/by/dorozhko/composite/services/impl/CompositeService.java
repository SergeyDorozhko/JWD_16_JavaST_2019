package by.dorozhko.composite.services.impl;

import by.dorozhko.composite.dal.CompositeDAL;
import by.dorozhko.composite.dal.FactoryDAL;
import by.dorozhko.composite.dal.exception.ExceptionDAL;
import by.dorozhko.composite.entity.Component;
import by.dorozhko.composite.entity.Composite;
import by.dorozhko.composite.entity.CompositeText;
import by.dorozhko.composite.repository.Repository;
import by.dorozhko.composite.repository.RepositoryFactory;
import by.dorozhko.composite.repository.exception.ExceptionRepository;
import by.dorozhko.composite.services.Service;
import by.dorozhko.composite.services.extract_symbols_chain_of_resp_correct.Sentence;
import by.dorozhko.composite.services.extract_symbols_chain_of_resp_correct.Lexem;
import by.dorozhko.composite.services.extract_symbols_chain_of_resp_correct.Text;
import by.dorozhko.composite.services.extract_symbols_chain_of_resp_correct.Word;
import by.dorozhko.composite.services.extract_symbols_chain_of_resp_correct.Paragraph;

import by.dorozhko.composite.services.impl.comporator.LexemBySymbolThenAlfabetComporator;
import by.dorozhko.composite.services.impl.comporator.TextPartComporator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class CompositeService implements Service {
    /**
     * Logger.
     */
    private final Logger logger = LogManager.getLogger(getClass().getName());

    /**
     * Creating composition from data.
     *
     * @param pathToData path to data.
     * @return
     */
    @Override
    public String createCompositeFromData(final String pathToData) {
        logger.debug("Start create composite of text method");
        FactoryDAL factoryDAL = FactoryDAL.getInstance();
        CompositeDAL dal = factoryDAL.getCompositeDAL();
        String textFromData;
        try {

            logger.debug(pathToData);
            textFromData = dal.read(pathToData);
        } catch (ExceptionDAL ex) {
            logger.error(ex);
            return "Some problem with data";
        }

        logger.debug("Text successfully read from data");


        Composite compositeText = createCompositeFromText(textFromData);


        RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
        Repository repository = repositoryFactory.getRepository();
        repository.setText(compositeText);

        logger.debug("Composition send to storage.");


        return "Successfully created.";
    }


    private Composite createCompositeFromText(final String text) {
        logger.debug("method createCompositeFromText is started.");
        Word wordToSymbol = new Word();
        Lexem lexemToWord = new Lexem(wordToSymbol);
        Sentence sentenceToLexem = new Sentence(lexemToWord);
        Paragraph paragraphToSentence = new Paragraph(sentenceToLexem);
        Text textToParagraph = new Text(paragraphToSentence);


        logger.debug("Chain of responsibility created. Start to parse text and create composition.");
        Composite compositeText = new CompositeText();

        compositeText = textToParagraph.handlerRequest(text, compositeText);


        logger.debug("Text successfully parsed and composition is ready.");

        return compositeText;
    }

    /**
     * take text from repository.
     *
     * @return text.
     */
    @Override
    public String viewTextFromRepository() {
        RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
        return repositoryFactory.getRepository().getText();
    }

    /**
     * Save text to data.
     *
     * @param pathToData
     * @return
     */
    @Override
    public String saveTextToData(final String pathToData) {
        logger.debug("Start save to data method");

        FactoryDAL factoryDAL = FactoryDAL.getInstance();
        CompositeDAL compositeDAL = factoryDAL.getCompositeDAL();
        try {
            compositeDAL.write(viewTextFromRepository(), pathToData);
        } catch (ExceptionDAL ex) {
            logger.error(ex);
            return "Some problem with file, data didn't save.";
        }
        return "Data successfully saved.";
    }

    @Override
    public String viewSortedText(final String sortBy) {
        logger.debug("Start view sorted text method");

        String[] sortParam = sortBy.split(" ");
        String result;
        switch (sortParam[0]) {
            case "Text":
                try {
                    result = sortParagraphBySentencesNumber().getTextPart();
                } catch (ExceptionRepository ex) {
                    logger.error(ex);
                    return "Check storage, text don't found";
                }
                break;
            case "WordsIsSentence":
                result = sortWordsInSentece().getTextPart();
                break;
            case "LexemsBySymbolsOfAlfabet":
                result = sortLexemInText(sortParam[1]).getTextPart();
                break;
            default:
                return "No such compare method.";
        }

        return result;
    }

    private Composite sortParagraphBySentencesNumber() throws ExceptionRepository {
        logger.debug("Start sort paragraph by sentences num...");

        RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
        Repository repository = repositoryFactory.getRepository();

        List<Component> list = new ArrayList<>(repository.getCompositeText());
        Collections.sort(list, new TextPartComporator());
        Composite sortedText = new CompositeText();

        for (Component component : list) {
            sortedText.add(component);
        }

        return sortedText;

    }

    private Composite sortWordsInSentece() {
        logger.debug("Start sort words in sentence.");

        Word wordToSymbol = new Word();
        Lexem sentenceToWords = new Lexem(wordToSymbol);
        Paragraph paragraphToSentence = new Paragraph(sentenceToWords);
        Text textToParagraph = new Text(paragraphToSentence);


        RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
        Repository repository = repositoryFactory.getRepository();

        Composite compositeText = new CompositeText();

        compositeText = textToParagraph.handlerRequest(repository.getText(), compositeText);


        for (Component paragraph : compositeText.getComponents()) {
            for (int i = 0; i < paragraph.getNumberOfChilds(); i++) {
                Composite sentence = (Composite) paragraph.getChild(i);
                Collections.sort(sentence.getComponents(), new TextPartComporator());
            }
        }


        return compositeText;

    }

    private Composite sortLexemInText(final String sortBySymbol) {
        logger.debug("Start sort lexems in text.");

        Word lexemToSymbol = new Word();
        Sentence textToLexem = new Sentence(lexemToSymbol);


        RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
        Repository repository = repositoryFactory.getRepository();

        Composite compositeText = new CompositeText();

        compositeText = textToLexem.handlerRequest(repository.getText(), compositeText);


        Collections.sort(compositeText.getComponents(), new LexemBySymbolThenAlfabetComporator(sortBySymbol));


        return compositeText;

    }

    @Override
    public String saveSortedText(final String sortBy) {
        String[] sortByThenPath = sortBy.split("[|]");

        logger.debug("Start save sorted text to data method");

        FactoryDAL factoryDAL = FactoryDAL.getInstance();
        CompositeDAL compositeDAL = factoryDAL.getCompositeDAL();
        try {
            compositeDAL.write(viewSortedText(sortByThenPath[0].trim()), sortByThenPath[1].trim());
        } catch (ExceptionDAL ex) {
            logger.error(ex);
            return "Some problem with file, data didn't save.";
        }
        return "Data successfully saved.";
    }


}
