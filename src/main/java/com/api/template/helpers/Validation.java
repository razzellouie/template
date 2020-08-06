package com.api.template.helpers;
import java.util.regex.Pattern; 

public class Validation {

    final private String emailRegex = "^(.+)@(.+)$"; 

    public boolean isEmail(String email) throws Exception {
        return Pattern.compile(emailRegex).matcher(email).matches();
    } 
}