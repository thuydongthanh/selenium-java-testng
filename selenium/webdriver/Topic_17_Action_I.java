package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_17_Action_I {

    WebDriver driver;
    Actions actions;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
        //Áp dụng impliciWait cho việc tìm element. Set 15s sẽ tìm liên tục 15s sau đó mới báo fail
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        actions = new Actions(driver);
        driver.manage().window().maximize();
    }

    //Khi chạy test case liên quan đến Actions thì không đươợc dùng chuột/bàn phím cùng lúc, nên chạy 1 máy độc lập
    @Test
    public void TC_01_Hover() throws InterruptedException {
        driver.get("https://automationfc.github.io/jquery-tooltip/");
        WebElement ageTextbox = driver.findElement(By.cssSelector("input#age"));
        actions.moveToElement(ageTextbox).perform();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText(), "We ask for your age only for statistical purposes.");
    }
    @Test
    public  void TC_02_Myntra() throws InterruptedException {
        driver.get("https://www.myntra.com/");
        actions.moveToElement(driver.findElement(By.xpath("//div[@class='desktop-navLink']/a[text()='Kids']"))).perform();
        Thread.sleep(3000);
        //Actions click => ít dùng
        actions.click(driver.findElement(By.xpath("//a[text()='Home & Bath']"))).perform();
        //WebElement Click
        //driver.findElement(By.xpath("//a[@class='desktop-categoryName' and text()='Home & Bath']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("span.breadcrumbs-crumb")).getText(), "Kids Home Bath");
        Assert.assertEquals(driver.findElement(By.cssSelector("h1.title-title")).getText(), "Kids Home Bath");
    }

    @Test
    public void TC_03_Fahasa() throws InterruptedException {
        driver.get("https://www.fahasa.com/");
        actions.moveToElement(driver.findElement(By.cssSelector("div.fhs-header-top-second-bar>div.fhs_center_right"))).perform();
        Thread.sleep(2000);
        actions.moveToElement(driver.findElement(By.cssSelector("a[title='FOREIGN BOOKS']"))).perform();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='fhs_column_stretch']//i//following-sibling::span")).getText(), "FOREIGN BOOKS");
    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}