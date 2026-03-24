package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class Topic_02_Selenium_Locator {

    WebDriver driver;

    String emailAddress = "Selenium Locator";

    @BeforeClass
    public void initialBrowser() {
        //Mở trình duyệt
        driver = new FirefoxDriver();
        //Mở app
        driver.get("https://demo.nopcommerce.com/register");
    }

    @Test
    public void TC_01_ID() throws InterruptedException {
        driver.findElement(By.id("small-searchterms")).sendKeys("Macbook");
        Thread.sleep(3000);

        driver.findElement(By.id("FirstName")).sendKeys("Automation");
        Thread.sleep(3000);
    }

    @Test
    public  void TC_02_Class() throws InterruptedException {
        //Nó không lấy hết toàn bộ giá trị nếu có khoảng trắng, phải lấy class duy nhất
        driver.findElement(By.className("register-next-step-button")).click();
        Thread.sleep(3000);
    }

    @Test
    public  void TC_03_Name() {
        driver.findElement(By.name("Gender"));
    }

    @Test
    public  void TC_04_LinkText() {
        //Chỉ làm việc với element Link và có text: thẻ a có thuộc tính href
        //Phải lấy hết toàn bộ giá trị text
        driver.findElement(By.linkText("Sitemap"));
        driver.findElement(By.linkText("Shipping & returns"));
        driver.findElement(By.linkText("Privacy notice"));
    }

    @Test
    public  void TC_05_Partial_link_Text() {
        //Chỉ làm viêc với element là link
        //Có thể lấy toàn bộ text hoặc 1 phần đầu giữa cuối (hay dùng)
        driver.findElement(By.partialLinkText("Computers"));
        driver.findElement(By.partialLinkText("Digital"));
        driver.findElement(By.partialLinkText("downloads"));
    }

    @Test
    public  void TC_06_Tagname() {
        //Tìm tất cả các element giống nhau: button, checkbox, radio,...
        driver.findElements(By.tagName("button"));
        driver.findElements(By.tagName("input"));
        driver.findElements(By.tagName("label"));
    }

    @Test
    public  void TC_07_Css() {
        driver.findElement(By.cssSelector("input#Company"));
        driver.findElement(By.cssSelector("#Company"));
        driver.findElement(By.cssSelector("input[id='Company'"));

        driver.findElement(By.cssSelector("button.register-next-step-button"));
        driver.findElement(By.cssSelector("button[class='button-1 register-next-step-button'"));

        driver.findElement(By.cssSelector("input[name='Gender'"));
        driver.findElement(By.cssSelector("input[name='NewsLetterSubscriptions[0].IsActive'"));

        driver.findElement(By.cssSelector("a[href='/register?returnUrl=%2Fregister']"));
        driver.findElement(By.cssSelector("a[href='/login?returnUrl=%2Fregister']"));

        driver.findElement(By.cssSelector("a[href*='/login?']"));
        driver.findElement(By.cssSelector("a[href*='/register?']"));

        driver.findElement(By.cssSelector("a"));
        driver.findElement(By.cssSelector("button"));
        driver.findElement(By.cssSelector("input"));
    }

    @Test
    public  void TC_08_XPath() {
        driver.findElement(By.xpath("//input[@id='Company']"));
        driver.findElement(By.xpath("//input[@id='Password']"));

        driver.findElement(By.xpath("//button[@class='button-1 register-next-step-button']"));
        driver.findElement(By.xpath("//button[contains(@class, 'register-next-step-button')]"));

        driver.findElement(By.xpath("//input[@name='Gender']"));

        driver.findElement(By.xpath("//a[text()='Register']"));

        driver.findElement((By.xpath("//a[contains(text(), 'Shipping')]")));
        driver.findElement((By.xpath("//a[contains(text(), '& returns')]")));

        driver.findElement(By.xpath("//a"));
        driver.findElement(By.xpath("//button"));
        driver.findElement(By.xpath("//input"));
    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}