package by.dorozhko.poputka.view;

import javax.servlet.jsp.tagext.TagSupport;

public class EqualsTag extends TagSupport {

    public static Boolean equalsValue(String userValue, Integer mapValue) {
        boolean result = false;
        if (userValue != null && userValue.length() > 0) {

            if (userValue.equals(mapValue.toString())) {
                result = true;
            }
        }

        return result;
    }

}