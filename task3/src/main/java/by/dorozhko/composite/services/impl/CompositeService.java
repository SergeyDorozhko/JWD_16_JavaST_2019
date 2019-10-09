package by.dorozhko.composite.services.impl;

import by.dorozhko.composite.dal.CompositeDAL;
import by.dorozhko.composite.dal.FactoryDAL;
import by.dorozhko.composite.dal.exception.ExceptionDAL;
import by.dorozhko.composite.entity.*;
import by.dorozhko.composite.repository.Repository;
import by.dorozhko.composite.repository.RepositoryFactory;
import by.dorozhko.composite.services.Service;
import by.dorozhko.composite.services.extract_symbols_chain_of_resp.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.locks.ReentrantLock;


public class CompositeService implements Service {
    private Logger logger = LogManager.getLogger(getClass().getName());

    @Override
    public String createCompositeFromData(String pathToData) {
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


        CompositeText compositeText = createCompositeFromText(textFromData);


        RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
        Repository repository = repositoryFactory.getRepository();
        repository.setText(compositeText);

        logger.debug("Composition send to storage.");

        System.out.println(repository.getText());
        return "Successfully created.";
    }


    private CompositeText createCompositeFromText(String text){
        logger.debug("method createCompositeFromText is started.");
        Word wordToSymbol = new Word();
        Lexem lexemToWord = new Lexem(wordToSymbol);
        Sentence sentenceToLexem = new Sentence(lexemToWord);
        Paragraph paragraphToSentence = new Paragraph(sentenceToLexem);
        Text textToParagraph = new Text(paragraphToSentence);

        logger.debug("Chain of responsibility created. Start to parse text and create composition.");
        CompositeText compositeText = new CompositeText();

        int countParagraphBranch = 0;


        textToParagraph.setText(text);

        for (Object paragraphFromText : textToParagraph.handlerRequest()) {
            String paragraph = (String) paragraphFromText;

            compositeText.add(new CompositeParagraph());

            int countSentenceBranch = 0;

            paragraphToSentence.setText(paragraph);
            for (Object sentenceFromParagraph : paragraphToSentence.handlerRequest()) {
                String sentence = (String) sentenceFromParagraph;

                compositeText.getChild(countParagraphBranch).add(new CompositeSentence());

                int countLexemBranch = 0;

                sentenceToLexem.setText(sentence);
                for (Object lexemFromSentence : sentenceToLexem.handlerRequest()) {
                    String lexem = (String) lexemFromSentence;

                    compositeText.getChild(countParagraphBranch).getChild(countSentenceBranch).add(new CompositeLexem());

                    int countWordBranch = 0;

                    lexemToWord.setText(lexem);
                    for (Object wordFromLexem : lexemToWord.handlerRequest()) {
                        String word = (String) wordFromLexem;

                        compositeText.getChild(countParagraphBranch).getChild(countSentenceBranch).getChild(countLexemBranch).add(new CompositeWord());

                        wordToSymbol.setText(word);
                        for (Object symbolFromWord : wordToSymbol.handlerRequest()) {
                            Character symbol = (Character) symbolFromWord;
                            compositeText.getChild(countParagraphBranch).getChild(countSentenceBranch).getChild(countLexemBranch).getChild(countWordBranch).add(new LeafSymbol(symbol));
                        }
                        countWordBranch++;
                    }

                    countLexemBranch++;
                }

                countSentenceBranch++;
            }

            countParagraphBranch++;
        }

        logger.debug("Text successfully parsed and composition is ready.");

        return compositeText;
    }

    @Override
    public String viewTextFromRepository(){
        RepositoryFactory repositoryFactory = RepositoryFactory.getInstance();
        return repositoryFactory.getRepository().getText();
    }

    @Override
    public String saveTextToData(String pathToData) {
        logger.debug("Start save to data method");

        FactoryDAL factoryDAL = FactoryDAL.getInstance();
        CompositeDAL compositeDAL = factoryDAL.getCompositeDAL();
        try {
            compositeDAL.write(viewTextFromRepository(), pathToData);
        }catch (ExceptionDAL ex){
            logger.error(ex);
            return "Some problem with file, data didn't save.";
        }
        return "Data successfully saved.";
    }
}
