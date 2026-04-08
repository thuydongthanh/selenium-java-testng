package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_Run_On_Browser {

    WebDriver driver;

    @Test
    public void TC_01_() {
        driver = new FirefoxDriver();

        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
        driver.quit();
    }
    @Test
    public  void TC_02_() {

    }

}