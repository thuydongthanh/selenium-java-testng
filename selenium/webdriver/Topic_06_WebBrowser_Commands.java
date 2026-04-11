package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.Logs;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URL;
import java.time.Duration;
import java.util.Set;

public class Topic_06_WebBrowser_Commands {

    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
        driver = new ChromeDriver();
        driver = new SafariDriver();
        driver = new InternetExplorerDriver();
    }

    @Test
    public void TC_01_Function() {
        //Mở ra 1 URL hợp lệ bất kỳ (lưu ý phải bắt đầu bằng http hoặc https)
            driver.get("https://demo.nopcommerce.com/login");

        //Đóng browser (close đúng tab và window đang đứng)
            driver.close();

        //Đóng browser (bao gồm tất cả các tab và window)
            driver.quit();

        //Lấy ra Title page hiện tại
            //1. Lưu dữ liệu lại rồi kiểm tra sau
            String homePageTitle = driver.getTitle();
            Assert.assertEquals(homePageTitle, "nopCommerce demo store. Login");
            //Recommend - 2. Kiểm tra trực tiếp luôn
            Assert.assertEquals(driver.getTitle(), "nopCommerce demo store. Login");

        //Lấy ra URL của page hiện tại đang đứng
            Assert.assertEquals(driver.getCurrentUrl(), "https://demo.nopcommerce.com/login");

         //Lấy ra Page Source Code - Toàn bộ source code: hmtl, css, js, jquery
            String homePageSourceCode = driver.getPageSource();
            //Kiểm tra tương đối
            Assert.assertTrue(homePageSourceCode.contains("Condition of Use"));

        //Lấy ra tất cả ID của tab/window các tab/window đang có
            driver.getWindowHandles();
        //Lấy ra ID của tab/window đang active
            driver.getWindowHandle();

        //Đi tìm 1 element
            driver.findElement(By.xpath(""));
        //Đi tìm n element
            driver.findElements(By.xpath(""));

         //Khai báo biến chung
        WebDriver.Timeouts timeouts = driver.manage().timeouts();
        WebDriver.Window windows = driver.manage().window();

        //Dùng để chờ cho việc tìm elements
            timeouts.implicitlyWait(Duration.ofSeconds(15));
            timeouts.implicitlyWait(Duration.ofMinutes(15));
        //Dùng để chờ cho việc page được load xong
            timeouts.pageLoadTimeout(Duration.ofSeconds(15));
        //Dùng để chờ cho 1 đoạn script được thực thi xong - code js
            timeouts.scriptTimeout(Duration.ofSeconds(15));

        //Thu nhỏ về Taskbar để chạy
            windows.minimize();
        //Phóng to lên (vẫn có Taskbar)
            windows.maximize();
        // Tràn màn hình (ko có taskbar)
            windows.fullscreen();

        // Set kích thước cho cửa sổ browser mở lên trên màn hình => ứng dụng để test responsive
            windows.setSize(new Dimension(1920, 1000));
            windows.getSize();
        // Set vị trí cho cửa sổ browser mở lên trên màn hình
            windows.setPosition(new Point(0,0));
            windows.getPosition();

        //Lấy hết tất cả cookie
            Set<Cookie> cookies = driver.manage().getCookies();

        //Lấy một giá trị của cookie
            driver.manage().getCookieNamed("Truyền vào tên cookie");

        //Xóa hết tất cả cookie
            driver.manage().deleteAllCookies();

        //Xóa cookie theo thứ tự dùng vòng lặp để xóa
            for (Cookie cookie: cookies) {
                driver.manage().deleteCookie(cookie);
            }
        //Xóa cookie theo tên
            driver.manage().deleteCookieNamed("Truyền vào tên cookie");

        //Đến 1 Test Class khác 02/03/04 => Không cần login - set cookie đã có vào đây rồi refresh lại
            for (Cookie cookie: cookies) {
                //add cookie theo thứ tự
                driver.manage().addCookie(cookie);
            }
            driver.navigate().refresh(); //Refresh lại là login thành công
        
            Logs logs = driver.manage().logs();
            LogEntries logEntries = logs.get("BROWSER");
            for (LogEntry LogEn: logEntries) {
                System.out.println(LogEn);
            }

            logs.getAvailableLogTypes();

        //Navigate
            WebDriver.Navigation navigation = driver.navigate();
            navigation.refresh(); //Refresh browswer
            navigation.back(); //back lại trang trước đó
            navigation.forward(); // chuyển tiếp tới trang trước đó
            navigation.to("Truyền url"); // Mở url bất kì

        //Switch to: Alert, Iframe, Windows
            WebDriver.TargetLocator targetLocator = driver.switchTo();
            targetLocator.alert().accept();
            targetLocator.alert().dismiss();

            targetLocator.frame("");
            targetLocator.defaultContent();

            targetLocator.window("");
    }
}