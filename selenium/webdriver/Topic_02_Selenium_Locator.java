package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
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

        // 1 - Duy nhất
        // 2 - Ưu tiên nếu có id/class/name
        // 3 - Không có id/class/name: dùng bất kì attribute khác
        // 4 - Giá trị của attribute phải có ý nghĩa liên quan đến element đó và ko bị thay đổi: title

        // => Tối ưu nhất để dùng

        //5 - Link không nên dùng attribute href vì dễ bị thay đổi

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

    @Test
    public void TC_09_Relative_Locator() {
        //1 - Dùng khi ko thể định danh element của chính nó (dựa vào vị trí bên cạnh / gần đó)
        //2- Sử dụng để test GUI (giao diện khớp với design)
        driver.get("https://demo.nopcommerce.com/login");
        //Element A
        By passwordInputBy = By.cssSelector("#Password");
        WebElement passwordInput = driver.findElement(By.cssSelector("#Password"));

        //Element B
        By rememberMeCheckboxBy = By.id("RememberMe");

        //Element C
        By forgotPasswordTextboxBy = By.className("forgot-password");

        //Element D
        By buttonLoginBy = By.cssSelector("button.login-button");

        //Element E

        WebElement rememberMeTextbox = driver.findElement(RelativeLocator.with(By.tagName("label"))
                .above(buttonLoginBy) // Label nằm phía trên button Login
                .below(passwordInputBy) // label nằm dưới input password
                .toLeftOf(forgotPasswordTextboxBy) //label nằm trái textbox forgotpassword
                .toRightOf(rememberMeCheckboxBy) // label nằm bên phải checkbox
                .near(rememberMeCheckboxBy).near(buttonLoginBy)
        );
    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}