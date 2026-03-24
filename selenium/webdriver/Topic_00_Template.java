package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_00_Template {

    // 1. Setup OS/Browser/Web/Page/Data/Variable/Object..
    WebDriver driver;

    @BeforeClass //chạy 1 lần trước tất cả test case
    public void initialBrowser() {
        driver = new FirefoxDriver();

        driver.get("https://demo.nopcommerce.com/");
    }

    // 2. Action/Execute: Tuong tac element/nhập liệu/verify
    @Test
    public void TC_01_() {

    }
    @Test
    public  void TC_02_() {

    }

    // 3. Clean: Delete data test/account/close browser
    @AfterClass //chạy 1 lần sau tất cả test case
    public void cleanBrowser() {
        driver.quit();
    }

}