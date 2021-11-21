package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validacao{
    public static boolean getSpecialCharacter(String name, String password) {
        if ((name.isEmpty() || name.trim().isEmpty()) || (password.isEmpty() || password.trim().isEmpty())){
            return false;
        }else{
            Pattern p = Pattern.compile("[^A-Za-z0-9*]");
            Matcher mName = p.matcher(name);
            Matcher mPassword = p.matcher(name);

            boolean bName = mName.find();
            boolean bPassword = mPassword.find();

            return !(bName == true || bPassword);
        }
    }
}