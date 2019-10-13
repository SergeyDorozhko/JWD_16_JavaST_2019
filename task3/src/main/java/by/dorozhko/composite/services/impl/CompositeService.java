package by.dorozhko.composite.services.impl;

import by.dorozhko.composite.dal.CompositeDAL;
import by.dorozhko.composite.dal.FactoryDAL;
import by.dorozhko.composite.dal.exception.ExceptionDAL;
import by.dorozhko.composite.entity.Composite;
import by.dorozhko.composite.entity.CompositeText;
import by.dorozhko.composite.repository.Repository;
import by.dorozhko.composite.repository.RepositoryFactory;
import by.dorozhko.composite.services.Service;
import by.dorozhko.composite.services.extract_symbols_chain_of_resp_correct.Lexem;
import by.dorozhko.composite.services.extract_symbols_chain_of_resp_correct.Paragraph;
import by.dorozhko.composite.services.extract_symbols_chain_of_resp_correct.Sentence;
import by.dorozhko.composite.services.extract_symbols_chain_of_resp_correct.Word;
import by.dorozhko.composite.services.extract_symbols_chain_of_resp_correct.Text;




import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class CompositeService implements Service {
    private Logger logger = LogManager.getLogger(getClass().getName());

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
    @Override
    public String viewTextFromRepository() {
        RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
        return repositoryFactory.getRepository().getText();
    }

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

        RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
        Repository repository = repositoryFactory.getRepository();

        return repository.getSortedText(sortBy);
    }
}
