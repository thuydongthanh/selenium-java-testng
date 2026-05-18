package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Topic_24_Shadow_DOM {

    WebDriver driver;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @Test
    public void TC_01_Shadow_AutomationFC() {
        driver.get("https://automationfc.github.io/shadow-dom/");

        //Ko tìm được 2 shadow root liên tiếp nhau
        //Ko support tìm elemet bằng  xpath

        //Element cha chứa shadow host
        WebElement shadowHostParent = driver.findElement(By.cssSelector("div#shadow_host"));
        //Lấy ra element shadow root
        SearchContext firstShadow = shadowHostParent.getShadowRoot();

        Assert.assertTrue(firstShadow.findElement(By.cssSelector("input[type='file']")).isDisplayed());

        Assert.assertEquals(firstShadow.findElement(By.cssSelector("span.info")).getText(), "some text");

        //Element chứa cha Shadow host thứ 2
        WebElement firstShadowElement = firstShadow.findElement(By.cssSelector("div#nested_shadow_host"));
        //lấy ra Element Shadow Root
        SearchContext secondShadow = firstShadowElement.getShadowRoot();

        Assert.assertEquals(secondShadow.findElement(By.cssSelector("div#nested_shadow_content")).getText(), "nested text");

        Assert.assertTrue(firstShadow.findElement(By.cssSelector("a[href='scroll.html']")).isDisplayed());

        driver.findElement(By.cssSelector("a[href='scroll.html']"));
    }
    @Test
    public  void TC_02_Polymer() {
        driver.get("https://shop.polymer-project.org/");

        SearchContext firstShadow = driver.findElement(By.cssSelector("shop-app[page='home']")).getShadowRoot();
        SearchContext secondShadow = firstShadow.findElement(By.cssSelector("shop-home.iron-selected")).getShadowRoot();

        secondShadow.findElement(By.cssSelector("shop-button>a[aria-label=\"Men's Outerwear Shop Now\"]")).click();

        System.out.println(driver.getCurrentUrl());
    }

    @Test
    public void TC_03() {
        driver.get("https://www.salesforce.com/");
        SearchContext firstShadow = driver.findElement(By.cssSelector(".globalnav-wrapper-c360  hgf-c360nav")).getShadowRoot();
        SearchContext secondShadow = firstShadow.findElement(By.cssSelector("div.desktop-cta hgf-button")).getShadowRoot();

        secondShadow.findElement(By.cssSelector("a.hgf-button")).click();
    }
    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}