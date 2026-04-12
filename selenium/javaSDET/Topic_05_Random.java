package javaSDET;

import org.testng.annotations.Test;

import java.util.Random;

public class Topic_05_Random {
    //global
    String prefixEmail = "thanhthuy";
    String postfixEmail = "@gmail.com"; //web mail server

    @Test
    public void testEmail() {
        Random random = new Random();
        String fullEmail = prefixEmail + random.nextInt(99999) + postfixEmail; // biến local
        System.out.println(fullEmail);
    }
}
