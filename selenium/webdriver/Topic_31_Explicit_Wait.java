package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_31_Explicit_Wait {

    //Cách khai báo
        //driver, Duration timeout => măc định 0,5s tìm lại
        //driver, Duration timeout, Duration sleep (polling time)

    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(300)); //custom polling time
    }

    @Test
    public void TC_01_() {
        //Wait cho element không hiển thị không còn trong HTML (trươc đó là có tồn tại)
        explicitWait.until(ExpectedConditions.stalenessOf(driver.findElement(By.cssSelector(""))));

        //Wait cho element ko hiển thị (còn/ko còn trong HTML)
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("")));

        //Wait cho element được hiển thị (có trên UI hoặc có trong HTML)
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("")));

        //Wait cho element được phép click(button/link/radi/checkbox...)
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("")));

        //Wait cho URL của page tuyệt đối
        explicitWait.until(ExpectedConditions.urlToBe("https://automationfc.github.io/dynamic-loading/"));

        //Wait cho URL của page tương  đối
        explicitWait.until(ExpectedConditions.urlContains("dynamic-loading"));

        //Wait cho URL page thỏa mãn biểu thức Regex
        explicitWait.until(ExpectedConditions.urlMatches("*$^...."));

        //Wait cho hàm js trả về kiểu dữ liệu String
        explicitWait.until(ExpectedConditions.jsReturnsValue("return argument[0].validationMessage"));

        //Wait cho Alert có xuâất hiện trong HTML
        explicitWait.until(ExpectedConditions.alertIsPresent());

        //Wait cho title cảu page tuyệt đối
        explicitWait.until(ExpectedConditions.titleIs("Title"));



    }
    @Test
    public  void TC_02_() {

    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}