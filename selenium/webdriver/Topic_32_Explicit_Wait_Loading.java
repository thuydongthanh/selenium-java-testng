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

public class Topic_32_Explicit_Wait_Loading {

    WebDriver driver;
    WebDriverWait explicitWait;

    //Cách set time out
        //- Nêu timemout set không đủ vẫn Fail như thường
        //- Set vừa đủ => pass
        //- Set dư timeout => Ko cần chờ hết timeout

    //Khi wait xong sẽ trả về kiểu dữ liệu tương ứng (boolean/element/...) => Lấy dữ liệu đó thao tác luônn

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
    }

    @Test
    public void TC_01_Less_Than() throws InterruptedException {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(3));

        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div#start>button")).click();

        //Điều kiện Wait
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));

        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
    }
    @Test
    public  void TC_02_Equal() throws InterruptedException {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div#start>button")).click();

        //Điều kiện Wait
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));

        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
    }

    @Test
    public  void TC_03_Greater_Than() throws InterruptedException {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));

        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div#start>button")).click();

        //Điều kiện Wait
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));

        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");
    }

    @Test
    public void TC_04() { //=> Ưu tiên sử dụng cách 1 Viisible trong case này
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.get("https://automationfc.github.io/dynamic-loading/");

        driver.findElement(By.cssSelector("div#start>button")).click();

        //Visible dành cho 1 element sau được xuất hiện
        //Trả về webelement có thể lưu lại biến và get text luôn
         WebElement helloWorld =  explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));
         Assert.assertEquals(helloWorld.getText(), "Hello World!");

        //Invisible dành cho 1 element sắp biến mất đi
        boolean loadingIconStatus = explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));
        Assert.assertTrue(loadingIconStatus);
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), "Hello World!");

        //Text
        boolean helloTextStatus = explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("div#finish>h4"), "Hello World!"));
        Assert.assertTrue(helloTextStatus);

    }


    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}