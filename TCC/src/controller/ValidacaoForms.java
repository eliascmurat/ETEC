package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidacaoForms {

    public static boolean verifyInfos(String text) {
        Pattern p = Pattern.compile("[^A-Za-z0-9*.,º]()- ");
        Matcher m = p.matcher(text);

        boolean bText = m.find();

        return (bText == true);
    }

}
