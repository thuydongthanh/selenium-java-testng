package javaSDET;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_04_And_Or {
    @Test
    public  void TC_Verify_And () {
        String contactInfo = "thanh thanh\n" +
                "thanh@gmail.com\n" +
                "Change Password";
        Assert.assertTrue(contactInfo.contains("thanh thanh") && contactInfo.contains("thanh@gmail.com"));
    }
}
