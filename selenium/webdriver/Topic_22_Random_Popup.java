package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_22_Random_Popup {

    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void TC_01_Random_Popup_JaveCode() throws InterruptedException {
        driver.get("https://www.javacodegeeks.com/");

        Thread.sleep(50000);

        By newsletterPopupBy = By.xpath("//div[@data-title='Newsletter Free eBooks'  and not(contains(@style, 'display:none'))]");

        //Hiển thị thì close đi rồi action tiếp
        if (driver.findElements(newsletterPopupBy).size() > 0 && driver.findElements(newsletterPopupBy).get(0).isDisplayed()) {
            System.out.println("-------Go to if------");
            driver.findElement(By.xpath("//div[@data-title='Newsletter Free eBooks' " +
                    "and not(contains(@style, 'display:none'))]//a[contains(@onclick, 'lepopup_close()')]")).click();
            Thread.sleep(3000);

            Assert.assertEquals(driver.findElements(newsletterPopupBy).size(), 0);
        }

        //Không hiển thị thì close đi luôn
        System.out.println("-------Ignore if------");
        driver.findElement(By.cssSelector("input#search-input")).sendKeys("Java");
        driver.findElement(By.cssSelector("button#search-submit")).click();

        Assert.assertTrue(driver.findElement(By.cssSelector("header#search-title-section h1.page-title")).isDisplayed());
    }
    @Test
    public  void TC_02_VNK_Edu() throws InterruptedException {
        driver.get("https://vnk.edu.vn/");
        By vnkPopup = By.cssSelector("div.popmake-content");

        Thread.sleep(10000);
        if (driver.findElements(vnkPopup).size() > 0 &&  driver.findElement(vnkPopup).isDisplayed()) {
            System.out.println("if");
            driver.findElement(By.cssSelector("div.popmake-content~button")).click();
            Assert.assertFalse(driver.findElement(vnkPopup).isDisplayed());
        }
        Thread.sleep(5000);
        driver.findElement(By.xpath("//button[text()='Danh sách khóa học']")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("div.title-content h1")).isDisplayed());
    }

    @Test
    public void TC_03_Dehieu() throws InterruptedException {
        driver.get("https://dehieu.vn/");
        Thread.sleep(7000);
        By loginPopup = By.cssSelector("div.css-modal-bt");

        if(driver.findElements(loginPopup).size() > 0 && driver.findElement(loginPopup).isDisplayed()) {
            System.out.println("if");
            driver.findElement(By.cssSelector("div#modalPopupForm button.close")).click();
            Assert.assertFalse(driver.findElement(loginPopup).isDisplayed());
        }

        Thread.sleep(3000);
        driver.findElement(By.xpath("//a[text()=' Đăng nhập']")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("span.title")).isDisplayed());
    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}