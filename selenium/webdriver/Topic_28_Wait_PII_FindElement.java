package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Topic_28_Wait_PII_FindElement {

    //Cơ chế wait của Selenium : Implicit, Explicit, Fluent
    //Nếu tìm thấy element thì sẽ dừng luôn không cần chờ hết time
    //Nếu không tìm thấy element thì sau 0,5s sẽ tìm lại 1 lần (Polling Time: cơ chế tìm lại)
        //Tìm lại mà thấy element thì không cần chở hết tổng time còn lại
        //Tìm lại và không thấy thì hết tổng time sẽ đánh Fail

    //Implicit Wait: polling time mặc định là 0,5s không thể sửa được
    //Explicit Waiit: polling time mặc định là 0,5s và có thể thay đổi được
    //Fluent Wait: Polling time tự set và thay đổi được


    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {
        driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(13));
    }

    @Test
    public void TC_01_FindElement() {
        driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
        //1. Nếu tìm thấy duy nhất 1 element
        driver.findElement(By.cssSelector("input#FirstName"));

        //2. Nếu tìm thấy nhiêu hơn 1 element => chỉ thao tác element đầu tiên => Nên check locator là duy nhất trước
        driver.findElement(By.cssSelector("input[type='text']")).sendKeys("abc");

        //3. Nếu không tìm thấy element
        //Show lỗi NoSuchElementException
         driver.findElement(By.cssSelector("input#RememberMe"));
    }

    @Test
    public  void TC_02_() {
        driver.get("http://demo.nopcommerce.com/register?returnUrl=%2F");
        //1. Nếu tìm thấy duy nhất 1 element
        List<WebElement> elements = null;
        elements = driver.findElements(By.cssSelector("input#FirstName"));
        System.out.println(elements.size());

        //2. Nếu tìm thấy nhiêu hơn 1 element
        elements = driver.findElements(By.cssSelector("input[type='text']"));
        System.out.println(elements.size());

        //3. Nếu không tìm thấy element => Ko tìm thấy sau khi hết time trả về List = 0 và không đánh fail test case chạy tiếp các step sau
        elements = driver.findElements(By.cssSelector("input#RememberMe"));
        System.out.println(elements.size());
        Assert.assertEquals(elements.size(), 0);
    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}