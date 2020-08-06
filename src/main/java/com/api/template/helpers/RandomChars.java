package com.api.template.helpers;

import java.nio.charset.Charset;
import java.util.Random;

public class RandomChars {
    
    private int limit;
    
    public RandomChars(int limit) {
        this.limit = limit;
    }
    
    public String getChars() {
        byte[] array = new byte[256];
        new Random().nextBytes(array);
        
        String randomString = new String(array, Charset.forName("UTF-8"));
        StringBuffer r = new StringBuffer();
        String AlphaNumericString = randomString.replaceAll("[^A-Za-z0-9]", "");
        for (int k = 0; k < AlphaNumericString.length(); k++) {
            
            if (Character.isLetter(AlphaNumericString.charAt(k)) && (limit > 0)
                    || Character.isDigit(AlphaNumericString.charAt(k)) && (limit > 0)) {
                
                r.append(AlphaNumericString.charAt(k));
                limit--;
            }
        }
        return r.toString();
    }

}