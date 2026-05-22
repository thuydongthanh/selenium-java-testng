package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_29_Implicit_Wait {

    //Implicit Wait: Wait ngầm định - ko rõ ràng cho trạng thái nào của element
    //Time out mặc định cho 2 hàm: findelement và findlements
    //Nếu không set time mặc định là 0
    //Nếu set nhiều lần phạm vi tính từ step được set trở xuống

    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
        driver.get("https://automationfc.github.io/dynamic-loading/");
    }
    
    @Test
    public void TC_01_Dont_Set() {
        driver.findElement(By.cssSelector("div#start>button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
    }

    @Test
    public void TC_02_Less_Than() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.findElement(By.cssSelector("div#start>button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
    }
    @Test
    public  void TC_03_Equal() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.findElement(By.cssSelector("div#start>button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
    }

    @Test
    public  void TC_04_Greater_Than() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.cssSelector("div#start>button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
    }


    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}