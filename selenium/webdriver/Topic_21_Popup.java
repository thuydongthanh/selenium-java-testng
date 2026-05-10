package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_21_Popup {

    //inDom: Dạng popup luôn có hiển thị trong HTML
    //Not InDom: Dạng popup chỉ có HTML khi mở popup, đóng popup sẽ không hiển thị HTML

    //Popup cố định: hiển thị hoặc không hiển thị theo yêu cầu/bussiness => mở hoặc đóng theo yêu cầu của mình
    //Popup random: TH1: luôn hiển thị -> có thể đóng và action tiếp, TH2: Ko hiển thị luôn

    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_Popup_English_24h() throws InterruptedException {
        driver.get("https://ngoaingu24h.vn/");
        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
        By loginPopup = By.cssSelector("div#custom-dialog>div.MuiDialog-container>div.MuiPaper-root");
        //Kiểm tra 1 element hiển thị có trong HTML => Hiển thị trên UI -> True, ko hiển thị trên giao diện -> false
        Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());

        driver.findElement(By.cssSelector("input[autocomplete='username']")).sendKeys("thuydtt");
        driver.findElement(By.cssSelector("input[autocomplete='new-password']")).sendKeys("123456");

        driver.findElement(By.xpath("//div[@id='custom-dialog']//button[@type='submit']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#notistack-snackbar")).getText(), "Bạn đã nhập sai tài khoản hoặc mật khẩu!");

        driver.findElement(By.cssSelector("div#custom-dialog button.close-btn")).click();
        Thread.sleep(3000);
    }

    @Test
    public  void TC_02_Popup_Kyna() throws InterruptedException {
        driver.get("https://skills.kynaenglish.vn/dang-nhap");

        By loginPoup = By.cssSelector("div#k-popup-account-login-mb div.modal-content");
        Assert.assertTrue(driver.findElement(loginPoup).isDisplayed());

        driver.findElement(By.cssSelector("input#user-login")).sendKeys("automation@gmail.com");
        driver.findElement(By.cssSelector("input#user-password")).sendKeys("123456");
        driver.findElement(By.cssSelector("button#btn-submit-login")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(), "Sai tên đăng nhập hoặc mật khẩu");
    }

    @Test
    public void TC_03_Tiki() throws InterruptedException {
        driver.get("https://tiki.vn/");
        //Popup 1 - Markerting
        //Hiển thị cố định khi vừa mở site ra
        //Khi đóng lại thì không còn trong HTML nữa
        //Khi refresh page thì lại hiện ra
        By markertingPopup = By.cssSelector("div#VIP_BUNDLE");

        //Kiểm tả hiển thị => Hiển thị cố định khi vừa mở site ra
        Assert.assertTrue(driver.findElement(markertingPopup).isDisplayed());

        driver.findElement(By.cssSelector("div#VIP_BUNDLE img[alt='close-icon']")).click();

        //Kiểm tra không hiển thị => Khi đóng lại thì không còn HTML nữa
        //Vì elemenet ko có trong HTML nên hàm findElement sẽ không tìm thấy -> đánh fail ngay step này
        Assert.assertEquals(driver.findElements(markertingPopup).size(), 0);

        Thread.sleep(2000);

        driver.findElement(By.cssSelector("div[data-view-id='header_header_account_container']")).click();

        By loginPopup = By.cssSelector("div.ReactModal__Content");
        Assert.assertTrue(driver.findElement(loginPopup).isDisplayed());

        driver.findElement(By.cssSelector("p.login-with-email")).click();
        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//span[@class='error-mess'][1]")).getText(), "Email không được để trống");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@class='error-mess'][2]")).getText(), "Mật khẩu không được để trống");

        driver.findElement(By.cssSelector("img.close-img")).click();

        Assert.assertEquals(driver.findElements(loginPopup).size(), 0);
    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}