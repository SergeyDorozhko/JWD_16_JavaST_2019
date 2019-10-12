package by.dorozhko.string_learn;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main {
    public static void main(String[] args) {
        System.out.println("dsf  dsd" );

        System.out.println("1 — английский \n 2 — белорусский \n 3 — русский ");

        char i = 0;
        try {
            i = (char) System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(i);
        String country = "";
        String language = "";
        switch (i) {
            case '1':
                country = "UK";
                language = "en";
                break;
            case '2':
                country = "BY";
                language = "be";
                break;
            case '3':
                country = "RU";
                language = "ru";
                break;
        }

        Locale current = new Locale(language, country);

        ResourceBundle rb = ResourceBundle.getBundle("text", current);
        String s1 = rb.getString("str1");
        System.out.println("s1 = " + s1);
//        try {
//            String coorectStr = new String(s1.getBytes("ISO-8859-1"));
//            System.out.println("coorectStr = " + coorectStr);
//        } catch (UnsupportedEncodingException newE) {
//            newE.printStackTrace();
//        }
//        try {
//            String s = new String(rb.getString("str1").getBytes("ISO-8859-1"));
//        } catch (UnsupportedEncodingException newE) {
//            newE.printStackTrace();
//        }
        //String s2 = rb.getString("str2");
        //System.out.println(s2);
    }
}
