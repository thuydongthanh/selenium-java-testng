package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_13_Button {

    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();

    }

    @Test
    public void TC_01_Fahasa() throws InterruptedException {
        driver.get("https://www.fahasa.com/customer/account/create");
        driver.findElement(By.cssSelector("li.popup-login-tab-login")).click();

        By loginButton = By.cssSelector("button.fhs-btn-login");

        Assert.assertFalse(driver.findElement(loginButton).isEnabled());
        // Tìm elmemt->get css value-> chuyển qua đữ liệu collor -> chuyển qua kiểu hexa -> in hoa -> verify
        Assert.assertEquals(Color.fromString(driver.findElement(loginButton).getCssValue("background-color")).asHex().toUpperCase(), "#000000");

        driver.findElement(By.cssSelector("input#login_username")).sendKeys("dao@gmail.com");
        driver.findElement(By.cssSelector("input#login_password")).sendKeys("123456789");

        //Mong đợi nó là enabled
        Assert.assertTrue(driver.findElement(loginButton).isEnabled());
        Assert.assertEquals(Color.fromString(driver.findElement(loginButton).getCssValue("background-color")).asHex().toUpperCase(), "#C92127");
        driver.findElement(loginButton).click();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.cssSelector("div.fhs-login-msg")).getText(), "Số điện thoại/Email hoặc Mật khẩu sai!");
    }

    @Test
    public  void TC_02_Huawei() throws InterruptedException {
        driver.get("https://id5.cloud.huawei.com/CAS/portal/userRegister/regbyemail.html");
        Thread.sleep(3000);
        By registerButton = By.cssSelector("div.hwid-btn-reg");
        Assert.assertTrue(driver.findElement(registerButton).isDisplayed());
        //lấy ra màu
        Color backgroundButton = Color.fromString(driver.findElement(registerButton).getCssValue("background"));
        //chuyển mã màu sang hexa và in đậm mã đầu để verify
        Assert.assertEquals(backgroundButton.asHex().toUpperCase(), "#CA141D");
    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}