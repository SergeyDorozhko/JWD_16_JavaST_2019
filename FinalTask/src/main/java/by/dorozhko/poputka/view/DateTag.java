package by.dorozhko.poputka.view;

import javax.servlet.jsp.tagext.TagSupport;
import java.time.LocalDate;

public class DateTag extends TagSupport {

    public static String nowMinusYears(String years) {
        LocalDate date = LocalDate.now();

        int yearsInt;
        try {
            yearsInt = Integer.parseInt(years);
        } catch (NumberFormatException ex) {
            return date.toString();
        }

        return date.minusYears(yearsInt).toString();
    }

    public static String nowPlusYears(String years) {
        LocalDate date = LocalDate.now();

        int yearsInt;
        try {
            yearsInt = Integer.parseInt(years);
        } catch (NumberFormatException ex) {
            return date.toString();
        }

        return date.plusYears(yearsInt).toString();
    }

}
