package by.dorozhko.transport.services.validator;

import by.dorozhko.transport.entity.Carriage;
import by.dorozhko.transport.entity.Train;
import by.dorozhko.transport.entity.params.CarriageType;
import by.dorozhko.transport.entity.params.EngineType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Validator {
    /**
     * number of carriage params.
     */
    private static int numberOfCarriageParams = 6;
    /**
     * number of train params.
     */
    private static int numberOfTrainParams = 6;
    /**
     * max available length of integer param.
     */
    private static int maxValueLength = 9;
    /**
     * teamplate for number.
     */
    private static String numberTeamplate = "^\\-?\\d+$";
    /**
     * teamplate for word.
     */
    private static String wordTeamplate = "^\\w+$";


    private Validator() {
    }

    /**
     * Check is line valid.
     *
     * @param line params of entity.
     * @return result of validation.
     */
    public static boolean isValidLine(final String line) {

        String[] checkClass = line.split(":");

        boolean isEnoughParams = checkClass.length > 1;
        if (!isEnoughParams) {
            return false;
        }
        boolean isCarriage = checkClass[0].trim().
                equals(Carriage.class.getSimpleName());
        boolean isTrain = checkClass[0].trim().
                equals(Train.class.getSimpleName());
        boolean result;


        if (isCarriage) {
            result = checkCarriageArguments(checkClass[1]);
        } else if (isTrain) {
            result = checkTrainArguments(checkClass[1]);
        } else {
            return false;
        }

        return result;
    }


    private static boolean checkCarriageArguments(final String arguments) {
        String[] carriageParam = arguments.split(",");

        return carriageParam.length == numberOfCarriageParams
                && checkKeyAndValueIsWord("name", carriageParam[0])
                && checkKeyAndValuePositivNumber("weight", carriageParam[1])
                && checkKeyAndValuePositivNumber("length", carriageParam[2])
                && checkTypeOfCarriage("carriageType", carriageParam[3])
                && checkKeyAndValuePositivNumber("maxValueOfBaggage",
                carriageParam[4])
                && checkKeyAndValuePositivNumber("numberOfPassengers",
                carriageParam[5]);
    }


    private static boolean checkTrainArguments(final String argumensts) {
        String[] trainParam = argumensts.split(",");

        return trainParam.length == numberOfTrainParams
                && checkKeyAndValueIsWord("name", trainParam[0])
                && checkKeyAndValuePositivNumber("weight", trainParam[1])
                && checkKeyAndValuePositivNumber("length", trainParam[2])
                && checkTypeOfEngine("engineType", trainParam[3])
                && checkKeyAndValuePositivNumber("enginePower", trainParam[4])
                && checkKeyAndValuePositivNumber("maxSpeed", trainParam[5]);
    }


    private static boolean checkKeyAndValuePositivNumber(
            final String key, final String keyAndValueLine) {
        String[] keyThenValue = keyAndValueLine.split("=");
        if (!keyThenValue[0].trim().equals(key) || keyThenValue.length == 1) {
            return false;
        }


        if (!isNumber(keyThenValue[1])
                || keyThenValue[1].length() > maxValueLength) {
            return false;
        }

        int value = Integer.parseInt(keyThenValue[1].trim());
        return value > 0;
    }

    /**
     * check is line a number.
     *
     * @param number incoming value.
     * @return result of check.
     */
    public static boolean isNumber(final String number) {
        Pattern pattern = Pattern.compile(numberTeamplate);
        Matcher matcher = pattern.matcher(number.trim());

        return matcher.find();
    }


    private static boolean checkKeyAndValueIsWord(
            final String key, final String keyAndValueLine) {
        String[] keyThenValue = keyAndValueLine.split("=");


        return keyThenValue[0].trim().equals(key)
                && keyThenValue.length != 1
                && isWord(keyThenValue[1]);
    }


    private static boolean isWord(final String word) {
        Pattern pattern = Pattern.compile(wordTeamplate);
        Matcher matcher = pattern.matcher(word.trim());


        return matcher.find();
    }


    private static boolean checkTypeOfCarriage(
            final String key, final String keyAndValueLine) {
        String[] keyThenValue = keyAndValueLine.split("=");
        if (!keyThenValue[0].trim().equals(key) || keyThenValue.length == 1) {
            return false;
        }

        try {
            CarriageType.valueOf(keyThenValue[1].trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }


    private static boolean checkTypeOfEngine(
            final String key, final String keyAndValueLine) {
        String[] keyThenValue = keyAndValueLine.split("=");
        if (!keyThenValue[0].trim().equals(key) || keyThenValue.length == 1) {
            return false;
        }

        try {
            EngineType.valueOf(keyThenValue[1].trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }
}
