package webdriver;

import com.beust.ah.A;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.xml.transform.Source;
import java.time.Duration;
import java.util.Set;

public class Topic_23_Window_Tab {

    WebDriver driver;
    Select select;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_Github() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");

        //getWindowHandle(): lấy ra ID của tab/window mà driver đang active
        String githubWindowID = driver.getWindowHandle();
        System.out.println("Github Window ID: " + githubWindowID);
        System.out.println("Page Title: " + driver.getTitle());
        System.out.println("Page URL: " + driver.getCurrentUrl());

        //Click vào Google Link -> nó sẽ bật lên một tab mới và tự nhảy qua
        //Nhưng về code selenium là driver không tự nhảy qua -> nó vẫn ở tab cũ
        driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
        Thread.sleep(3000);

        //Switch qua tab google
        switchToWindowById(githubWindowID); //truyền ID của page hiện tại
        driver.findElement(By.cssSelector("textarea[name='q']")).sendKeys("Selenium Webdriver");

        String googleWindowID = driver.getWindowHandle();

        //Switch qua tab github
        switchToWindowById(googleWindowID);
        driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
        Thread.sleep(2000);

        switchToWindowByTitle("Facebook");
        System.out.println(driver.getTitle());

        //Quay về github
        switchToWindowByTitle("Selenium WebDriver");
        driver.findElement(By.xpath("//a[text()='TIKI']")).click();

        //Switch qua tiki
        switchToWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");

        closeALlWindowWithoutParent(githubWindowID);

        driver.getTitle();
    }

    @Test
    public  void TC_02_LivePanda() throws InterruptedException {
        driver.get("http://live.techpanda.org/");

        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/parent::div//div[@class='actions']//a[text()='Add to Compare']")).click();
        By messageBy = By.cssSelector("li.success-msg span");
        Assert.assertEquals(driver.findElement(messageBy).getText(),
                "The product Sony Xperia has been added to comparison list.");

        driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/parent::div//div[@class='actions']//a[text()='Add to Compare']")).click();
        Assert.assertEquals(driver.findElement(messageBy).getText(),
                "The product Samsung Galaxy has been added to comparison list.");

        driver.findElement(By.cssSelector("button[title='Compare']")).click();

        String currentId = driver.getWindowHandle();

        switchToWindowById(currentId);

        Thread.sleep(2000);
        Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");

        driver.findElement(By.cssSelector("button[title='Close Window'")).click();

        switchToWindowByTitle("Mobile");

        driver.findElement(By.xpath("//a[text()='Clear All']")).click();
        Thread.sleep(2000);

        driver.switchTo().alert().accept();

        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(messageBy).getText(), "The comparison list was cleared.");
    }

    @Test
    public void TC_03_Cambridge() throws InterruptedException {
        driver.get("https://dictionary.cambridge.org/vi/");

        driver.findElement(By.cssSelector("span.cdo-register-button")).click();
        Thread.sleep(2000);
        switchToWindowByTitle("Login");
        Thread.sleep(2000);

        driver.findElement(By.cssSelector("form#gigya-login-form input[value='Login']")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("span#gigya-error-msg-gigya-login-form-loginID")).getText(), "This field is required");
        Assert.assertEquals(driver.findElement(By.cssSelector("span#gigya-error-msg-gigya-login-form-password")).getText(), "This field is required");
        driver.close();

        switchToWindowByTitle("Cambridge Dictionary | Từ điển tiếng Anh, Bản dịch & Từ điển từ đồng nghĩa");
        Thread.sleep(2000);

        driver.findElement(By.cssSelector("input#searchword")).sendKeys("automation");
        driver.findElement(By.cssSelector("button.cdo-search-button")).click();
        Thread.sleep(2000);

        Assert.assertEquals(driver.findElement(By.cssSelector("div.dictionary:nth-child(1) span")).getText(), "automation");
    }

    @Test
    public void TC_04_Harvard() throws InterruptedException {
        driver.get("https://courses.dce.harvard.edu/");
        driver.findElement(By.cssSelector("a[data-action='login']")).click();
        Thread.sleep(2000);
        System.out.println(driver.getTitle());

        switchToWindowById(driver.getWindowHandle());

        Assert.assertEquals(driver.getTitle(), "Harvard Division of Continuing Education Login Portal");
        Thread.sleep(3000);
        driver.close();

        switchToWindowByTitle("DCE Course Search");
        Thread.sleep(5000);

        Assert.assertTrue(driver.findElement(By.cssSelector("#sam-wait")).isDisplayed());
        driver.findElement(By.cssSelector("button.sam-wait__close")).click();

        driver.findElement(By.cssSelector("input#crit-keyword")).sendKeys("Data Science");
        select = new Select(driver.findElement(By.cssSelector("select#crit-srcdb")));
        select.selectByVisibleText("Harvard Summer School 2026");
        select = new Select(driver.findElement(By.cssSelector("select#crit-summer_school")));
        select.selectByVisibleText("Adult, Extension, & Visiting College");
        select = new Select(driver.findElement(By.cssSelector("select#crit-session")));
        select.selectByVisibleText("Any Part of Term");

        driver.findElement(By.cssSelector("button#search-button")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Data Structures']")).isDisplayed());
    }

    @Test
    public  void TC_05_Selenium_Ver4x() throws InterruptedException {
        driver.get("http://live.techpanda.org/");

        driver.findElement(By.xpath("//a[text()='Mobile']")).click();
        driver.switchTo().newWindow(WindowType.WINDOW).get("http://live.techpanda.org/index.php/customer/account/login/");
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("button#send2")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-email")).getText(), "This is a required field.");
        Assert.assertEquals(driver.findElement(By.cssSelector("div#advice-required-entry-pass")).getText(), "This is a required field.");
    }


    private void closeALlWindowWithoutParent(String githubWindowID) throws InterruptedException {
        //Lấy toàn bộ ID của window/tab
        Set<String> allWindowIDs = driver.getWindowHandles();
        //Dùng vòng lặp duyệt vào ID
        for (String id:allWindowIDs) {
           if(!id.equals(githubWindowID)) {
               driver.switchTo().window(id);
               Thread.sleep(2000);
               driver.close();
            }
        }

        //Sau khi chạy hết thì sẽ đóng hết các tab window ngoại trừ trang github
        //Driver đang đứng ở window/tab nào => tab cuối cùng bị close

        //System.out.println(driver.getTitle());//Sẽ bị fail vì driver ở tab đã bị close

        //Switch vào window duy nhất còn lại
        driver.switchTo().window(githubWindowID);
    }

    private void switchToWindowByTitle(String expectedTitle) {
        //Lấy toàn bộ ID của window/tab
        Set<String> allWindowIDs = driver.getWindowHandles();

        //Dùng vòng lặp duyệt vào ID
        for (String id:allWindowIDs) {
            //Mỗi lần duyệt sẽ cho nó switch vào trước
            driver.switchTo().window(id);
            //get ra title window hiện tại
            String pageTitle = driver.getTitle();
            if (pageTitle.equals(expectedTitle)) {
                break;
            }
        }
    }

    private void switchToWindowById(String githubWindowID) {
        //Chỉ đúng khi có 2 tab/window

        //List: cho phép lưu mảng trùng
        //Set: ko cho phép lưu trùng
        Set<String> allWindowIDs = driver.getWindowHandles(); //lấy tất cả ID tab đang có
        for (String id: allWindowIDs) {
            //Kiểm tra điều kiện: nếu ID nào khác với ID truyền vào thì switch qua
            if (!id.equals(githubWindowID)) {
                driver.switchTo().window(id);
            }
        }
    }



    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}