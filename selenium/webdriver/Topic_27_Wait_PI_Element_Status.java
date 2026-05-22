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

public class Topic_27_Wait_PI_Element_Status {

    //Mục đích: chờ tất cả các step hoàn thành để chạy được tuần tự => Test case sẽ ít bị fail hơn, thao tác giôngs với end user nhất có thể

    //Không phải tất cá các step đều cần wait => chỉ cần wait ở những step quan trọng như load trang, load dữ liệu,...

    //Điều kiện của Element:
    // 1. Element hiển thị trên UI và có trong DOM HTML: ELement ở cùng 1 trang sẽ có thời điểm hiển thị hoặc không hiển thị
    // 2. Có trong HTML nhưng không hiển thị trong giao diện
    // 3. Không có trên UI và không có trong HTML DOM

    //Các tiêu chí/trạng thái để set wait cho 1 element sử dụng các điều kiện này
    //Hiển thị: displayed/ visible/ visibility khi nó thỏa mãn điều kiện số 1
    //Không hiển thị: undisplayed /invisible /invisibility khi nó thỏa mãn điều kiện số 2 và 3
    //Xuất hiện (chỉ cần có trong HTML): present/ presence khi nó thỏa mãn 1 trong 2 điều kiện số 1 và số 2
    //Staleness (element không còn trong HTML) : khi nó thỏa mãn điều kiện số 3
    // => Tại thời điểm A element có xuất hiện (presence / visible) và sau đó tại thời điểm B biến mất không còn trong HTML nữa
    // => Thời điểm B check staleness là đúng


    WebDriver driver;
    WebDriverWait driverWait;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
        driverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void TC_01_Visible() {
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");
        driver.findElement(By.cssSelector("button#send2")).click();

        //Email Address error message xuất hiện => Điều kiện 1
        //Wait
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("advice-required-entry-email")));

        //Verify
        Assert.assertTrue(driver.findElement(By.id("advice-required-entry-email")).isDisplayed());
    }

    @Test
    public  void TC_02_Invisible_I() throws InterruptedException {
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");
        driver.findElement(By.cssSelector("button#send2")).click();

        driver.findElement(By.id("email")).sendKeys("tuydtt@gmail.com");
        driver.findElement(By.cssSelector("button#send2")).click();

        //Element không có trên UI nhưng vẫn có trong HTML

        //Wait
        driverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("advice-required-entry-email")));

        //Verify
        Assert.assertFalse(driver.findElement(By.id("advice-required-entry-email")).isDisplayed());
    }

    @Test
    public  void TC_02_Invisible_II() throws InterruptedException {
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");
        driver.findElement(By.cssSelector("button#send2")).click();

        driver.findElement(By.id("email")).sendKeys("tuydtt@gmail.com");
        driver.findElement(By.id("pass")).sendKeys("123456");
        driver.findElement(By.cssSelector("button#send2")).click();

        driver.switchTo().alert().accept();

        //Element không có trên UI và không có trong HTML

        driverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("advice-required-entry-email")));
        Assert.assertFalse(driver.findElement(By.id("advice-required-entry-email")).isDisplayed());
    }

    @Test
    public  void TC_Visible_Facebook() {
        driver.get("https://www.facebook.com/");
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='email']")));
    }

    @Test
    public  void TC_InVisible_Facebook() {
        driver.get("https://www.facebook.com/");
        driver.findElement(By.cssSelector("a[aria-label='Create new account']")).click();

        //Element không có trên UI và không có trên HTML
        driverWait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.xpath("//label[text()='Password']/parent::div/parent::div/following-sibling::div")));
    }

    @Test
    public  void TC_03_Presence() throws InterruptedException {

        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        driver.findElement(By.cssSelector("button#send2")).click();

        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div#advice-required-entry-email")));

        driver.findElement(By.cssSelector("input#email")).sendKeys("test@gmail.com");

        driverWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div#advice-required-entry-email")));
    }

    @Test
    public  void TC_04_Staleness() {
        driver.get("https://live.techpanda.org/index.php/customer/account/login/");

        driver.findElement(By.cssSelector("button#send2")).click();

        WebElement valitdateEmail = driver.findElement(By.cssSelector("div#advice-required-entry-email"));

        driver.findElement(By.xpath("//a[text()='Mobile']")).click();

        driverWait.until(ExpectedConditions.stalenessOf(valitdateEmail));
    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}