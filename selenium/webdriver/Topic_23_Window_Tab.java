package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.xml.transform.Source;
import java.time.Duration;
import java.util.Set;

public class Topic_23_Window_Tab {

    WebDriver driver;

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

        closeALlWindowWithoutParent(githubWindowID);

        driver.getTitle();
    }

    private void closeALlWindowWithoutParent(String githubWindowID) throws InterruptedException {
        //Switch qua tiki
        switchToWindowByTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");

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

    @Test
    public  void TC_02_() {

    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}