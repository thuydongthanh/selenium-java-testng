package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_WebElement_Excercise_II {
    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
    }

    @Test
    public void Login_01_Empty_Data() throws InterruptedException {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
        driver.findElement(By.cssSelector("button#send2")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-email")).getText(), "This is a required field.");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-pass")).getText(), "This is a required field.");
    }
    @Test
    public void Login_02_Invaild_Email() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
        driver.findElement(By.id("email")).sendKeys("123122344@134243.1233");
        driver.findElement(By.id("pass")).sendKeys("123456");
        driver.findElement(By.cssSelector("button#send2")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
    }
    @Test
    public void Login_03_Invalid_Password() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
        driver.findElement(By.id("email")).sendKeys("thanhthuy@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("123");
        driver.findElement(By.cssSelector("button#send2")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
    }
    @Test
    public void Login_04_Incorrect_Email_Password() {
        driver.get("http://live.techpanda.org/index.php/customer/account/login/");
        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
        driver.findElement(By.id("email")).sendKeys("thanhthuy@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("123123123");
        driver.findElement(By.cssSelector("button#send2")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(), "Invalid login or password.");
    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}