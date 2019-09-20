package by.dorozhko.transport.services.validator;

import by.dorozhko.transport.entity.Carriage;
import by.dorozhko.transport.entity.Train;
import by.dorozhko.transport.entity.params.CarriageType;
import by.dorozhko.transport.entity.params.EngineType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Validator {

    private static int maxValueLength = 10;
    private static String numberTeamplate = "^\\d+$";
    private static String wordTeamplate = "^\\w+$";


    private Validator() {
    }

    public static boolean isValidLine(final String line) {

        String[] cheackClass = line.split(":");

        boolean isCarriage = cheackClass[0].trim().equals(Carriage.class.getSimpleName());
        boolean isTrain = cheackClass[0].trim().equals(Train.class.getSimpleName());

        boolean result;


        if (isCarriage) {
            result = checkCarriageArguments(cheackClass[1]);
        } else if (isTrain) {
            result = checkTrainArguments(cheackClass[1]);
        } else {
            return false;
        }

        return result;
    }


    private static boolean checkCarriageArguments(final String arguments) {
        String[] carriageParam = arguments.split(",");
        if (carriageParam.length != 6) {
            return false;
        }
        if (!checkKeyAndValueIsWord("name", carriageParam[0])) {
            return false;
        }

        if (!checkKeyAndValuePositivNumber("weight", carriageParam[1])) {
            return false;
        }

        if (!checkKeyAndValuePositivNumber("length", carriageParam[2])) {
            return false;
        }

        if (!checkTypeOfCarriage("carriageType", carriageParam[3])) {
            return false;
        }

        if (!checkKeyAndValuePositivNumber("maxValueOfBaggage", carriageParam[4])) {
            return false;
        }

        if (!checkKeyAndValuePositivNumber("numberOfPassengers", carriageParam[5])) {
            return false;
        }

        return true;

    }


    private static boolean checkTrainArguments(String argumensts) {
        String[] trainParam = argumensts.split(",");
        if (trainParam.length != 6) {
            return false;
        }
        if (!checkKeyAndValueIsWord("name", trainParam[0])) {
            return false;
        }

        if (!checkKeyAndValuePositivNumber("weight", trainParam[1])) {
            return false;
        }

        if (!checkKeyAndValuePositivNumber("length", trainParam[2])) {
            return false;
        }

        if (!checkTypeOfEngine("engineType", trainParam[3])) {
            return false;
        }

        if (!checkKeyAndValuePositivNumber("enginePower", trainParam[4])) {
            return false;
        }

        if (!checkKeyAndValuePositivNumber("maxSpeed", trainParam[5])) {
            return false;
        }

        return true;
    }


    private static boolean checkKeyAndValuePositivNumber(String key, String keyAndValueLine) {
        String[] keyThenValue = keyAndValueLine.split("=");
        if (!keyThenValue[0].trim().equals(key) || keyThenValue.length == 1) {
            return false;
        }


        if (!isNumber(keyThenValue[1]) || keyThenValue[1].length() > maxValueLength) {
            return false;
        }

        int value = Integer.valueOf(keyThenValue[1].trim());
        if (value <= 0) {
            return false;
        }
        return true;
    }


    public static boolean isNumber(String number) {
        Pattern pattern = Pattern.compile(numberTeamplate);
        Matcher matcher = pattern.matcher(number.trim());

        if (matcher.find()) {
            return true;
        }

        return false;
    }


    private static boolean checkKeyAndValueIsWord(String key, String keyAndValueLine) {
        String[] keyThenValue = keyAndValueLine.split("=");
        if (!keyThenValue[0].trim().equals(key) || keyThenValue.length == 1) {
            return false;
        }

        if (!isWord(keyThenValue[1])) {
            return false;
        }

        return true;
    }


    private static boolean isWord(String word) {
        Pattern pattern = Pattern.compile(wordTeamplate);
        Matcher matcher = pattern.matcher(word.trim());

        if (matcher.find()) {
            return true;
        }

        return false;
    }


    private static boolean checkTypeOfCarriage(String key, String keyAndValueLine) {
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


    private static boolean checkTypeOfEngine(String key, String keyAndValueLine) {
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
