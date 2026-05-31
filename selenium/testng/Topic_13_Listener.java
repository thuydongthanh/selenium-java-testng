package testng;

import listener.ScreenshotListener;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Random;

@Listeners(ScreenshotListener.class) //Áp dung listener cho riêng class này

public class Topic_13_Listener {
    public WebDriver getDriver() {
        return driver;
    }

    WebDriver driver;
    String firstName, lastName, emailAddress, password, fulllName;
    Random rand = new Random();

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
        firstName = "Thanh";
        lastName = "Thuy";
        fulllName = firstName + " " + lastName;
        password = "123456789";
    }

    @Test()
    public void TC_01_TechPanda() throws InterruptedException {
        driver.get("http://live.techpanda.org/");
        emailAddress = "Thanhthuy" + rand.nextInt(99999) + "@gmail.com";

        driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        driver.findElement(By.cssSelector("input#firstname")).sendKeys(firstName);
        driver.findElement(By.cssSelector("input#lastname")).sendKeys(lastName);
        driver.findElement(By.cssSelector("input#email_address")).sendKeys(emailAddress);
        driver.findElement(By.cssSelector("input#password")).sendKeys(password);
        driver.findElement(By.cssSelector("input#confirmation")).sendKeys(password);

        //Register
        driver.findElement(By.cssSelector("button[title='Register']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), "Thank you");
        String informationText = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();

        //verify tương đối
        Assert.assertTrue(informationText.contains(fulllName) && informationText.contains(emailAddress));

        //Check thông tin trong edit
        driver.findElement(By.xpath("//h3[text()='Contact Information']/following-sibling::a")).click();
        Assert.assertEquals(driver.findElement(By.id("firstname")).getAttribute("value"), firstName);
        Assert.assertEquals(driver.findElement(By.id("email")).getAttribute("value"), emailAddress);
        Assert.assertEquals(driver.findElement(By.id("firstname")).getAttribute("value"), firstName);

        //Logout
        driver.findElement(By.cssSelector(".account-cart-wrapper>a")).click();
        driver.findElement(By.cssSelector("#header-account .last")).click();


        System.out.println("Email Address: " + emailAddress);
        System.out.println("Password: " + password);

    }
}
