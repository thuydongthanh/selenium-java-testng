package testng;

//Chạy multiple browser: Chrome, Edge, Firefox

import org.bouncycastle.oer.Switch;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.time.Duration;

public class Topic_10_Multiple_Browser {

    WebDriver driver;
    String domainUrl;

    @BeforeClass
    @Parameters({"server", "browser"})
    public void beforeClass(String serverName, @Optional("Firefox") String broswerName) {

        //Kiểm tra tương đối: contains - chứa , startWith - bắt đầu bằng, endWith - kết thúc bằng
        //Kiểm tra tuyệt đối: equals - bằng(giá trị/hoa thường) - equalsIgnoreCase - bằng (giá trị/ko phân biệt hoa thưởng)

        //If-else
        if (serverName.equalsIgnoreCase("Dev")) {
            domainUrl = "https://dev-opensource-demo.orangehrmlive.com/";
        } else if (serverName.equalsIgnoreCase("Testing")) {
            domainUrl = "https://testing-opensource-demo.orangehrmlive.com/";
        } else if (serverName.equalsIgnoreCase("Production")) {
            domainUrl = "https://opensource-demo.orangehrmlive.com/";
        } else {
            throw new RuntimeException("Server name is not valid !!!");
        }

        //Switch case
        switch (broswerName) {
            case "Chrome":
                driver = new ChromeDriver();
                break;
            case "Edge":
                driver = new EdgeDriver();
                break;
            case "Firefox":
                driver = new FirefoxDriver();
                break;
            default:
                new RuntimeException("Browser name is not valid !!!");
        }

        //Khởi tạo browser
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
    }

    @Test
    public void TC_01_Login_Multiple(){
        driver.get(domainUrl);
        driver.findElement(By.cssSelector("input[name='username']")).sendKeys("Admin");
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys("admin123");
        driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        driver.quit();
    }

}
