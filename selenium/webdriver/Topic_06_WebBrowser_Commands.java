package webdriver;

import com.beust.ah.A;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

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

        //Lấy ra ID của tab/window hiện tại
            driver.getWindowHandles();
        //Lấy ra ID của tab/window đang active
            driver.getWindowHandle();

        //Đi tìm 1 element
            driver.findElement(By.xpath(""));
        //Đi tìm n element
            driver.findElements(By.xpath(""));

        //Dùng để chờ cho việc tìm elements
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
            driver.manage().timeouts().implicitlyWait(Duration.ofMinutes(15));
        //Dùng để chờ cho việc page được load xong
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(15));
        //Dùng để chờ cho 1 đoạn script được thực thi xong - code js
            driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(15));

        //Thu nhỏ về Taskbar để chạy
            driver.manage().window().minimize();
        //Phóng to lên (vẫn có Taskbar)
            driver.manage().window().maximize();
        // Tràn màn hình (ko có taskbar)
            driver.manage().window().fullscreen();

        // Set kích thước cho cửa sổ browser mở lên trên màn hình => ứng dụng để test responsive
            driver.manage().window().setSize(new Dimension(1920, 1000));
            driver.manage().window().getSize();
        // Set vị trí cho cửa sổ browser mở lên trên màn hình
            driver.manage().window().setPosition(new Point(0,0));
            driver.manage().window().getPosition();

        //Test GUI - Graphpic User Interface: font, collor, postion,...
    }
}