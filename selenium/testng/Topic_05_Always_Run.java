package testng;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class Topic_05_Always_Run {

    //Apply for Before<> và After<> ko nên đưa vào testcase
    //Chạy khi nếu trường hợp Before<> bị fail thì các testcase mặc định sẽ bị skip và After sẽ không chạy

    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.get("https://www.fahasa.com/");
        //Fail ở step này
        Assert.assertTrue(false);
    }


    @Test
    public void TC_01() {
        System.out.println("Run TC 01");
    }

    @Test
    public void TC_02() {
        System.out.println("Run TC 02");
    }

    @Test
    public void TC_03() {
        System.out.println("Run TC 03");
    }


    @AfterClass(alwaysRun = true)
    public void afterClass() {
        driver.quit();
    }
}
