package webdriver;

import com.google.common.base.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;


public class Topic_36_Fluent_Wait {

    WebDriver driver;
    WebDriverWait explicitWait;

    //Fluent Wait sử dụng theo dạng tấn số hoặc chu kì nhất định
    //Cơ chế Fluent Wait giống implicit và explicit nhưng có thể custom lại polling time

    //Khác biệt giữa fluent và implicit/explicit:
        //Nếu như fluent không ignore exception trong quá trình xử lí thì sẽ fail ngay sau tần số đầu tiên, còn implicit và explicit đã mặc định ignore exception rồi


    //Khai báo tùy theo kiểu dữ liệu
    FluentWait<WebDriver> driverFluentWait;
    FluentWait<WebElement> elementFluentWait;
    FluentWait<String> stringFluentWait;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();

        driverFluentWait = new FluentWait<>(driver);
    }

    public void TC_01_() {
        //Tổng time = 10s tần số tìm lại mặc định 0,5s
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //Tổng time = 10s tần số tìm lại set là 0,3s
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofMillis(300));

    }

    @Test
    public void TC_02_Dynamic_Loading_1() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        findElement(By.cssSelector("div#start button")).click();
        Assert.assertEquals(getTextElement(By.cssSelector("div#finish h4")), "Hello World!");
    }

    @Test
    public void TC_02_Dynamic_Loading_2() {
        driver.get("https://automationfc.github.io/dynamic-loading/");
        findElement(By.cssSelector("div#start button")).click();
        Assert.assertTrue(isDisplayedElement(By.xpath("//h4[text()='Hello World!']")));
    }

    @Test
    public void TC_03_Countdown() {
        driver.get("https://automationfc.github.io/fluent-wait/");
        WebElement countDownTime = findElement(By.id("javascript_countdown_time"));
        Assert.assertTrue(isSeconMatching(countDownTime));
    }


    //Viết 1 hàm để tìm element với timeout/polling tự set
    //Điều kiện của hàm sẽ là findElementL kiểu trả về của hàm apply
    //findElement thì cần có driver: tham số của hàm apply
    public WebElement findElement(By by) {
        driverFluentWait = new FluentWait<>(driver);
        driverFluentWait
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);

        return driverFluentWait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                return driver.findElement(by);
            }
        });
    }

    //Kiểm tra element hiển thị
    //isDisplayed = boolean
    public boolean isDisplayedElement(By by) {
        driverFluentWait = new FluentWait<>(driver);
        driverFluentWait.withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class);

        return driverFluentWait.until(new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return driver.findElement(by).isDisplayed();
            }
        });
    }

    //Lấy ra 1 text của element
    //getText = string
    public String getTextElement(By by) {
        driverFluentWait = new FluentWait<>(driver);
        driverFluentWait.withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(300))
                .ignoring(NoSuchElementException.class);

        return driverFluentWait.until(new Function<WebDriver, String>() {
            @Override
            public String apply(WebDriver driver) {
                return driver.findElement(by).getText();
            }
        });
    }

    //Có sẵn element
    public boolean isElementDisplayed(WebElement element) {
        FluentWait<WebElement> driverFluentWait = new FluentWait<>(element);
        driverFluentWait.withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofMillis(100)).ignoring(NoSuchElementException.class);
        return driverFluentWait.until(new Function<WebElement, Boolean>() {
            @Override
            public Boolean apply(WebElement element) {
                return element.isDisplayed();
            }
        });
    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

    public boolean isSeconMatching(WebElement element) {
        FluentWait<WebElement> driverFluentWait = new FluentWait<>(element);
        driverFluentWait.withTimeout(Duration.ofSeconds(13))
                .pollingEvery(Duration.ofMillis(100)) //1s kiểm tra 5 lần
                .ignoring(NoSuchElementException.class);

        return driverFluentWait.until(new Function<WebElement, Boolean>() {
            @Override
            public Boolean apply(WebElement element) {
                return element.getText().endsWith("00");
            }
        });
    }
}