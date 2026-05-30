package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Date;

public class Topic_34_Mix_Wait {

    WebDriver driver;
    WebDriverWait explicitWait;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
    }
    
    @Test
    public void TC_01_Element_Found() {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        //Wait với Explicit
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#email")));

        //Wait với Implicit
        driver.findElement(By.cssSelector("input#email"));
    }

    @Test
    public void TC_02_Element_Not_Found_Only_Implicit() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        //Wait với Implicit
        driver.findElement(By.cssSelector("input#tester"));
    }

    @Test
    public void TC_03_Element_Not_Found_Only_Explicit_By() {
        //Implicit =0 và Explicit = 3
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(3));
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");
        System.out.println("Start = " + getDateTimeNow());

        try {
            //Wait explicit truyền vào By
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#tester")));
        } catch (Exception e) {
            System.out.println("End = " + getDateTimeNow());
            throw new RuntimeException(e);
        }
    }

    @Test
    public void TC_03_Element_Not_Found_Only_Explicit_Element() {
        //Implicit =0 và Explicit = 3
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(3));
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");
        System.out.println("Start = " + getDateTimeNow());

        //Fail luôn do nhận implicit wait tại step này
        WebElement emailTextbox = driver.findElement(By.cssSelector("input#tester"));

        try {
            //Wait với Explicit truyền vào element
            explicitWait.until(ExpectedConditions.visibilityOf(emailTextbox));
        } catch (Exception e) {
            System.out.println("End = " + getDateTimeNow());
            throw new RuntimeException(e);
        }

        //Check visible không nên truyền vào Element vì sẽ nhận implicit wait => Nếu chỉ dùng explicit tất các hảm wait cho element đều phải dùng BY
        //=> Nên set cả 2 loại Implicit và explicit để giảm thiểu lỗi
    }

    @Test
    public void TC_04_Element_Not_Found_Mix_Implicit_Explicit() {

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(1));

        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        System.out.println("Start = " + getDateTimeNow());
        try {
            //Wait với Explicit
            explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input#tester")));
        } catch (Exception e) {
            System.out.println("Start = " + getDateTimeNow());
            throw new RuntimeException(e);
        }
    }

    public String getDateTimeNow() {
        Date date = new Date();
        return date.toString();
    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}