package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class Topic_33_Explicit_Wait_Ajax {

    WebDriver driver;
    WebDriverWait explicitWait;
    String uploadFilesPath = System.getProperty("user.dir") + File.separator + "uploadFiles" + File.separator;

    String firstImage = "a.jpg";
    String secondImage = "b.jpg";
    String thirdImage = "c.jpg";

    String firstImagePath = uploadFilesPath + firstImage;
    String secondImagePath = uploadFilesPath + secondImage;
    String thirdImagePath = uploadFilesPath + thirdImage;

    @BeforeClass
    public void initialBrowser() {
        driver = new FirefoxDriver();
    }

    @Test
    public void TC_01_Calender() throws InterruptedException {
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));

        driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

        //Wait và verify cho Calander element xuất hiện
        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#ctl00_ContentPlaceholder1_Panel1"))).isDisplayed());

        //Wait and verify text
        explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"), "No Selected Dates to display."));

        //Wait and click elemnet
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//td/a[text()='24']"))).click();

        //Wait and verify ajax loading invisible
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div[id$='RadCalendar1']>div.raDiv"))));

        //Wait and verify text
        Assert.assertTrue(explicitWait.until(ExpectedConditions.textToBe(By.cssSelector("span#ctl00_ContentPlaceholder1_Label1"), "Sunday, May 24, 2026")));
    }

    @Test
    public void TC_02_GoFile() {
        //Set cả 2 wait: implicit và explicit
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://gofile.io/?t=uploadFiles");

        //Wait cho loading mất đi
        //Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.animate-spin"))));

        //Sendkey upload file
        driver.findElement(By.cssSelector("input[type='file']")).sendKeys(firstImagePath + "\n" + secondImagePath + "\n" + thirdImagePath);

//        //Wait loading biến mất
//        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("span#destinationFolder"))));

//        //Wait cho các progress bar của các file biến mất
//        List<WebElement> listProgressBar = driver.findElements(By.cssSelector("div.progress-container"));
//        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(listProgressBar)));

        //Wait cho loading progress bar biến mất
        List<WebElement> loadingProgressBar = driver.findElements(By.cssSelector("div.processing-indicator"));
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfAllElements(loadingProgressBar)));

        //Wait và kiểm tra text xuất hiện
        Assert.assertTrue(explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Upload Complete']"))).isDisplayed());

        //Click element link
        driver.findElement(By.cssSelector("a.linkSuccessCard")).click();

        //Wait loading biến mất
        Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#filemanager_loading .animate-spin"))));

        //Verify text xuất hiện
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+ firstImage +"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+ secondImage +"']")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.xpath("//a[text()='"+ thirdImage +"']")).isDisplayed());
    }

    @AfterClass
    public void cleanBrowser() {
        driver.quit();
    }

}